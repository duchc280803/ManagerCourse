<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import Swal from "sweetalert2";
let role = window.localStorage.getItem("role");
const route = useRoute();
const idParam = route.params.id;

const showRouteContent = ref(false);
const showRouteContent3 = ref(false);
const showRouteContent4 = ref(false);
const showRouteContent5 = ref(false);
const listSubject = ref([]);
const pageNumber = ref(0);
const pageSize = ref(6);

const toggleRouteContent = () => {
  showRouteContent.value = !showRouteContent.value;
  if (showRouteContent.value) {
    getListSubjectName(1);
  }
}

const toggleRouteContent2 = () => {
  showRouteContent3.value = !showRouteContent3.value;
  if (showRouteContent3.value) {
    getListSubjectName(2);
  }
}

const toggleRouteContent3 = () => {
  showRouteContent4.value = !showRouteContent4.value;
  if (showRouteContent4.value) {
    getListSubjectName(3);
  }
}

const toggleRouteContent4 = () => {
  showRouteContent5.value = !showRouteContent5.value;
  if (showRouteContent5.value) {
    getListSubjectName(4);
  }
}

const getListSubjectName = async (classify) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/subject/subject-course`, {
      params: {
        pageNumber: pageNumber.value,
        pageSize: pageSize.value,
        id: idParam,
        classify: classify,
      },
    });
    listSubject.value = response.data;
  } catch (error) {
    console.error('Error fetching subjects:', error);
    // Handle error
  }
};

const nextPage = () => {
  pageNumber.value++;
  getListSubjectName();
};

const previousPage = () => {
  if (pageNumber.value > 0) {
    pageNumber.value--;
    getListSubjectName();
  }
};

const listSubjectAddCourse = ref([]);
const getListSubjectAddCourse = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/subject/subject-add-course?id=${idParam}`);
    listSubjectAddCourse.value = response.data;
  } catch (error) {
    console.error('Error fetching subjects to add course:', error);
    // Handle error
  }
};
getListSubjectAddCourse();

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
      try {
        const response = await axios.post(`http://localhost:8080/api/v1/course/add-course?id=${idParam}`, formData);
        listSubjectAddCourse.value.push(response.data);
        getListSubjectName();
      } catch (error) {
        console.error('Error adding course:', error);
        // Handle error
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
  <div class="container">
    <button v-show="role == 'ADMIN'" data-bs-toggle="modal" data-bs-target="#subject-add" style="
                text-decoration: none;
                background-color: #fc6736;
                border-radius: 5px;
                color: black;
                margin-top: 5px;
                margin-bottom: 10px;
                margin-left: 1100px;
                font-weight: 600;
              ">
      Thêm mới
    </button>
    <div class="card list-route" @click="toggleRouteContent()">
      <div class=" item-route box-shadow pa-haft">
        <div class="d-flex justify-between item-route-title">
          <b>Session 01: Môn cơ bản</b>
          <span class="ml-half">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="lucide lucide-chevron-down">
              <path d="m6 9 6 6 6-6" />
            </svg>
          </span>
        </div>
        <div class="route-content pl-medium mt-half" v-if="showRouteContent">
          <div class="list-lesson">
            <div class="item-lesson">
              <div class="item-lesson-title d-flex justify-between" v-for="(s, index) in listSubject" :key="s.id">
                <span>{{ s.subjectName }}</span>
                <span class="ml-half"></span>
              </div>
              <ul class="list-contents pl-medium mt-half">
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="card list-route" @click="toggleRouteContent2()">
      <div class=" item-route box-shadow pa-haft">
        <div class="d-flex justify-between item-route-title">
          <b>Session 02: Môn data</b>
          <span class="ml-half">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="lucide lucide-chevron-down">
              <path d="m6 9 6 6 6-6" />
            </svg>
          </span>
        </div>
        <div class="route-content pl-medium mt-half" v-if="showRouteContent3">
          <div class="list-lesson">
            <div class="item-lesson">
              <div class="item-lesson-title d-flex justify-between" v-for="(s, index) in listSubject" :key="s.id">
                <span>{{ s.subjectName }}</span>
                <span class="ml-half"></span>
              </div>
              <ul class="list-contents pl-medium mt-half">
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="card list-route" @click="toggleRouteContent3()">
      <div class=" item-route box-shadow pa-haft">
        <div class="d-flex justify-between item-route-title">
          <b>Session 03: Môn cấu trúc dữ liệu</b>
          <span class="ml-half">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="lucide lucide-chevron-down">
              <path d="m6 9 6 6 6-6" />
            </svg>
          </span>
        </div>
        <div class="route-content pl-medium mt-half" v-if="showRouteContent4">
          <div class="list-lesson">
            <div class="item-lesson">
              <div class="item-lesson-title d-flex justify-between" v-for="(s, index) in listSubject" :key="s.id">
                <span>{{ s.subjectName }}</span>
                <span class="ml-half"></span>
              </div>
              <ul class="list-contents pl-medium mt-half">
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="card list-route" @click="toggleRouteContent4()" style="margin-bottom: 260px;">
      <div class=" item-route box-shadow pa-haft">
        <div class="d-flex justify-between item-route-title">
          <b>Session 04: Môn Chuyên sâu</b>
          <span class="ml-half">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="lucide lucide-chevron-down">
              <path d="m6 9 6 6 6-6" />
            </svg>
          </span>
        </div>
        <div class="route-content pl-medium mt-half" v-if="showRouteContent5">
          <div class="list-lesson">
            <div class="item-lesson">
              <div class="item-lesson-title d-flex justify-between" v-for="(s, index) in listSubject" :key="s.id">
                <span>{{ s.subjectName }}</span>
                <span class="ml-half"></span>
              </div>
              <ul class="list-contents pl-medium mt-half">
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal add-->
  <div class="modal fade" id="subject-add" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 600px; margin-left: -60px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm môn học vào khóa học</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <section class="section-table">
            <table>
              <thead>
                <tr style="
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
                <tr style="text-align: center" v-for="(sc, index) in listSubjectAddCourse">
                  <td>{{ index + 1 }}</td>
                  <td>{{ sc.subjectName }}</td>
                  <td>
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" v-model="selectedSubjectIds"
                      :value="sc.id" />
                  </td>
                </tr>
              </tbody>
            </table>
          </section>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="addCourse()">
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

.pap {
  padding-top: 20px;
}

.box-shadow {
  box-shadow: 0 3px 6px -4px rgba(0, 0, 0, .16), 0 3px 6px rgba(0, 0, 0, .23);
}

.pa-half {
  padding: 15px !important;
}

.d-flex {
  display: flex;
}

.justify-between {
  justify-content: space-between;
}

b,
strong {
  font-weight: 700;
}

.card.item-route.box-shadow.pa-haft {
  height: 56px;
}

.d-flex.justify-between.item-route-title {
  padding: 15px;
}

.pl-medium {
  padding-left: 30px !important;
}

.mt-half {
  margin-top: 15px;
}

.card.list-route {
  cursor: pointer;
}
</style>
