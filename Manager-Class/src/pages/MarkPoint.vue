<script setup>
import { ref, watch, watchEffect } from "vue";
import axios from "axios";

const subjectByClass = ref([]);
const selectPointForUsers = ref([]);

const selectedClassName = ref("");
const selectedSubjectName = ref("");

const token = window.localStorage.getItem("token");
const config = {
  headers: {
    Authorization: "Bearer " + token,
  },
};
const listClassRole = ref([]);
const fetchClassDataForRole = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/v1/class/findAllClassName",
      config
    );
    listClassRole.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassDataForRole();

const fetchSubjectsByClass = async () => {
  try {
    const classId = selectedClassName.value?.id || "";
    const response = await axios.get(
      `http://localhost:8080/api/v1/subject/subject-by-class?id=${classId}`
    );
    subjectByClass.value = response.data;
  } catch (error) {
    console.error("Error fetching subjects by class:", error);
  }
};

const selectPointTeacher = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/point/select-point-teacher?subjectName=${selectedSubjectName.value.subjectName}&className=${selectedClassName.value.className}`
    );
    selectPointForUsers.value = response.data;
    console.log(selectPointForUsers.value);
  } catch (error) {
    console.error("Error fetching subjects by class:", error);
  }
};

watch(selectedClassName, () => {
  fetchSubjectsByClass();
});

watchEffect(() => {
  if (selectedClassName.value && selectedSubjectName.value) {
    selectPointTeacher();
  }
});
function editPoint(index) {
  this.selectPointForUsers[index].editing = true;
}
function savePoint(idUser, point) {
  const formData = {
    point: point,
  };

  axios
    .post(
      `http://localhost:8080/api/v1/point/create?idSubject=${selectedSubjectName.value.id}&idUser=${idUser}`,
      formData
    )
    .then((response) => {
      this.fetchSubjectsByClass();
    });
}
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
        <h3>Chấm điểm</h3>
      </div>
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
              <option
                :value="cl"
                :key="index"
                v-for="(cl, index) in listClassRole">
                {{ cl.className }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-4">
          <select
            class="form-select"
            aria-label="Default select example"
            v-model="selectedSubjectName">
            <option value="" disabled>Chọn môn</option>
            <option :value="s" v-for="(s, index) in subjectByClass">
              {{ s.subjectName }}
            </option>
          </select>
        </div>
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
              <th>Điểm</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr
              style="text-align: center"
              v-for="(s, index) in selectPointForUsers"
              :key="s.idUser">
              <td>{{ index + 1 }}</td>
              <td>{{ s.fullName }}</td>
              <td>
                <template v-if="!s.editing && !s.hasScore">
                  <span @dblclick="editPoint(index)">{{ s.point }}</span>
                </template>
                <template v-else>
                  <input
                    v-if="!s.point"
                    type="number"
                    class="form-control"
                    v-model="s.point"
                    @blur="savePoint(s.idUser, s.point)" />
                  <span v-else>{{ s.point }}</span>
                </template>
              </td>
              <td :style="{ color: s.point < 5 ? 'red' : 'green' }">
                {{ s.point < 5 ? "False" : "Passed" }}
              </td>
            </tr>
          </tbody>
        </table>
      </section>
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
