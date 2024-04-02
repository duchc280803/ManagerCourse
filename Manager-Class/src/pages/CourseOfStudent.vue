<script setup>
import { ref, watch } from "vue";
import axios from "axios";

const token = window.localStorage.getItem("token");
const listCourseNames = ref([]);
const selectedCourse = ref(null);
const listCourseOfStudent = ref([]);

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

var pageNumber = 0;
var pageSize = 6;
const fetchCourseData = async () => {
  if (selectedCourse.value) {
    const config = {
      headers: {
        Authorization: "Bearer " + token,
      },
    };
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/student/course-of-student?courseName=${selectedCourse.value}&pageNumber=${pageNumber}&pageSize=${pageSize}`,
        config
      );
      listCourseOfStudent.value = response.data;
    } catch (error) {
      console.error("Error fetching class data:", error);
    }
  }
};

const nextPage = function () {
  pageNumber++;
  fetchCourseData();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchCourseData();
  }
};
watch(selectedCourse, () => {
  fetchCourseData();
});

fetchData();
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
        <h3>Khóa học của tôi</h3>
      </div>
    </div>
    <div class="row search_table">
      <div class="col-4">
        <div
          class="input-group-prepend position-relative"
          style="margin-left: 20px; margin-top: 20px">
          <select
            class="form-select"
            aria-label="Default select example"
            v-model="selectedCourse">
            <option disabled value="">Chọn khóa học</option>
            <option
              v-for="(cs, index) in listCourseNames"
              :key="index"
              :value="cs.courseName">
              {{ cs.courseName }}
            </option>
          </select>
        </div>
      </div>
      <div class="col-lg-4"></div>
      <div class="col-2"></div>
      <div class="col-2"></div>
    </div>
    <div class="card-body">
      <section class="section-table">
        <table>
          <thead>
            <tr style="background-color: #d5d1defe; text-align: center">
              <th>STT</th>
              <th>Mã môn</th>
              <th>Tên Môn</th>
              <th>Hình thức</th>
              <th>Số buổi</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr
              style="text-align: center"
              v-for="(l, index) in listCourseOfStudent">
              <td>{{ index + 1 }}</td>
              <td>{{ l.subjectCode }}</td>
              <td>{{ l.subjectName }}</td>
              <td>{{ l.learningMode == 1 ? "Online" : "Offline" }}</td>
              <td>{{ l.numberOfSessions }}</td>
              <td style="width: 100px">
                <span
                    :style="{
                      'background-color':
                        l.status == 1
                          ? '#9bcf53'
                          : l.status == 2
                          ? '#00008B' // Đổi từ '#FFC700' thành '#00008B' vì bạn muốn màu xanh đậm
                          : '#59D5E0',
                      'border-radius': '5px',
                      color: l.status == 1 ? 'darkgreen' : 'white',
                    }">
                    {{
                      l.status == 1
                        ? "Môn nền tảng"
                        : l.status == 2
                        ? "Môn chuyên sâu"
                        : "Môn bổ trợ"
                    }}
                  </span>
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
