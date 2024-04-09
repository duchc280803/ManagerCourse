<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

/**
 * Get list class
 */
const listClass = ref([]);
var pageNumber = 0;
var pageSize = 6;
const fetchClassData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
    listClass.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassData();
const nextPage = function () {
  pageNumber++;
  fetchClassData();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchClassData();
  }
};

/**
 * Search
 */
const search = ref("");
const searchClass = async () => {
  axios
    .get(`http://localhost:8080/api/v1/class/search?className=${search.value}`)
    .then(function (response) {
      listClass.value = response.data;
    });
};
watch(search, () => {
  searchClass();
});

/**
 * Refresh
 */
function refreshClass() {
  search.value = "";
  fetchClassData();
}

/**
 * Get list course
 */
const listCourseName = ref([]);
const getListCourseName = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/course-name`)
    .then(function (response) {
      listCourseName.value = response.data;
    });
};
getListCourseName();

/**
 * Get list teacher
 */
 const courseName = ref("");
const getListTeacher = ref([]);
const fillTeacher = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/fill-teacher-for-course?courseName=${courseName.value}`)
    .then(function (response) {
      getListTeacher.value = response.data;
    });
};
watch(courseName, () => {
  fillTeacher();
})

/**
 * Create a new class
 */
const classCode = ref("CLASS_CODE" + new Date().getTime());
const className = ref("");
const quantityStudent = ref("");
const required = ref("");
const teacherName = ref("");


const errorCreateClass = ref({});
const createClass = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới lớp học không?",
    text: "",
    icon: "question",
    showCancelButton: true,
    cancelButtonText: "Hủy bỏ",
    cancelButtonColor: "#d33",
    confirmButtonColor: "#3085d6",
    confirmButtonText: "Xác nhận",
    reverseButtons: true,
  }).then(async (result) => {
    if (result.value) {
      const classData = {
        classCode: classCode.value,
        className: className.value,
        quantityStudent: quantityStudent.value,
        required: required.value,
        teacherName: teacherName.value,
        courseName: courseName.value,
      };
      axios
        .post(`http://localhost:8080/api/v1/class/create`, classData)
        .then(function (response) {
          listClass.value.push(response.data);
          fetchClassData();
          let modal = document.getElementById("class-add");
          if (modal) {
            modal.classList.remove("show");
            modal.setAttribute("aria-hidden", "true");
            modal.style.display = "none";
            let modalBackdrop =
              document.getElementsByClassName("modal-backdrop")[0];
            if (modalBackdrop) {
              modalBackdrop.parentNode.removeChild(modalBackdrop);
            }
          }
        })
        .catch(function (error) {
          errorCreateClass.value = error.response.data;
        });
    } else {
      Swal.fire({
        position: "top-end",
        icon: "success",
        title: "Hủy bỏ thành công",
        timer: 1500,
        width: "400px",
        height: "300px",
        customClass: {
          popup: "small-popup",
        },
        showConfirmButton: false,
      });
    }
  });
};

/**
 * Get details of a class
 */
const classUpdate = ref({});
const classDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/class/detail?id=${id}`)
    .then(function (response) {
      classUpdate.value = response.data;
      console.log(classUpdate.value);
    });
};

/**
 * Update the class
 */
const errorUpdateClass = ref({})
const updateclass = async (id) => {
  Swal.fire({
    title: "Bạn có muốn cập nhập lớp học không?",
    text: "",
    icon: "question",
    showCancelButton: true,
    cancelButtonText: "Hủy bỏ",
    cancelButtonColor: "#d33",
    confirmButtonColor: "#3085d6",
    confirmButtonText: "Xác nhận",
    reverseButtons: true,
  }).then(async (result) => {
    if (result.value) {
      await axios
        .put(
          `http://localhost:8080/api/v1/class/update?id=${id}`,
          classUpdate.value
        )
        .then(function (response) {
          fetchClassData();
          let modal = document.getElementById("class-update");
          if (modal) {
            modal.classList.remove("show");
            modal.setAttribute("aria-hidden", "true");
            modal.style.display = "none";
            let modalBackdrop =
              document.getElementsByClassName("modal-backdrop")[0];
            if (modalBackdrop) {
              modalBackdrop.parentNode.removeChild(modalBackdrop);
            }
          }
        }).catch(function (error) {
          errorUpdateClass.value = error.response.data
        });
    } else {
      Swal.fire({
        position: "top-end",
        icon: "success",
        title: "Hủy bỏ thành công",
        timer: 1500,
        width: "400px",
        height: "300px",
        customClass: {
          popup: "small-popup",
        },
        showConfirmButton: false,
      });
    }
  });
};
</script>
<template>
  <div class="container class">
    <div class="card border">
      <div class="card-header">
        <div class="card-title">
          <h3>Lớp lập trình</h3>
        </div>
      </div>
      <div class="card-body">
        <div class="row search_table">
          <div class="col-4">
            <div class="input-group-prepend position-relative">
              <input
                type="text"
                placeholder="Tìm kiếm"
                class="form-control search"
                v-model.trim="search" />
            </div>
          </div>
          <div class="col-lg-4">
            <button
              @click="refreshClass()"
              style="
                text-decoration: none;
                background-color: #fc6736;
                border-radius: 5px;
                color: black;
                margin-left: 25px;
                margin-top: 15px;
                font-weight: 600;
              ">
              Làm mới
            </button>
          </div>
          <div class="col-2"></div>
          <div class="col-2">
            <button
              data-bs-toggle="modal"
              data-bs-target="#class-add"
              style="
                text-decoration: none;
                background-color: #fc6736;
                border-radius: 5px;
                color: black;
                margin-top: 15px;
                font-weight: 600;
              ">
              Thêm mới
            </button>
          </div>
        </div>
        <section class="section-table">
          <table>
            <thead>
              <tr
                style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
                <th>STT</th>
                <th>Mã lớp</th>
                <th>Tên lớp</th>
                <th>Khóa học</th>
                <th>Giảng viên</th>
                <th style="width: 90px">Số lượng</th>
                <th style="width: 90px">Trạng thái</th>
                <th style="width: 150px">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(cl, index) in listClass"
                :key="cl.id"
                style="text-align: center">
                <td>{{ index + 1 + pageNumber * pageSize }}</td>
                <td>{{ cl.classCode }}</td>
                <td>{{ cl.className }}</td>
                <td>
                  {{ cl.subjectName }}
                </td>
                <td>
                  {{ cl.teacherName }}
                </td>
                <td>{{ cl.quantityStudent }}</td>
                <td>
                  <span
                    :style="{
                      'background-color': cl.status == 1 ? '#9bcf53' : 'red',
                      'border-radius': '5px',
                      color: cl.status == 1 ? 'darkgreen' : 'white',
                    }">
                    {{ cl.status == 1 ? "Đang mở" : "Đóng" }}
                  </span>
                </td>
                <td>
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#class-update"
                    @click="classDetail(cl.id)"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Sửa
                  </button>
                  <RouterLink :to="`/class/information/${cl.id}/${cl.subjectName}`">
                    <button
                      @click="classDetail(cl.id)"
                      style="
                        text-decoration: none;
                        background-color: #fc6736;
                        border-radius: 5px;
                        color: black;
                        font-weight: 600;
                        margin-left: 5px;
                      ">
                      Chi tiết
                    </button>
                  </RouterLink>
                </td>
              </tr>
            </tbody>
          </table>
        </section>
        <nav aria-label="Page navigation example" style="padding-left: 985px">
          <ul class="pagination">
            <li class="page-item">
              <button class="page-link" @click="previousPage()">
                Previous
              </button>
            </li>
            <li class="page-item">
              <a class="page-link" href="#">{{ pageNumber + 1 }}</a>
            </li>
            <li class="page-item">
              <button class="page-link" @click="nextPage()">Next</button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
  <!-- Modal add-->
  <div
    class="modal fade"
    id="class-add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm lớp mới</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div
              class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Mã lớp
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã lớp..."
                    v-model="classCode"
                    disabled />
                </div>
                <span style="color: red">{{ errorCreateClass.classCode }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên lớp
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên lớp..."
                    v-model="className" />
                </div>
                <span style="color: red">{{ errorCreateClass.className }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng..."
                    v-model="quantityStudent" />
                </div>
                <span style="color: red">{{
                  errorCreateClass.quantityStudent
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Yêu cầu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Yêu cầu..."
                    v-model="required" />
                </div>
                <span style="color: red">{{ errorCreateClass.required }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <select
                  class="form-select"
                  aria-label="Default select example"
                  v-model="courseName">
                  <option value="" disabled>Chọn khóa học</option>
                  <option
                    v-for="item in listCourseName"
                    :value="item.courseName">
                    {{ item.courseName }}
                  </option>
                </select>
                <span style="color: red">{{
                  errorCreateClass.courseName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giảng viên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="teacherName">
                    <option value="" disabled>Chọn giảng viên</option>
                    <option
                      v-for="item in getListTeacher"
                      :value="item.fullName">
                      {{ item.fullName }}
                    </option>
                  </select>
                  <span style="color: red">{{
                    errorCreateClass.teacherName
                  }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="createClass()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="class-update"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Sửa lớp học</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div
              class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Mã lớp
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã lớp..."
                    v-model="classUpdate.classCode"
                    disabled />
                </div>
                <span style="color: red">{{
                    errorUpdateClass.classCode
                  }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên lớp
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên lớp..."
                    v-model="classUpdate.className" />
                </div>
                <span style="color: red">{{
                    errorUpdateClass.className
                  }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng..."
                    v-model="classUpdate.quantityStudent" />
                </div>
                <span style="color: red">{{
                    errorUpdateClass.quantityStudent
                  }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Mục tiêu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mục tiêu..."
                    v-model="classUpdate.required" />
                </div>
                <span style="color: red">{{
                    errorUpdateClass.required
                  }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classUpdate.courseName">
                    <option
                      v-for="item in listCourseName"
                      :value="item.courseName">
                      {{ item.courseName }}
                    </option>
                  </select>
                  <span style="color: red">{{
                    errorUpdateClass.courseName
                  }}</span>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giảng viên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classUpdate.teacherName">
                    <option
                      v-for="item in getListTeacher"
                      :value="item.fullName">
                      {{ item.fullName }}
                    </option>
                  </select>
                  <span style="color: red">{{
                    errorUpdateClass.teacherName
                  }}</span>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Trạng thái
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classUpdate.status">
                    <option :value="1">Đang mở</option>
                    <option :value="2">Đang đóng</option>
                  </select>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Hủy
          </button>
          <button
            type="button"
            class="btn btn-primary"
            @click="updateclass(classUpdate.id)">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
* {
  font-family: BaiJamjuree !important;
}
.card.border {
  box-shadow: 0 0 30px #523f690d;
  border: 1px solid #523f690d;
  border-radius: 20px;
}
.search_table {
  margin: 0;
}
.search_table input {
  height: 42px;
  border-radius: 16px;
  padding: 9px 30px 9px 12px;
  margin: 10px 5px 10px 0;
  border: 1px solid #e3ebf6;
  font-size: 15px;
}
input.form-control.search {
  width: 410px;
}
.search_table .btn {
  margin: 10px 0;
  width: 80px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
}
a.btn.btn-primary.action {
  margin: 10px;
  width: 70px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
}
table {
  width: 100%;
}
.section-table {
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: auto;
  overflow: overlay;
}
td {
  height: 50px;
  background-color: #f0f0f0; /* Màu nền của ô */
  border: 1px solid #ccc; /* Đường viền */
  border-radius: 10px; /* Góc bo */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* Đổ bóng */
}
.pap {
  padding-top: 20px;
}
tr {
  height: 54px;
}
</style>
