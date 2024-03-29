<script setup>
import { ref } from "vue";
import axios from "axios";

const listContact = ref([]);

const selectContact = async () => {
  axios
    .get(`http://localhost:8080/api/v1/contact/show`)
    .then(function (response) {
      listContact.value = response.data;
    });
};
selectContact()

</script>
<template>
  <div class="container student">
    <div class="card border">
      <div class="card-header">
        <div class="card-title">
          <h3>Thông tin liên hệ</h3>
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
                <th>Họ tên</th>
                <th>Số điên thoại</th>
                <th>Email</th>
                <th>Tin nhắn</th>
                <th>Trạng thái</th>
                <th style="width: 100px">Hành    động</th>
              </tr>
            </thead>
            <tbody>
              <tr style="text-align: center" v-for="(c, index) in listContact">
                <td>{{ index + 1 }}</td>
                <td>{{ c.fullName }}</td>
                <td>{{ c.phoneNumber }}</td>
                <td>{{ c.email }}</td>
                <td>{{ c.message }}</td>
                <td>{{ c.status }}</td>
              </tr>
            </tbody>
          </table>
        </section>
      </div>
    </div>
  </div>
</template>
<style scoped>
* {
  font-family: BaiJamjuree !important;
}
.modal-content.lg {
  width: 950px;
  margin-left: -200px;
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
.search_table input {
  height: 42px;
  border-radius: 16px;
  padding: 9px 30px 9px 12px;
  margin: 10px 5px 10px 0;
  border: 1px solid #e3ebf6;
  font-size: 15px;
}
input.form-control.search {
  width: 410px;
}
.search_table .btn {
  margin: 10px 0;
  width: 80px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
}
button.btn.btn-primary.action {
  margin: 10px;
  width: 70px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
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
.modal-title {
  font-size: 24px;
}
.ctrl_label {
  font-style: normal;
  font-weight: 600 !important;
  font-size: 16px !important;
  color: #000 !important;
  align-items: center;
  display: flex;
}
.text-danger {
  color: #f64e60 !important;
}
input.form-control.an-select {
  box-sizing: border-box;
  margin: 0;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5715;
  list-style: none;
  font-feature-settings: "tnum", "tnum";
  position: relative;
  display: inline-block;
  cursor: pointer;
}
a.btn.btn-primary.action {
  margin: 10px;
  width: 70px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
}
.pap {
  padding-top: 20px;
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
</style>
