<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

/**
 * Get list students
 */
const listStudent = ref([]);
var pageNumber = 0;
var pageSize = 6;
const fetchStudentData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/student/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
    listStudent.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchStudentData();
const nextPage = function () {
  pageNumber++;
  fetchStudentData();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchStudentData();
  }
};

/**
 * Search for students
 */
const search = ref("");
const searchStudent = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/student/search?studentName=${search.value}`
    )
    .then(function (response) {
      listStudent.value = response.data;
    });
};
watch(search, () => {
    searchStudent();
});
/**
 * Get name course
 */
const getListCourseName = ref([]);
const findByCourseName = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/course-name`)
    .then(function (response) {
      getListCourseName.value = response.data;
    });
};
findByCourseName();

/**
 * Create a new student
 */
const fullName = ref("");
const gender = ref("");
const dateOfBirth = ref("");
const phoneNumber = ref("");
const address = ref("");
const email = ref("");
const courseName = ref("");
const startDateLearn = ref("");
const username = ref("");
const password = ref("");
const error = ref({});

const resetForm = () => {
  fullName.value = "";
  gender.value = "";
  dateOfBirth.value = "";
  phoneNumber.value = "";
  address.value = "";
  email.value = "";
  courseName.value = "";
  startDateLearn.value = "";
  username.value = "";
  password.value = "";
  error.value = {};
};
const createStudent = async () => {
  try {
    const newStudent = {
      fullName: fullName.value,
      gender: gender.value,
      dateOfBirth: dateOfBirth.value,
      phoneNumber: phoneNumber.value,
      address: address.value,
      email: email.value,
      courseName: courseName.value,
      startDateLearn: startDateLearn.value,
      username: username.value,
      password: password.value,
    };
    const response = await axios.post(`http://localhost:8080/api/v1/student/create`, newStudent);
    listStudent.value.push(response.data);
    fetchStudentData();
    resetForm(); // Reset form data and error messages after successful create operation
    closeModal("#student-add"); // Close modal
    // Show success message
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Học viên đã được thêm mới thành công",
      timer: 1500,
      showConfirmButton: false
    });
  } catch (error) {
    // Set error messages to display in UI
    error.value = error.response.data;
  }
};

const closeModal = (modalId) => {
    const modal = document.querySelector(modalId);
    const backdrop = document.querySelector(".modal-backdrop");
    if (modal && backdrop) {
        modal.classList.remove("show");
        modal.style.display = "none";
        backdrop.remove();
    }
};

/**
 * Refresh the student
 */
function refresh() {
  search.value = "";
  fetchStudentData();
}

/**
 * Get details of a subject
 */
const studentUpdate = ref({});
const studentDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/student/detail?id=${id}`)
    .then(function (response) {
      studentUpdate.value = response.data;
    });
};

/**
 * Update the subject
 */
const errorUpdateStudent = ref({});
const updateStudent = async (id) => {
  Swal.fire({
    title: "Bạn có muốn thêm mới học viên không?",
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
      try {
        const response = await axios.put(
          `http://localhost:8080/api/v1/student/update?id=${id}`,
          studentUpdate.value
        );
        fetchStudentData();
        closeModal("#student-update"); // Close modal
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Update thành công",
          timer: 1500,
          customClass: {
            popup: "small-popup",
          },
          showConfirmButton: false,
        });
      } catch (error) {
        errorUpdateStudent.value = error.response.data;
      }
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
          <h3>Học viên</h3>
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
            <button @click="refresh()" style="
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
            <button data-bs-toggle="modal" data-bs-target="#student-add" style="
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
              <tr style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
                <th style="width: 30px">STT</th>
                <th>Mã học viên</th>
                <th>Họ tên</th>
                <th>Khóa học</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(s, index) in listStudent" :key="s.id" style="text-align: center">
                <td style="text-align: center">
                  {{ index + 1 + pageNumber * pageSize }}
                </td>
                <td>{{ s.codeName }}</td>
                <td>{{ s.fullName }}</td>
                <td>{{ s.courseName }}</td>
                <td>{{ s.phoneNumber }}</td>
                <td>{{ s.email }}</td>
                <td>
                  <span style="
                      background-color: #9bcf53;
                      border-radius: 5px;
                      color: darkgreen;
                    ">{{ s.status == 1 ? "Đang học" : "Nghỉ học" }}</span>
                </td>
                <td>
                  <button data-bs-toggle="modal" data-bs-target="#student-update" @click="studentDetail(s.id)" style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Sửa
                  </button>
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
  <div class="modal fade" id="student-add" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Thêm mới học viên</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Họ và tên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Họ và tên..." v-model="fullName" />
                </div>
                <span style="color: red">{{
                error.fullName
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giới tính
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select class="form-select" aria-label="Default select example" v-model="gender">
                    <option value="" disabled>Chọn giới tính</option>
                    <option :value="true">Nam</option>
                    <option :value="false">Nữ</option>
                  </select>
                </div>
                <span style="color: red">{{ error.gender }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Năm sinh
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="date" class="form-control an-select" v-model="dateOfBirth" />
                </div>
                <span style="color: red">{{
                error.dateOfBirth
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Học khóa
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select class="form-select" aria-label="Default select example" v-model="courseName">
                    <option disabled value="">Khóa học</option>
                    <option v-for="(cs, index) in getListCourseName" :key="cs" :value="cs.courseName">
                      {{ cs.courseName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{
                error.courseName
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Địa chỉ
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Địa chỉ..." v-model="address" />
                </div>
                <span style="color: red">{{
                error.address
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số điện thoại
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Số điện thoại..."
                    v-model="phoneNumber" />
                </div>
                <span style="color: red">{{
                error.phoneNumber
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Email
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Email..." v-model="email" />
                </div>
                <span style="color: red">{{ error.email }}</span>
                <span style="color: red">{{
                error.messageEmail
              }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="createStudent()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div class="modal fade" id="student-update" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Cập nhập học viên</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Họ và tên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Họ và tên..."
                    v-model="studentUpdate.fullName" />
                </div>
                <span style="color: red">{{
                errorUpdateStudent.fullName
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Giới tính
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select class="form-select" aria-label="Default select example" v-model="studentUpdate.gender">
                    <option value="" disabled>Chọn giới tính</option>
                    <option :value="true">Nam</option>
                    <option :value="false">Nữ</option>
                  </select>
                </div>
                <span style="color: red">{{ errorUpdateStudent.gender }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Năm sinh
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="date" class="form-control an-select" v-model="studentUpdate.yearOfBirth" />
                </div>
                <span style="color: red">{{
                errorUpdateStudent.dateOfBirth
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Học khóa
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select class="form-select" aria-label="Default select example" v-model="studentUpdate.courseName">
                    <option disabled value="">Khóa học</option>
                    <option v-for="(cs, index) in getListCourseName" :key="cs" :value="cs.courseName">
                      {{ cs.courseName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{
                errorUpdateStudent.courseName
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Địa chỉ
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Địa chỉ..."
                    v-model="studentUpdate.address" />
                </div>
                <span style="color: red">{{ errorUpdateStudent.address }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số điện thoại
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Số điện thoại..."
                    v-model="studentUpdate.phoneNumber" />
                </div>
                <span style="color: red">{{
                errorUpdateStudent.phoneNumber
              }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Email
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Email..."
                    v-model="studentUpdate.email" />
                </div>
                <span style="color: red">{{ error.email }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="updateStudent(studentUpdate.id)">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.section-table {

  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: auto;
  overflow: overlay;
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

.modal-content.lg {
  width: 950px;
  margin-left: -200px;
}

.pap {
  padding-top: 20px;
}

td {
  padding-left: 5px;
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
</style>
