<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import Swal from "sweetalert2";

const route = useRoute();
const listStudentInformation = ref([]);
var idParam = route.params.id;
var idParamCourse = route.params.courseName;
var pageNumber = 0;
var pageSize = 5;
const fetchStudentInformation = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class/student-information?pageNumber=${pageNumber}&pageSize=${pageSize}&id=${idParam}`
    );
    listStudentInformation.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchStudentInformation();
const nextPage = function () {
  pageNumber++;
  fetchStudentInformation();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchStudentInformation();
  }
};

const teacherInformation = ref({});
const fetchTeacherInformation = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class/teacher-information?id=${idParam}`
    );
    teacherInformation.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchTeacherInformation();

const studentCheckBox = ref([]);
const fetchStudentCheckBox = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/student/show-cb-student?courseName=${idParamCourse}`
    );
    studentCheckBox.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchStudentCheckBox();

const selectedStudentIds = ref([]);
const errorStudentAddClassRequest = ref({});
const addStudentToClass = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm học viên vào lớp không?",
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
        selectedStudentIds: selectedStudentIds.value,
      };
      axios
        .put(
          `http://localhost:8080/api/v1/class/add-student?id=${idParam}`,
          formData
        )
        .then(function (response) {
          listStudentInformation.value.push(response.data);
          fetchStudentInformation();
          let modal = document.getElementById("classinfor-add");
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
          errorStudentAddClassRequest.value = error.response.data;
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

var pageNumber = 0;
var pageSize = 6;
const subjectByClass = ref([]);
const getListSubjectByClass = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/subject/subject-by-class?id=${idParam}&pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
    subjectByClass.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
getListSubjectByClass();
const nextPageSubject = function () {
  pageNumber++;
  getListSubjectByClass();
};

const previousPageSubject = function () {
  if (pageNumber > 0) {
    pageNumber--;
    getListSubjectByClass();
  }
};

const listSchedule = ref([]);
const formatTime = (timestamp) => {
  const date = new Date(timestamp);
  return `${date.getHours()}h:${date.getMinutes()}p:${date.getSeconds()}s`;
};
var pageNumberSchedule = 0;
var pageSizeSchedule = 5;
const selectedSubjectName = ref("");
const fetchScheduleData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/schedule/show?pageNumber=${pageNumberSchedule}&pageSize=${pageSizeSchedule}&id=${idParam}&subjectName=${
        selectedSubjectName.value.subjectName == undefined
          ? ""
          : selectedSubjectName.value.subjectName
      }`
    );
    listSchedule.value = response.data;
    console.log(selectedSubjectName.value.subjectName);
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
const nextPageSchedule = function () {
  pageNumber++;
  fetchScheduleData();
};

const previousPageSchedule = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchScheduleData();
  }
};

const subjectName = ref([]);
const getListsubjectName = async () => {
  const response = await axios.get(
    `http://localhost:8080/api/v1/subject/subject-name?id=${idParam}`
  );
  subjectName.value = response.data;
};
getListsubjectName();
watch(selectedSubjectName, () => {
  fetchScheduleData();
});
</script>
<template>
  <div class="container class">
    <span>
      <span class="ant-breadcrumb-link">
        <RouterLink to="/class">
          <a href="#/" class="router-link-active">Lớp học</a>
        </RouterLink>
      </span>
      <span class="ant-breadcrumb-separator">/</span>
    </span>
    <span>
      <span class="ant-breadcrumb-link"
        ><a class="router-link-active active">Chi tiết lớp học</a></span
      ></span
    >
    <div class="row">
      <div class="col-lg-6">
        <div
          class="card"
          style="height: 575px; margin-bottom: 14px; border-radius: 5px">
          <div class="card-header">
            <div class="card-title">
              <div class="row">
                <div class="col-lg-6"><h3>Thông tin môn học</h3></div>
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
                    <th>Tên môn</th>
                    <th>Giảng viên</th>
                    <th style="width: 110px">Thời gian</th>
                    <th style="width: 100px">Loại môn</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(s, index) in subjectByClass"
                    style="text-align: center">
                    <td>{{ index + 1 + pageNumber * pageSize }}</td>
                    <td>{{ s.subjectName }}</td>
                    <td>{{ teacherInformation.fullName }}</td>
                    <td>{{ s.studyTimeStart }} | {{ s.studyTimeEnd }}</td>
                    <td>
                      <span
                        :style="{
                          'background-color':
                            s.classify == 1
                              ? '#9bcf53'
                              : s.classify == 2
                              ? '#FFC700'
                              : '#59D5E0',
                          'border-radius': '5px',
                          color: s.classify == 1 ? 'darkgreen' : 'white',
                        }">
                        {{
                          s.classify == 1
                            ? "Môn nền tảng"
                            : 2
                            ? "Môn chuyên sâu"
                            : "Môn bổ trợ"
                        }}
                      </span>
                    </td>
                  </tr>
                </tbody>
              </table>
            </section>
            <nav
              aria-label="Page navigation example"
              style="padding-left: 400px">
              <ul class="pagination">
                <li class="page-item">
                  <button class="page-link" @click="previousPageSubject()">
                    Previous
                  </button>
                </li>
                <li class="page-item">
                  <a class="page-link" href="#">{{ pageNumber + 1 }}</a>
                </li>
                <li class="page-item">
                  <button class="page-link" @click="nextPageSubject()">
                    Next
                  </button>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
      <div class="col-lg-6">
        <div
          class="card"
          style="height: 575px; margin-bottom: 14px; border-radius: 5px">
          <div class="card-header">
            <div class="card-title">
              <div class="row">
                <div class="col-lg-6"><h3>Thông tin học viên</h3></div>
                <div class="col-lg-6">
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#classinfor-add"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      margin-top: 5px;
                      font-weight: 600;
                      margin-left: 170px;
                    ">
                    Thêm mới
                  </button>
                </div>
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
                    <th>Họ tên</th>
                    <th>Số điện thoại</th>
                    <th>Email</th>
                    <th style="width: 80px">Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(s, index) in listStudentInformation"
                    style="text-align: center">
                    <td>{{ index + 1 }}</td>
                    <td>{{ s.fullName }}</td>
                    <td>{{ s.phoneNumber }}</td>
                    <td>{{ s.email }}</td>
                    <td>
                      <button
                        data-bs-toggle="modal"
                        data-bs-target="#point"
                        style="
                          text-decoration: none;
                          background-color: #fc6736;
                          border-radius: 5px;
                          color: black;
                          margin-top: 15px;
                          font-weight: 600;
                        ">
                        Xem điểm
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </section>
          </div>
        </div>
      </div>
    </div>
    <div class="card">
      <div class="row">
        <div class="col-6">
          <select
            class="form-select"
            aria-label="Default select example"
            style="margin: 10px" v-model="selectedSubjectName">
            <option value="" disabled>Chọn môn</option>
            <option :value="s" :key="index" v-for="(s, index) in subjectName">
              {{ s.subjectName }}
            </option>
          </select>
        </div>
      </div>
      <section class="section-table" style="margin: 20px">
        <table>
          <thead>
            <tr
              style="
                background-color: #d5d1defe;
                height: 52px;
                text-align: center;
              ">
              <th>STT</th>
              <th>Tên môn</th>
              <th>Tên phòng</th>
              <th>Tầng</th>
              <th>Ngày học</th>
              <th>Thời gian học</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center" v-for="(sc, index) in listSchedule">
              <td>{{ index + 1 + pageNumberSchedule * pageSizeSchedule }}</td>
              <td>{{ sc.subjectName }}</td>
              <td>{{ sc.classRoomName }}</td>
              <td>{{ sc.floor }}</td>
              <td>{{ sc.dateLearn }}</td>
              <td>{{ sc.timeStart }} - {{ sc.timeEnd }}</td>
            </tr>
          </tbody>
        </table>
      </section>
      <nav aria-label="Page navigation example" style="padding-left: 990px">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link" @click="previousPageSchedule()">
              Previous
            </button>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">{{ pageNumberSchedule + 1 }}</a>
          </li>
          <li class="page-item">
            <button class="page-link" @click="nextPageSchedule()">Next</button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
  <!-- Modal point-->
  <div
    class="modal fade"
    id="point"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div
        class="modal-content lg"
        style="width: 950px; height: 650px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thông tin điểm</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
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
                  <th>Họ tên</th>
                  <th>Môn</th>
                  <th>Điểm</th>
                  <th>Ghi chú</th>
                  <th>Trạng thái</th>
                </tr>
              </thead>
              <tbody>
                <tr style="text-align: center">
                  <td>1</td>
                  <td>Hoàng Công Đức</td>
                  <td>Java core</td>
                  <td>10</td>
                  <td>Tốt</td>
                  <td>Passed</td>
                </tr>
              </tbody>
            </table>
          </section>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal add-->
  <div
    class="modal fade"
    id="classinfor-add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 600px; margin-left: -60px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm học viên vào lớp</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <section class="section-table">
            <table>
              <thead>
                <tr
                  style="
                    background-color: #d5d1defe;
                    height: 52px;
                    text-align: center;
                  ">
                  <th>#</th>
                  <th>Mã học viên</th>
                  <th>Tên học viên</th>
                  <th style="width: 100px">Hành động</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  style="text-align: center"
                  v-for="(s, index) in studentCheckBox"
                  :key="s.id">
                  <td>{{ index + 1 }}</td>
                  <td>{{ s.codeName }}</td>
                  <td>{{ s.fullName }}</td>
                  <td>
                    <input
                      class="form-check-input"
                      type="checkbox"
                      :value="s.id"
                      v-model="selectedStudentIds"
                      id="flexCheckDefault" />
                  </td>
                </tr>
              </tbody>
            </table>
          </section>
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
            @click="addStudentToClass()">
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
.information-teacher {
  margin: 10px;
  font-size: 16px;
  font-weight: 600;
}
span.status {
  margin-left: 225px;
  font-size: 16px;
  font-weight: 600;
}
span.medium-score {
  font-size: 16px;
  font-weight: 600;
}
button.btn.btn-primary.action {
  background-color: #00c7f2;
  border-color: #00c7f2;
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
a {
  text-decoration: none;
}
</style>
