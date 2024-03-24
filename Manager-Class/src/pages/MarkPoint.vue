<script setup>
import { ref } from "vue";

const students = ref([
  {
    name: "Hoàng Công Đức",
    lab1: 34,
    lab2: 56,
    lab3: 78,
    assignment: 90,
    total: 34,
    status: "Passed",
    editing: { lab1: false, lab2: false, lab3: false, assignment: false },
  },
]);

const gradeHeaders = ref([
  "Lab 1 (20%)",
  "Lab 2 (20%)",
  "Lab 3 (20%)",
  "Assignment (40%)",
]);

const editGrade = (student, field) => {
  student.editing[field] = true;
};

const saveGrade = (student, field) => {
  student.editing[field] = false;
  student.total =
    parseInt(student.lab1) * 0.2 +
    parseInt(student.lab2) * 0.2 +
    parseInt(student.lab3) * 0.2 +
    parseInt(student.assignment) * 0.4;
  student.status = student.total >= 40 ? "Passed" : "Failed";
};

const listClass = ref([]);
const fetchClassData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class/findAllClassName`
    );
    listClass.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassData();
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
        <h3>Chấm điểm</h3>
      </div>
    </div>
    <div class="card-body">
      <div class="row search_table">
        <div class="col-4">
          <div class="input-group-prepend position-relative">
            <select class="form-select" aria-label="Default select example">
              <option value="" disabled>Chọn lớp</option>
              <option :value="cl" :key="index" v-for="(cl, index) in listClass">
                {{ cl.className }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-4"></div>
        <div class="col-2"></div>
        <div class="col-2"></div>
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
              <th>Học viên</th>
              <th v-for="(item, index) in gradeHeaders" :key="index">
                {{ item }}
              </th>
              <th>Điểm tổng</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(student, index) in students"
              :key="index"
              style="text-align: center">
              <td>{{ index + 1 }}</td>
              <td>{{ student.name }}</td>
              <td @click="editGrade(student, 'lab1')">
                <span v-if="!student.editing.lab1">{{ student.lab1 }}</span>
                <input
                  v-else
                  type="text"
                  v-model="student.lab1"
                  class="form-control"
                  @blur="saveGrade(student, 'lab1')" />
              </td>
              <td @click="editGrade(student, 'lab2')">
                <span v-if="!student.editing.lab2">{{ student.lab2 }}</span>
                <input
                  v-else
                  type="text"
                  v-model="student.lab2"
                  class="form-control"
                  @blur="saveGrade(student, 'lab2')" />
              </td>
              <td @click="editGrade(student, 'lab3')">
                <span v-if="!student.editing.lab3">{{ student.lab3 }}</span>
                <input
                  v-else
                  type="text"
                  v-model="student.lab3"
                  class="form-control"
                  @blur="saveGrade(student, 'lab3')" />
              </td>
              <td @click="editGrade(student, 'assignment')">
                <span v-if="!student.editing.assignment">{{
                  student.assignment
                }}</span>
                <input
                  v-else
                  type="text"
                  v-model="student.assignment"
                  class="form-control"
                  @blur="saveGrade(student, 'assignment')" />
              </td>
              <td>{{ student.total }}</td>
              <td>{{ student.status }}</td>
            </tr>
          </tbody>
        </table>
      </section>
      <nav aria-label="Page navigation example" style="padding-left: 985px">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link">Previous</button>
          </li>
          <li class="page-item">
            <a class="page-link" href="#"></a>
          </li>
          <li class="page-item">
            <button class="page-link">Next</button>
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
  max-height: calc(89% - 1.6rem);
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: auto;
  overflow: overlay;
}

td {
  padding-left: 5px;
}

td {
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

* {
  font-family: BaiJamjuree !important;
}
</style>
