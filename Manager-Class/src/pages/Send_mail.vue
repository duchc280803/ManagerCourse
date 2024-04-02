<script setup>
import axios from "axios";
import { ref } from "vue";

const getListEmailTemplate = ref([]);
const findByEmailTemplate = async () => {
  axios
    .get(`http://localhost:8080/api/v1/templates/show`)
    .then(function (response) {
      getListEmailTemplate.value = response.data;
    });
};
findByEmailTemplate();
</script>
<template>
  <div class="card" style="margin: 20px" id="emailForm">
    <div class="card-body">
      <section class="section-table">
        <table>
          <thead>
            <tr style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
              <th>STT</th>
              <th>Tên mẫu email</th>
              <th>Ngày tạo</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center; padding-top: 5px" v-for="(e, index) in getListEmailTemplate">
              <td>{{ index + 1 }}</td>
              <td>{{ e.templateName }}</td>
              <td>{{ e.createdAt }}</td>
              <td>
                <RouterLink :to="`/template-email/${e.id}`">
                  <button style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Xem mẫu
                  </button>
                </RouterLink>
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

table.m_-6748071709643648038container {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
  font-weight: 600;
  border: 10px double #13336042;
  background-color: #006c6d09;
  border-radius: 8px;
  font-size: medium;
  color: #321811e8;
}
</style>
