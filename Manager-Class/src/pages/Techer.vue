<script setup>
import { ref, watch } from "vue";
import axios from "axios";

/**
 * Add list course to selected
 */
const inputValue = ref([]);
const selectedCourseName = ref("");
const dropdownVisible = ref(false);
const showDropdown = () => {
  dropdownVisible.value = true;
};
const addItem = () => {
  // Thêm giá trị mới vào mảng inputValue
  inputValue.value.push(selectedCourseName.value.trim());
  dropdownVisible.value = false;
};

/**
 * Get list teacher
 */
const listTeacher = ref([]);
var pageNumber = 0;
var pageSize = 6;
const getListteacher = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/teacher/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
    )
    .then(function (response) {
      listTeacher.value = response.data;
    });
};
getListteacher();
const nextPage = function () {
  pageNumber++;
  getListteacher();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    getListteacher();
  }
};

/**
 * Get list course
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
 * Add a new teacher
 */
const fullName = ref("");
const gender = ref("");
const age = ref("");
const dateOfBirth = ref("");
const phoneNumber = ref("");
const address = ref("");
const email = ref("");
const createTeacher = async () => {
  const newTeacher = {
    fullName: fullName.value,
    gender: gender.value,
    age: age.value,
    dateOfBirth: dateOfBirth.value,
    phoneNumber: phoneNumber.value,
    address: address.value,
    email: email.value,
    inputValue: inputValue.value,
  };
  axios
    .post(`http://localhost:8080/api/v1/teacher/create`, newTeacher)
    .then(function (response) {
      listTeacher.value.push(response.data);
      getListteacher();
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
    });
};

/**
 * View add course to teacher new
 */
const updateTeacherResponse = ref({});
const showDetetailTeacher = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/teacher/detail?id=${id}`)
    .then(function (response) {
      updateTeacherResponse.value = response.data;
    });
};
const addItemUpdate = () => {
  updateTeacherResponse.value.inputValue.push(selectedCourseName.value.trim());
  dropdownVisible.value = false;
};

/**
 * Search teacher
 */
const search = ref("");
const searchTeacher = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/teacher/search?teacherName=${search.value}`
    )
    .then(function (response) {
      listTeacher.value = response.data;
    });
};
watch(search, () => {
  searchTeacher();
});

/**
 * Refresh
 */
function refresh() {
  getListteacher();
  search.value = "";
}

/**
 * Update teacher
 */
const updateTeacher = async (id) => {
  await axios
    .put(
      `http://localhost:8080/api/v1/teacher/update?id=${id}`,
      updateTeacherResponse.value
    )
    .then(function (response) {
      getListteacher();
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
    });
};
</script>
<template>
  <div class="container teacher">
    <div class="card border">
      <div class="card-header">
        <div class="card-title">
          <h3>Giảng viên</h3>
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
              @click="refresh()"
              style="
                text-decoration: none;
                background-color: #fc6736;
                border-radius: 5px;
                color: black;
                margin-left: 25px;
                margin-top: 5px;
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
                margin-top: 5px;
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
                <th>Mã giảng viên</th>
                <th>Họ tên</th>
                <th>Số khóa</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(t, index) in listTeacher"
                :key="t.id"
                style="text-align: center">
                <td>{{ index + 1 + pageNumber * pageSize }}</td>
                <td>{{ t.codeName }}</td>
                <td>{{ t.fullName }}</td>
                <td>
                  {{ t.courseOfClassList.length }}
                </td>
                <td>{{ t.phoneNumber }}</td>
                <td>{{ t.email }}</td>
                <td>
                  <span
                    :style="{
                      'background-color': t.status == 1 ? '#9bcf53' : 'red',
                      'border-radius': '5px',
                      color: t.status == 1 ? 'darkgreen' : 'white',
                    }">
                    {{ t.status == 1 ? "Hoạt động" : "Ngừng hoạt động" }}
                  </span>
                </td>
                <td>
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal1"
                    @click="showDetetailTeacher(t.id)"
                    style="
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
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Thêm mới giảng viên</h5>
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
                  Họ và tên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Họ và tên..."
                    v-model="fullName" />
                </div>
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
                  <select
                    v-model="gender"
                    class="form-select"
                    aria-label="Default select example">
                    <option disabled value="">Chọn giới tính</option>
                    <option :value="true">Nam</option>
                    <option :value="false">Nữ</option>
                  </select>
                </div>
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
                  <input
                    type="date"
                    class="form-control an-select"
                    v-model="dateOfBirth" />
                </div>
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
                        @change="addItem()">
                        <option
                          v-for="item in getListCourseName"
                          :key="item"
                          :value="item.courseName">
                          {{ item.courseName }}
                        </option>
                      </select>
                    </div>
                    <input
                      type="text"
                      class="form-control an-select"
                      placeholder="Chọn khóa học"
                      v-model="inputValue"
                      @focus="showDropdown"
                      @blur="hideDropdown" />
                  </div>
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Số điện thoại..."
                    v-model="phoneNumber" />
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Email..."
                    v-model="email" />
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Địa chỉ..."
                    v-model="address" />
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
            @click="createTeacher()">
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
          <h5 class="modal-title">Sửa giảng viên</h5>
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
                  Họ và tên
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Họ và tên..."
                    v-model="updateTeacherResponse.fullName" />
                </div>
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
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="updateTeacherResponse.gender">
                    <option :value="true">Nam</option>
                    <option :value="false">Nữ</option>
                  </select>
                </div>
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
                  <input
                    type="date"
                    class="form-control an-select"
                    v-model="updateTeacherResponse.yearOfBirth" />
                </div>
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
                          v-for="item in getListCourseName"
                          :key="item"
                          :value="item.courseName">
                          {{ item.courseName }}
                        </option>
                      </select>
                    </div>
                    <input
                      type="text"
                      class="form-control an-select"
                      placeholder="Chọn khóa học"
                      v-model="updateTeacherResponse.inputValue"
                      @focus="showDropdown"
                      @blur="hideDropdown" />
                  </div>
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Số điện thoại..."
                    v-model="updateTeacherResponse.phoneNumber" />
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Email..."
                    v-model="updateTeacherResponse.email" />
                </div>
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
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Địa chỉ..."
                    v-model="updateTeacherResponse.address" />
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
            @click="updateTeacher(updateTeacherResponse.id)">
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
.pap {
  padding-top: 20px;
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
input.form-control.search {
  width: 410px;
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

.modal-content.lg {
  width: 950px;
  margin-left: -200px;
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
td {
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
