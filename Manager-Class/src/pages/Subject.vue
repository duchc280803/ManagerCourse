<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import Swal from "sweetalert2";

var role = window.localStorage.getItem("role");
const route = useRoute();
const idParam = route.params.id;

/**
 * Get list subject
 */
const listSubject = ref([]);
var pageNumber = 0;
var pageSize = 6;
const getListSubjectName = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/subject/show?pageNumber=${pageNumber}&pageSize=${pageSize}&id=${idParam}`
    )
    .then(function (response) {
      listSubject.value = response.data;
    });
};
getListSubjectName();
const nextPage = function () {
  pageNumber++;
  getListSubjectName();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    getListSubjectName();
  }
};

/**
 * Create a new subject
 */
const subjectCode = ref("SUBJECT_CODE" + new Date().getTime());
const subjectName = ref("");
const curriculumContent = ref("");
const learningMode = ref("");
const studyTimeStart = ref("");
const studyTimeEnd = ref("");
const classify = ref("");
const errorCreateSubject = ref({});
const createSubject = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới môn học không?",
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
      const formDataSubject = {
        subjectCode: subjectCode.value,
        subjectName: subjectName.value,
        curriculumContent: curriculumContent.value,
        learningMode: learningMode.value,
        studyTimeStart: studyTimeStart.value,
        studyTimeEnd: studyTimeEnd.value,
        classify: classify.value,
      };
      axios
        .post(`http://localhost:8080/api/v1/subject/create`, formDataSubject)
        .then(function (response) {
          listSubject.value.push(response.data);
          getListSubjectName();
          let modal = document.getElementById("subject-add");
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
          errorCreateSubject.value = error.response.data;
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
 * Get details of a subject
 */
const subjectUpdate = ref({});
const subjectDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/subject/detail?id=${id}`)
    .then(function (response) {
      subjectUpdate.value = response.data;
    });
};

/**
 * Update the subject
 */
const errorUpdateSubject = ref({});
const updateSubject = async (id) => {
  Swal.fire({
    title: "Bạn có muốn sửa môn học không?",
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
          `http://localhost:8080/api/v1/subject/update/${id}`,
          subjectUpdate.value
        )
        .then(function (response) {
          getListSubjectName();
        })
        .catch(function (error) {
          errorUpdateSubject.value = error.response.data;
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
 * Search for a list subject
 */
const search = ref("");
const searchSubject = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/subject/search?subjectName=${search.value}`
    )
    .then(function (response) {
      listSubject.value = response.data;
    });
};
watch(search, () => {
  searchSubject();
});

/**
 * refresh the search
 */
function refreshSubject() {
  search.value = "";
  getListSubjectName();
}

</script>
<template>
  <div class="container student">
    <div class="card border" style="margin-bottom: 30px">
      <div class="card-header">
        <div class="card-title">
          <h3>Môn học</h3>
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
              v-show="role == 'ADMIN'"
              @click="refreshSubject()"
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
              v-show="role == 'ADMIN'"
              data-bs-toggle="modal"
              data-bs-target="#subject-add"
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
                <th>Mã môn</th>
                <th style="width: 80px">Tên môn</th>
                <th style="width: 100px">Loại môn</th>
                <th>Nội dung</th>
                <th style="width: 80px">Hình thức</th>
                <th style="width: 150px">Thời gian</th>
                <th style="width: 120px" v-show="role == 'ADMIN'">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(s, index) in listSubject"
                :key="s.id"
                style="text-align: center">
                <td>{{ index + 1 + pageNumber * pageSize }}</td>
                <td>{{ s.subjectCode }}</td>
                <td>{{ s.subjectName }}</td>
                <td>
                  <span
                    :style="{
                      'background-color':
                        s.classify == 1
                          ? '#9bcf53'
                          : s.classify == 2
                          ? '#00008B' // Đổi từ '#FFC700' thành '#00008B' vì bạn muốn màu xanh đậm
                          : '#59D5E0',
                      'border-radius': '5px',
                      color: s.classify == 1 ? 'darkgreen' : 'white',
                    }">
                    {{
                      s.classify == 1
                        ? "Môn nền tảng"
                        : s.classify == 2
                        ? "Môn chuyên sâu"
                        : "Môn bổ trợ"
                    }}
                  </span>
                </td>
                <td>{{ s.curriculumContent }}</td>
                <td>{{ s.learningMode == 1 ? "Online" : "Offline" }}</td>
                <td style="width: 120px">
                  {{ s.studyTimeStart }} | {{ s.studyTimeEnd }}
                </td>
                <td v-show="role == 'ADMIN'">
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#subject-update"
                    @click="subjectDetail(s.id)"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Sửa
                  </button>
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#point"
                    @click="getListStudyPoint(s.id)"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                      margin-left: 5px;
                    ">
                    Đầu điểm
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
    id="subject-add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm môn mới</h5>
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
                  Mã môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã môn..."
                    v-model="subjectCode"
                    disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên môn..."
                    v-model="subjectName" />
                </div>
                <span style="color: red">{{
                  errorCreateSubject.subjectName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Nội dung
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea
                    type="number"
                    class="form-control an-select"
                    placeholder="Nội dung..."
                    v-model="curriculumContent"></textarea>
                </div>
                <span style="color: red">{{
                  errorCreateSubject.curriculumContent
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Hình thức học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="learningMode">
                    <option value="" disabled>Chọn hình thức học</option>
                    <option :value="1">Online</option>
                    <option :value="2">Offline</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorCreateSubject.learningMode
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Loại môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classify">
                    <option value="" disabled>Chọn loại môn</option>
                    <option :value="1">Môn nền tảng</option>
                    <option :value="2">Môn chuyên sâu</option>
                    <option :value="3">Môn bổ trợ</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorCreateSubject.classify
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian bắt đầu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <input
                  type="date"
                  class="form-control an-select"
                  v-model="studyTimeStart" />
                <span style="color: red">{{
                  errorCreateSubject.studyTimeStart
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian kết thúc
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <input
                  type="date"
                  class="form-control an-select"
                  v-model="studyTimeEnd" />
                <span style="color: red">{{
                  errorCreateSubject.studyTimeEnd
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
          <button
            type="button"
            class="btn btn-primary"
            @click="createSubject()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="subject-update"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Update môn</h5>
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
                  Mã môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã môn..."
                    v-model="subjectUpdate.subjectCode"
                    disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên môn..."
                    v-model="subjectUpdate.subjectName" />
                </div>
                <span style="color: red">{{
                  errorUpdateSubject.subjectName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Nội dung
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea
                    type="number"
                    class="form-control an-select"
                    placeholder="Nội dung..."
                    v-model="subjectUpdate.curriculumContent"></textarea>
                </div>
                <span style="color: red">{{
                  errorUpdateSubject.curriculumContent
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Hình thức học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="subjectUpdate.learningMode">
                    <option value="" disabled>Chọn hình thức học</option>
                    <option :value="1">Online</option>
                    <option :value="2">Offline</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorUpdateSubject.learningMode
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Loại môn
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="subjectUpdate.classify">
                    <option value="" disabled>Chọn loại môn</option>
                    <option :value="1">Môn nền tảng</option>
                    <option :value="2">Môn chuyên sâu</option>
                    <option :value="3">Môn bổ trợ</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorUpdateSubject.classify
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian bắt đầu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <input
                  type="date"
                  class="form-control an-select"
                  v-model="subjectUpdate.studyTimeStart" />
                <span style="color: red">{{
                  errorUpdateSubject.studyTimeStart
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian kết thúc
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <input
                  type="date"
                  class="form-control an-select"
                  v-model="subjectUpdate.studyTimeEnd" />
                <span style="color: red">{{
                  errorUpdateSubject.studyTimeEnd
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
          <button
            type="button"
            class="btn btn-primary"
            @click="updateSubject(subjectUpdate.id)">
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
.card.subject {
  border-radius: 40px;
  background-position: center;
  background-size: cover;
  overflow: hidden;
  background-color: burlywood;
}
span.subject-code {
  margin: 5px;
  font-size: 16px;
  font-weight: 600;
}
span.subject-name {
  margin: 5px;
  font-size: 16px;
  font-weight: 600;
}
a.router-link-active {
  text-decoration: none;
  color: black;
}
tr {
  height: 80px;
}
table {
  width: 100%;
}
.section-table {
  max-height: calc(89% - 1.6rem);
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: hidden;
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
.pap {
  padding-top: 20px;
}
</style>
