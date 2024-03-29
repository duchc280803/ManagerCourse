<script setup>
import { ref, watch } from "vue";
import axios from "axios";

// Khai báo các biến ref
const token = window.localStorage.getItem("token");
console.log(token);
const listCourseNames = ref([]);
const selectedCourse = ref(null);
const selectedSubject = ref(null);
const listSubject = ref([]);
const listPointStudent = ref([]);

// Hàm fetch dữ liệu các khóa học
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

// Hàm lấy danh sách môn học dựa trên khóa học đã chọn
const getListSubjectName = async () => {
  if (selectedCourse.value) {
    const config = {
      headers: {
        Authorization: "Bearer " + token,
      },
    };
    axios
      .get(
        `http://localhost:8080/api/v1/point/select-point-student?courseName=${selectedCourse.value.courseName}`,
        config
      )
      .then(function (response) {
        listSubject.value = response.data;
        console.log(listSubject.value);
      });
  }
};

// Sử dụng watch để theo dõi sự thay đổi của selectedCourse và gọi hàm getListSubjectName
watch(selectedCourse, () => {
  getListSubjectName();
});

// Gọi fetchData để lấy danh sách khóa học khi component được khởi tạo
fetchData();
</script>
<template>
  <div
    class="card"
    style="
      width: 1192px;
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
      <div class="col-12">
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
      <div class="col-lg-6"></div>
    </div>
    <div class="card-body">
      <h1></h1>
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
              <th>Điểm</th>
              <th>Trạng Thái</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center" v-for="(item, index) in listSubject">
              <td>{{ index + 1 }}</td>
              <td>{{ item.subjectName }}</td>
              <td>{{ item.point }}</td>
              <td
                :style="{
                  color: item.point < 5 ? 'red' : 'green',
                }">
                {{ item.point < 5 ? "False" : "Passed" }}
              </td>
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
