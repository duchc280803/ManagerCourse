<script setup>
import { ref } from "vue";
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
      `http://localhost:8080/api/v1/subject/subject-course?pageNumber=${pageNumber}&pageSize=${pageSize}&id=${idParam}`
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
const listSubjectAddCourse = ref([]);
const getListSubjectAddCourse = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/subject/subject-add-course?id=${idParam}`
    )
    .then(function (response) {
      listSubjectAddCourse.value = response.data;
    });
};
getListSubjectAddCourse()

const selectedSubjectIds = ref([]);
const addCourse = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm môn học vào khóa học không?",
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
        selectedSubjectIds: selectedSubjectIds.value,
      };
      axios
        .post(
          `http://localhost:8080/api/v1/course/add-course?id=${idParam}`,
          formData
        )
        .then(function (response) {
          listSubjectAddCourse.value.push(response.data);
          getListSubjectName();
        })
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
    <div class="card border" style="margin-bottom: 30px">
      <div class="card-header">
        <div class="card-title">
          <h3>Môn học</h3>
        </div>
      </div>
      <div class="card-body" style="height: 630px">
        <div class="row search_table">
          <div class="col-4">
          </div>
          <div class="col-lg-4">
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
                <th style="width: 150px">Buổi học</th>
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
                <td>{{ s.curriculumContent }}</td>
                <td>{{ s.learningMode == 1 ? "Online" : "Offline" }}</td>
                <td style="width: 120px">
                  {{ s.numberOfSessions }}
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
      <div class="modal-content lg" style="width: 600px; margin-left: -60px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm môn học vào khóa học</h5>
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
                  <th>Tên môn</th>
                  <th style="width: 100px">Hành động</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  style="text-align: center" v-for="(sc, index) in listSubjectAddCourse">
                  <td>{{ index + 1 }}</td>
                  <td>{{ sc.subjectName }}</td>
                  <td>
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="flexCheckDefault" v-model="selectedSubjectIds" :value="sc.id"/>
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
            @click="addCourse()">
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
