<script setup>
import { ref, watch } from "vue";
import axios from "axios";
const token = window.localStorage.getItem("token");
const listCourseNames = ref([]);
const selectedCourse = ref(null);
const listSubject = ref([]);

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

const getListSubjectName = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/subject/subject-by-course?courseName=${selectedCourse.value.courseName}`
    )
    .then(function (response) {
      listSubject.value = response.data;
    });
};
watch(selectedCourse, () => {
  getListSubjectName();
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
        <h3>Điểm theo môn</h3>
      </div>
    </div>
    <div class="row search_table" style="margin: 10px">
      <div class="col-6">
        <select
          class="form-select"
          aria-label="Default select example"
          v-model="selectedCourse">
          <option
            v-for="(course, index) in listCourseNames"
            :key="index"
            :value="course">
            {{ course.courseName }}
          </option>
        </select>
      </div>
      <div class="col-lg-6">
        <select class="form-select" aria-label="Default select example">
          <option value="" disabled>Tên môn</option>
          <option
            v-for="(subject, index) in listSubject"
            :key="index"
            :value="subject">
            {{ subject.subjectName }}
          </option>
        </select>
      </div>
    </div>
    <div class="card-body">
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
              <th>Tên đầu điểm</th>
              <th>Trọng số</th>
              <th>Điểm</th>
              <th>Ghi chú</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center">
              <td>1</td>
              <td>Document</td>
              <td>10</td>
              <td>7.0</td>
              <td>Tốt</td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  </div>
</template>
<style scoped>
.card.border {
  box-shadow: 0 0 30px #523f690d;
  border: 1px solid #523f690d;
  border-radius: 20px;
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
