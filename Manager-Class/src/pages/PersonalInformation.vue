<script setup>
import axios from "axios";
import Swal from "sweetalert2";
import { ref } from "vue";
var username = window.localStorage.getItem("username");
const personal = ref({});
const fillPersonal = async () => {
  axios
    .get(`http://localhost:8080/api/v1/auth/fill-personal?username=${username}`)
    .then(function (response) {
      personal.value = response.data;
    });
};
fillPersonal();

const errorInfor = ref({});
const updatePersonal = async () => {
  Swal.fire({
    title: "Bạn có muốn chỉnh sửa không?",
    text: "",
    icon: "question",
    showCancelButton: true,
    cancelButtonText: "Hủy bỏ", // Thay đổi từ "Cancel" thành "Hủy bỏ"
    cancelButtonColor: "#d33",
    confirmButtonColor: "#3085d6",
    confirmButtonText: "Xác nhận", // Thay đổi từ "Yes" thành "Có"
    reverseButtons: true,
  }).then((result) => {
    if (result.value) {
      axios
        .put(
          `http://localhost:8080/api/v1/auth/update-personal/${username}`,
          personal.value
        )
        .then(function (response) {
          fillPersonal();
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Cập nhật thành công",
            timer: 1500,
            customClass: {
              popup: "small-popup", // Thêm class cho message
            },
            showConfirmButton: false, // Ẩn nút "OK"
          });
        })
        .catch(function (error) {
          errorInfor.value = error.response.data;
        });
    } else {
      Swal.fire({
        position: "top-end",
        icon: "success",
        title: "Hủy bỏ thành công",
        timer: 1500,
        width: "400px",
        height: "300px",
        customClass: {
          popup: "small-popup", // Thêm class cho message
        },
        showConfirmButton: false, // Ẩn nút "OK"
      });
    }
  });
};
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
    <div
      data-v-d7d2693e=""
      class="ant-row"
      style="
        margin-left: -12.5px;
        margin-right: -12.5px;
        margin-bottom: 10px;
        margin: 15px;
        row-gap: 0px;
      ">
      <div
        data-v-d7d2693e=""
        class="ant-col ant-col-xs-24 ant-col-md-12 col-align-center"
        style="padding-left: 12.5px; padding-right: 12.5px">
        <!-- eslint-disable vue/no-v-html -->
        <div class="unicon">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="inherit">
            <path
              d="M14.49,10.86a3.09,3.09,0,1,0-5,0,4.67,4.67,0,0,0-1.12,1A1,1,0,1,0,10,13.12a2.62,2.62,0,0,1,2.05-1h0a2.62,2.62,0,0,1,2.05,1,1,1,0,0,0,.78.37,1,1,0,0,0,.78-1.62A4.67,4.67,0,0,0,14.49,10.86ZM12,10.13h0A1.09,1.09,0,1,1,13.09,9,1.09,1.09,0,0,1,12,10.13Zm8.46-.5A8.5,8.5,0,0,0,7.3,3.36,8.56,8.56,0,0,0,3.54,9.63,8.46,8.46,0,0,0,6,16.46l5.3,5.31a1,1,0,0,0,1.42,0L18,16.46A8.46,8.46,0,0,0,20.46,9.63ZM16.6,15.05,12,19.65l-4.6-4.6A6.49,6.49,0,0,1,5.53,9.83,6.57,6.57,0,0,1,8.42,5a6.47,6.47,0,0,1,7.16,0,6.57,6.57,0,0,1,2.89,4.81A6.49,6.49,0,0,1,16.6,15.05Z"></path>
          </svg>
        </div>
        <label
          data-v-d7d2693e=""
          for=""
          class="lablestyle-tt"
          style="margin: 0px 10px"
          >Thông tin tài khoản</label
        >
      </div>
    </div>
    <div class="row ant-row">
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Tên đăng nhập</span></span>
          <input
            type="text"
            class="form-control"
            id="brandName"
            placeholder="Tên đăng nhập"
            v-model="personal.username"
            disabled />
        </div>
      </div>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Họ tên</span></span>
          <input
            type="text"
            class="form-control"
            id="brandName"
            v-model="personal.fullName"
            placeholder="Họ tên" />
        </div>
        <span style="color: red">{{ errorInfor.fullName }}</span>
      </div>
      <p style="padding: 5px"></p>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Email</span></span>
          <input
            type="text"
            class="form-control"
            id="brandName"
            v-model="personal.email"
            placeholder="Email" />
        </div>
        <span style="color: red">{{ errorInfor.email }}</span>
      </div>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Số điện thoại</span></span>
          <input
            type="text"
            class="form-control"
            id="brandName"
            v-model="personal.phoneNumber"
            placeholder="Số điện thoại" />
        </div>
        <span style="color: red">{{ errorInfor.phoneNumber }}</span>
      </div>
    </div>
    <div
      data-v-d7d2693e=""
      class="ant-row"
      style="
        margin-left: -12.5px;
        margin-right: -12.5px;
        margin-bottom: 10px;
        margin: 15px;
        row-gap: 0px;
      ">
      <div
        data-v-d7d2693e=""
        class="ant-col ant-col-xs-24 ant-col-md-12 col-align-center"
        style="padding-left: 12.5px; padding-right: 12.5px">
        <!-- eslint-disable vue/no-v-html -->
        <div class="unicon">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="inherit">
            <path
              d="M12,2A10,10,0,0,0,4.65,18.76h0a10,10,0,0,0,14.7,0h0A10,10,0,0,0,12,2Zm0,18a8,8,0,0,1-5.55-2.25,6,6,0,0,1,11.1,0A8,8,0,0,1,12,20ZM10,10a2,2,0,1,1,2,2A2,2,0,0,1,10,10Zm8.91,6A8,8,0,0,0,15,12.62a4,4,0,1,0-6,0A8,8,0,0,0,5.09,16,7.92,7.92,0,0,1,4,12a8,8,0,0,1,16,0A7.92,7.92,0,0,1,18.91,16Z"></path>
          </svg>
        </div>
        <label
          data-v-d7d2693e=""
          for=""
          class="lablestyle-tt"
          style="margin: 0px 10px"
          >Thông tin cá nhân</label
        >
      </div>
    </div>
    <div class="row ant-row">
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Ngày sinh</span></span>
          <input
            type="date"
            class="form-control"
            id="brandName"
            v-model="personal.dateOfBirth" />
        </div>
        <span style="color: red">{{ errorInfor.dateOfBirth }}</span>
      </div>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Địa chỉ</span></span>
          <input
            type="text"
            class="form-control"
            id="brandName"
            placeholder="Address"
            v-model="personal.address" />
        </div>
        <span style="color: red">{{ errorInfor.address }}</span>
      </div>
      <p style="padding: 5px"></p>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Tuổi</span></span>
          <input
            type="number"
            class="form-control"
            id="brandName"
            v-model="personal.age" />
        </div>
        <span style="color: red">{{ errorInfor.age }}</span>
      </div>
      <div class="col-lg-6">
        <div class="form-group">
          <span class="header-title"><span>Giới tính</span></span>
          <select
            class="form-select"
            aria-label="Default select example"
            v-model="personal.gender">
            <option :value="true">Nam</option>
            <option :value="false">Nữ</option>
          </select>
        </div>
        <span style="color: red">{{ errorInfor.gender }}</span>
      </div>
    </div>
    <button
      data-bs-toggle="modal"
      data-bs-target="#exampleModal1"
      @click="updatePersonal(personal.id)"
      style="
        text-decoration: none;
        background-color: #fc6736;
        border-radius: 5px;
        color: black;
        font-weight: 600;
        width: 118px;
        margin-left: 540px;
        margin-top: 30px;
        margin-bottom: 30px;
      ">
      Lưu thay đổi
    </button>
  </div>
</template>
<style scoped>
* {
  font-family: BaiJamjuree !important;
}
label.lablestyle-tt {
  color: #292929;
  font-size: 16px;
  font-weight: 600;
  margin: 0 10px;
}
.unicon {
  display: inline-block;
}
.row.ant-row {
  margin: 15px;
}
button.btn.btn-primary.luu {
  margin: 25px 0;
  width: 120px;
  height: 44px;
  font-size: 16px;
  background-color: #00c7f2;
  border-color: #00c7f2;
  margin-left: 540px;
}
</style>
