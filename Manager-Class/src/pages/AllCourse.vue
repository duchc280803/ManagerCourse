<script setup>
import { ref, watch } from "vue";
import axios from "axios";
const token = window.localStorage.getItem("token");

/**
 * Get list course
 */
 const listCourse = ref([]);
var pageNumber = 0;
var pageSize = 5;
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


const listCourseNames = ref([]);

const fetchData = async () => {
  const config = {
    headers: {
      Authorization: "Bearer " + token,
    },
  };
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/course/course-student`,
      config
    );
    listCourseNames.value = response.data;
  } catch (error) {
    console.error("Error fetching course names:", error);
  }
};
fetchData();
const isEnrolled = (courseName) => {
  return listCourseNames.value.some(
    (course) => course.courseName === courseName
  );
};
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
        <h3>Tất cả khóa học</h3>
      </div>
    </div>
    <div class="card-body">
      <section class="section-table">
        <table>
          <thead>
            <tr style="background-color: #d5d1defe; text-align: center">
              <th>STT</th>
              <th>Mã khóa học</th>
              <th>Tên khóa học</th>
              <th style="width: 100px;">Dự kiến</th>
              <th>Giá khóa</th>
              <th style="width: 130px">Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr
              style="text-align: center"
              v-for="(cs, index) in listCourse"
              :key="cs.courseName">
              <td>{{ index + 1 + pageNumber * pageSize }}</td>
              <td>{{ cs.courseCode }}</td>
              <td>{{ cs.courseName }}</td>
              <td>{{ cs.scheduled }}</td>
              <td>{{ cs.coursePrice.toLocaleString('vi-VN') }}đ</td>
              <td>
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
                <button
                  v-if="isEnrolled(cs.courseName)"
                  style="
                    text-decoration: none;
                    background-color: chartreuse;
                    border-radius: 5px;
                    color: black;
                    font-weight: 600;
                    margin-left: 5px;
                  ">
                  Đang học
                </button>
                <button
                  v-else
                  style="
                    text-decoration: none;
                    background-color: #fc6736;
                    border-radius: 5px;
                    color: black;
                    font-weight: 600;
                    margin-left: 5px;
                  ">
                  Đăng ký
                </button>
              </td>
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
tbody tr {
  height: 70px;
}
* {
  font-family: BaiJamjuree !important;
}
</style>
