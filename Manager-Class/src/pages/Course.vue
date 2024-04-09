<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";
import { notifyError, notifySuccess } from "@/toast-message/NotifyMessage"
/**
 * Get list course
 */
const listCourse = ref([]);
let pageNumber = 0;
let pageSize = 6;
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
const description = ref("");
const scheduled = ref("");
const image = ref("");
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
        description: description.value,
        scheduled: scheduled.value,
        nameTeacher: nameTeacher.value,
        image: image.value,
        status: status.value,
      };
      await axios
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
          notifySuccess("Thêm thành công khóa học!")
        })
        .catch(function (error) {
          notifyError("Thêm thất bại khóa học!")
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
          notifySuccess("Sửa thành công khóa học!")
        })
        .catch(function (error) {
          notifyError("Sửa khóa học thất bại")
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
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <div class="col-inner">
          <div class="sidebar-filter">
            <div class="course-filter-title"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
                viewBox="0 0 32 32" fill="none">
                <path
                  d="M14.6667 26.6668C14.2889 26.6668 13.9723 26.5391 13.7167 26.2835C13.4611 26.0279 13.3334 25.7113 13.3334 25.3335V17.3335L5.3667 7.16683C5.05559 6.78905 5.01115 6.38905 5.23337 5.96683C5.45559 5.54461 5.80004 5.3335 6.2667 5.3335H25.7334C26.2 5.3335 26.5445 5.54461 26.7667 5.96683C26.9889 6.38905 26.9445 6.78905 26.6334 7.16683L18.6667 17.3335V25.3335C18.6667 25.7113 18.5389 26.0279 18.2834 26.2835C18.0278 26.5391 17.7111 26.6668 17.3334 26.6668H14.6667Z"
                  fill="#6FBD44"></path>
              </svg> Bộ lọc tìm kiếm </div>
            <div class="pap"></div>
            <div class="item-filter">
              <div class="box-filter">
                <div class="input-group-prepend position-relative">
                  <input type="text" placeholder="Tìm kiếm" class="form-control search" v-model.trim="search" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-9">
        <div class="row">
          <div class="col-6">
          </div>
          <div class="col-2"></div>
          <div class="col-2"><button @click="refreshCourse()" style="
                  text-decoration: none;
                  background-color: #fc6736;
                  border-radius: 5px;
                  color: black;
                  margin-top: 15px;
                  font-weight: 600;">
              Làm mới
            </button></div>
          <div class="col-2">
            <button data-bs-toggle="modal" data-bs-target="#exampleModal" style="
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
        <br>
        <div class="row">
          <div class="col-lg-4" v-for="(cs, index) in listCourse" :key="cs.id" style="padding-bottom: 15px;">
            <div class="card item-course">
              <div class="item-img">
                <RouterLink :to="`/course/subject/${cs.id}`">
                  <img :src="cs.image" alt="" width="360" height="190" />
                </RouterLink>
              </div>
              <div class="item-info">
                <div class="item-detail full-width">
                  <h3 class="item-title">
                    {{ cs.courseName }}
                  </h3>
                </div>
                <div class="item-price">
                  {{ cs.coursePrice.toLocaleString('vi-VN') }} VNĐ
                </div>
                <div class="item-meta">
                  <div class="item-meta-line">
                    <div>
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17" viewBox="0 0 24 24" fill="none"
                        stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                        class="lucide lucide-calendar-check-2">
                        <path d="M8 2v4" />
                        <path d="M16 2v4" />
                        <path d="M21 14V6a2 2 0 0 0-2-2H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h8" />
                        <path d="M3 10h18" />
                        <path d="m16 20 2 2 4-4" />
                      </svg> Dự kiến học: {{ cs.scheduled }}
                    </div>
                  </div>
                  <div class="item-meta-line">
                    <div>
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17" viewBox="0 0 24 24" fill="none"
                        stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                        class="lucide lucide-bar-chart">
                        <line x1="12" x2="12" y1="20" y2="10" />
                        <line x1="18" x2="18" y1="20" y2="4" />
                        <line x1="6" x2="6" y1="20" y2="16" />
                      </svg> Trạng thái: <span
                        :style="{ 'background-color': cs.status == 1 ? '#9bcf53' : 'red', 'border-radius': '5px', color: cs.status == 1 ? 'darkgreen' : 'white', }">
                        {{ cs.status == 1 ? "Đang hoạt động" : "Ngừng hoạt động" }}
                      </span>
                    </div>
                  </div>
                  <div class="item-meta-line">
                    <div>
                      <button data-bs-toggle="modal" data-bs-target="#exampleModal1" @click="getDetailCourse(cs.id)"
                        style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                        Chỉnh sửa
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

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
  <!-- Modal add-->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm mới khóa học</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Mã khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Mã khóa học..." v-model="courseCode"
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
                  <input type="text" class="form-control an-select" placeholder="Tên khóa học..."
                    v-model="courseName" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.courseName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Ảnh
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Url Ảnh..." v-model="image" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.image
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
                      <select class="form-select" v-model="selectedCourseName" @change="addItem()">
                        <option v-for="item in getListTeacher" :key="item" :value="item.fullName">
                          {{ item.fullName }}
                        </option>
                      </select>
                    </div>
                    <input type="text" class="form-control an-select" placeholder="Chọn giảng viên"
                      v-model="nameTeacher" @focus="showDropdown" @blur="hideDropdown" />
                  </div>
                  <span style="color: red">{{
                    errorCourseCreate.nameTeacher
                  }}</span>
                </div>
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
                  <input type="text" class="form-control an-select" placeholder="Dự kiến..." v-model="scheduled" />
                </div>
                <span style="color: red">{{
                  errorCourseCreate.scheduled
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
                  <input type="number" class="form-control an-select" placeholder="Giá khóa..." v-model="coursePrice" />
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
                  <textarea type="number" class="form-control an-select" placeholder="Mô tả..."
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
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
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
  <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Sửa khóa học</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Mã khóa học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Mã khóa học..."
                    v-model="detailCourse.courseCode" disabled />
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
                  <input type="text" class="form-control an-select" placeholder="Tên khóa học..."
                    v-model="detailCourse.courseName" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.courseName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Ảnh
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Url Ảnh..."
                    v-model="detailCourse.image" />
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.image
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
                      <select class="form-select" v-model="selectedCourseName" @change="addItemUpdate()">
                        <option v-for="item in getListTeacher" :key="item" :value="item.fullName">
                          {{ item.fullName }}
                        </option>
                      </select>
                    </div>
                    <input type="text" class="form-control an-select" placeholder="Chọn giảng viên"
                      v-model="detailCourse.nameTeacher" @focus="showDropdown" @blur="hideDropdown" />
                  </div>
                </div>
                <span style="color: red">{{
                  errorupdateTeacher.nameTeacher
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
                  <input type="text" class="form-control an-select" placeholder="Dự kiến..."
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
                  <input type="number" class="form-control an-select" placeholder="Giá khóa..."
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
                  <textarea type="number" class="form-control an-select" placeholder="Mô tả..."
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
                  <select class="form-select" aria-label="Default select example" v-model="detailCourse.status">
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
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="updateTeacher(detailCourse.id)">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.modal-content.lg {
  width: 950px;
  margin-left: -200px;
}

* {
  font-family: BaiJamjuree !important;
}

.modal-title {
  font-size: 24px;
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
  background-color: #f0f0f0;
  /* Màu nền của ô */
  border: 1px solid #ccc;
  /* Đường viền */
  border-radius: 10px;
  /* Góc bo */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  /* Đổ bóng */
}

tr {
  height: 54px;
}



img {
  display: inline-block;
  height: auto;
  max-width: 100%;
  border-radius: 15px !important;
}

.item-course {
  border-radius: 15px !important;
  overflow: hidden;
}

.item-info {
  padding: 15px !important;
}

.item-title {
  font-size: 18px;
  font-weight: 600;
  min-height: 50px;
}

.item-price {
  color: #7ebf4f !important;
  font-size: 17px;
  font-weight: 600;
  line-height: 22px;
}

.item-meta {
  border-top: 1px solid rgba(0, 0, 0, .42);
  padding-top: 12px;
  margin-top: 20px;
}

.item-meta-line {
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 8px;
}
</style>
