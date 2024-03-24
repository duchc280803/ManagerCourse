<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

const token = window.localStorage.getItem("token");
const selectedClassName = ref(null);
const listClass = ref([]);
const fetchClassData = async () => {
  try {
    const config = {
      headers: {
        Authorization: "Bearer " + token,
      },
    };
    const response = await axios.get(
      `http://localhost:8080/api/v1/class/findAllClassNameOfStudent`,
      config
    );
    listClass.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassData();

var pageNumber = 0;
var pageSize = 6;
const listScheduleByCourse = ref([]);
const fetchScheduleData = async () => {
  if (selectedClassName.value) {
    try {
      const config = {
        headers: {
          Authorization: "Bearer " + token,
        },
      };
      const response = await axios.get(
        `http://localhost:8080/api/v1/schedule/schedule-by-course?id=${selectedClassName.value.id}&pageNumber=${pageNumber}&pageSize=${pageSize}`,
        config
      );
      listScheduleByCourse.value = response.data;
    } catch (error) {
      console.error("Error fetching class data:", error);
    }
  }
};

const nextPage = function () {
  pageNumber++;
  fetchScheduleData();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchScheduleData();
  }
};
watch(selectedClassName, () => {
  fetchScheduleData();
});
</script>
<template>
  <div
    class="card"
    style="
      width: 1192px;
      height: 585px;
      margin-left: 30px;
      border-radius: 10px;
      margin-bottom: 30px;
    ">
    <div class="card-header">
      <div class="card-title">
        <h3>Lịch học</h3>
      </div>
    </div>
    <div class="row search_table">
      <div class="col-4">
        <div
          class="input-group-prepend position-relative"
          style="margin-left: 20px; margin-top: 20px"></div>
      </div>
      <div class="col-lg-4"></div>
      <div class="col-2"></div>
      <div class="col-2"></div>
    </div>
    <div class="card-body">
      <div class="row search_table">
        <div class="col-4">
          <div class="input-group-prepend position-relative">
            <select
              class="form-select"
              aria-label="Default select example"
              v-model="selectedClassName">
              <option value="" disabled>Chọn lớp</option>
              <option :value="cl" :key="index" v-for="(cl, index) in listClass">
                {{ cl.className }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-3"></div>
        <div class="col-3"></div>
        <div class="col-2">
          <button
            data-bs-toggle="modal"
            data-bs-target="#schedule-add"
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
              <th>Tên môn</th>
              <th>Tên phòng</th>
              <th>Tầng</th>
              <th>Thứ</th>
              <th>Ngày học</th>
              <th>Thời gian học</th>
            </tr>
          </thead>
          <tbody>
            <tr
              style="text-align: center"
              v-for="(sc, index) in listScheduleByCourse">
              <td>{{ index + 1 + pageNumber * pageSize }}</td>
              <td>{{ sc.subjectName }}</td>
              <td>{{ sc.classRoomName }}</td>
              <td>{{ sc.floor }}</td>
              <td>Thứ {{ sc.dateLean ? new Date(sc.dateLean).getDay() + 1 : '' }}</td>
              <td>
                {{ sc.dateLean }}
              </td>
              <td>{{ sc.timeStart }} - {{ sc.timeEnd }}</td>
            </tr>
          </tbody>
        </table>
      </section>
      <nav aria-label="Page navigation example" style="padding-left: 985px">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link" @click="previousPage()">Previous</button>
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
</template>
<style scoped>
.pap {
  padding: 15px;
}
table {
  width: 100%;
}
.section-table {
  max-height: calc(89% - 1.6rem);
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: auto;
  overflow: overlay;
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
* {
  font-family: BaiJamjuree !important;
}
</style>
