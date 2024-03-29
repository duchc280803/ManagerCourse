<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

/**
 * Get list course
 */
const listCourse = ref([]);
var pageNumber = 0;
var pageSize = 6;
const getListCourse = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/course/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
    )
    .then(function (response) {
      listCourse.value = response.data;
    });
};
getListCourse();
const nextPage = function () {
  pageNumber++;
  getListCourse();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    getListCourse();
  }
};

/**
 * Add list teacher to input
 */
const selectedCourseName = ref("");
const dropdownVisible = ref(false);
const nameTeacher = ref([]);
const showDropdown = () => {
  dropdownVisible.value = true;
};
const addItem = () => {
  // Thêm giá trị mới vào mảng inputValue
  nameTeacher.value.push(selectedCourseName.value.trim());
  dropdownVisible.value = false;
};
const getListTeacher = ref([]);

/**
 * Show teacher
 */
const fillTeacher = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/fill-teacher`)
    .then(function (response) {
      getListTeacher.value = response.data;
    });
};
fillTeacher();

/**
 * Create a new course
 */
const courseCode = ref("COURSE" + new Date().getTime());
const courseName = ref("");
const courseTime = ref("");
const coursePrice = ref("");
const quantitySubject = ref("");
const description = ref("");
const status = ref(1);
const errorCourseCreate = ref({});
const createCourse = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới khóa học không?",
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
      const formData = {
        courseCode: courseCode.value,
        courseName: courseName.value,
        courseTime: courseTime.value,
        coursePrice: coursePrice.value,
        quantitySubject: quantitySubject.value,
        description: description.value,
        nameTeacher: nameTeacher.value,
        status: status.value,
      };
      axios
        .post(`http://localhost:8080/api/v1/course/create`, formData)
        .then(function (response) {
          listCourse.value.push(response.data);
          getListCourse();
          let modal = document.getElementById("exampleModal");
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
          errorCourseCreate.value = error.response.data;
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
 * Seacher course
 */
const search = ref("");
const searchCourse = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/course/search?nameCourse=${search.value}`
    )
    .then(function (response) {
      listCourse.value = response.data;
    });
};
watch(search, () => {
  searchCourse();
});

/**
 * Refresh
 */
function refreshCourse() {
  getListCourse();
  search.value = "";
}

/**
 * Detail course
 */
const detailCourse = ref({});
const getDetailCourse = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/course/detail?id=${id}`)
    .then(function (response) {
      detailCourse.value = response.data;
    });
};
const addItemUpdate = () => {
  detailCourse.value.nameTeacher.push(selectedCourseName.value.trim());
  dropdownVisible.value = false;
};

/**
 * Update course
 */
const errorupdateTeacher = ref({});
const updateTeacher = async (id) => {
  Swal.fire({
    title: "Bạn có muốn update lớp học không?",
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
          `http://localhost:8080/api/v1/course/update?id=${id}`,
          detailCourse.value
        )
        .then(function (response) {
          getListCourse();
          let modal = document.getElementById("exampleModal1");
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
          errorupdateTeacher.value = error.response.data;
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
  <div class="container student">
    <div class="card border">
      <div class="card-header">
        <div class="card-title">
          <h3>Khóa học</h3>
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
              @click="refreshCourse()"
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
              data-bs-target="#exampleModal"
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
                <th>Mã khóa học</th>
                <th>Tên khóa học</th>
                <th style="width: 130px">Giảng viên</th>
                <th>Dự kiến</th>
                <th>Số môn</th>
                <th>Giá khóa</th>
                <th style="width: 100px">Trạng thái</th>
                <th style="width: 100px">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(cs, index) in listCourse"
                :key="cs.id"
                style="text-align: center">
                <td>{{ index + 1 + pageNumber * pageSize }}</td>
                <td>{{ cs.courseCode }}</td>
                <td>{{ cs.courseName }}</td>
                <td>
                  <select
                    class="form-select"
                    aria-label="Default select example">
                    <option disabled value="">Giảng viên</option>
                    <option value="1" v-for="tc in cs.teacherOfClassResponses">
                      {{ tc.fullName }}
                    </option>
                  </select>
                </td>
                <td>{{ cs.scheduled }}</td>
                <td>{{ cs.quantitySubject }}</td>
                <td>{{ cs.coursePrice.toLocaleString('vi-VN') }}đ</td>
                <td>
                  <span
                    :style="{
                      'background-color': cs.status == 1 ? '#9bcf53' : 'red',
                      'border-radius': '5px',
                      color: cs.status == 1 ? 'darkgreen' : 'white',
                    }">
                    {{ cs.status == 1 ? "Đang hoạt động" : "Ngừng hoạt động" }}
                  </span>
                </td>
                <td>
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal1"
                    @click="getDetailCourse(cs.id)"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Sửa
                  </button>
                  <RouterLink :to="`/course/subject/${cs.id}`">
                    <button
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
    id="exampleModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm mới khóa học</h5>
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
                  Mã khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã khóa học..."
                    v-model="courseCode"
                    disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên khóa học..."
                    v-model="courseName" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.courseName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giảng Viên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <div class="combobox">
                    <div class="card" v-show="dropdownVisible">
                      <select
                        class="form-select"
                        v-model="selectedCourseName"
                        @change="addItem()">
                        <option
                          v-for="item in getListTeacher"
                          :key="item"
                          :value="item.fullName">
                          {{ item.fullName }}
                        </option>
                      </select>
                    </div>
                    <input
                      type="text"
                      class="form-control an-select"
                      placeholder="Chọn giảng viên"
                      v-model="nameTeacher"
                      @focus="showDropdown"
                      @blur="hideDropdown" />
                  </div>
                  <span style="color: red">{{
                    errorCourseCreate.nameTeacher
                  }}</span>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng môn..."
                    v-model="quantitySubject" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.quantitySubject
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Dự kiến
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Dự kiến..."
                    v-model="detailCourse.scheduled" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.scheduled
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giá khóa
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Giá khóa..."
                    v-model="coursePrice" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.coursePrice
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Mô tả
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea
                    type="number"
                    class="form-control an-select"
                    placeholder="Mô tả..."
                    v-model="description"></textarea>
                </div>
                <span style="color: red">{{
                  errorCourseCreate.description
                }}</span>
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
          <button type="button" class="btn btn-primary" @click="createCourse()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="exampleModal1"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Sửa khóa học</h5>
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
                  Mã khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã khóa học..."
                    v-model="detailCourse.courseCode" disabled/>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên khóa học..."
                    v-model="detailCourse.courseName" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.courseName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giảng dậy
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <div class="combobox">
                    <div class="card" v-show="dropdownVisible">
                      <select
                        class="form-select"
                        v-model="selectedCourseName"
                        @change="addItemUpdate()">
                        <option
                          v-for="item in getListTeacher"
                          :key="item"
                          :value="item.fullName">
                          {{ item.fullName }}
                        </option>
                      </select>
                    </div>
                    <input
                      type="text"
                      class="form-control an-select"
                      placeholder="Chọn giảng viên"
                      v-model="detailCourse.nameTeacher"
                      @focus="showDropdown"
                      @blur="hideDropdown" />
                  </div>
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.nameTeacher
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng môn..."
                    v-model="detailCourse.quantitySubject" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.quantitySubject
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Dự kiến
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Dự kiến..."
                    v-model="detailCourse.scheduled" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.scheduled
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giá khóa
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Giá khóa..."
                    v-model="detailCourse.coursePrice" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.coursePrice
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Mô tả
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea
                    type="number"
                    class="form-control an-select"
                    placeholder="Mô tả..."
                    v-model="detailCourse.description"></textarea>
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.description
                }}</span>
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
                    v-model="detailCourse.status">
                    <option :value="1">Đang mở</option>
                    <option :value="2">Đang đóng</option>
                  </select>
                </div>
                <span style="color: red">{{ errorupdateTeacher.status }}</span>
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
            @click="updateTeacher(detailCourse.id)">
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
.modal-content.lg {
  width: 950px;
  margin-left: -200px;
}
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
button.btn.btn-primary.action {
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
.modal-title {
  font-size: 24px;
}
.ctrl_label {
  font-style: normal;
  font-weight: 600 !important;
  font-size: 16px !important;
  color: #000 !important;
  align-items: center;
  display: flex;
}
.text-danger {
  color: #f64e60 !important;
}
input.form-control.an-select {
  box-sizing: border-box;
  margin: 0;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5715;
  list-style: none;
  font-feature-settings: "tnum", "tnum";
  position: relative;
  display: inline-block;
  cursor: pointer;
}
a.btn.btn-primary.action {
  margin: 10px;
  width: 70px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
}
.pap {
  padding-top: 20px;
}
td {
  padding-left: 5px;
  width: 50px;
  height: 50px;
  background-color: #f0f0f0; /* Màu nền của ô */
  border: 1px solid #ccc; /* Đường viền */
  border-radius: 10px; /* Góc bo */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* Đổ bóng */
}
tr {
  height: 54px;
}
</style>
