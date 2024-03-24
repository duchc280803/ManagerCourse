<script setup>
import { ref } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

// Tạo các ref cho username và password
const username = ref("");
const password = ref("");
const email = ref("");

const errorRegister = ref({});
const register = async () => {
  Swal.fire({
    title: "Bạn có muốn đăng ký tài khoản không?",
    text: "",
    icon: "question",
    showCancelButton: true,
    cancelButtonText: "Hủy bỏ",
    cancelButtonColor: "#d33",
    confirmButtonColor: "#3085d6",
    confirmButtonText: "Xác nhận",
    reverseButtons: true,
  }).then(async (result) => {
    if (result.value) {
      // Truy cập giá trị thực sự của các ref
      const formData = {
        username: username.value,
        password: password.value,
        email: email.value,
      };

      axios
        .post("http://localhost:8080/api/v1/auth/register", formData)
        .then(function (response) {
        })
        .catch(function (error) {
          errorRegister.value = error.response.data;
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
          popup: "small-popup",
        },
        showConfirmButton: false,
      });
    }
  });
};
</script>
<template>
  <div class="layout">
    <div class="ant-row ant-row-center">
      <div
        class="card"
        style="width: 759px; margin-left: 120px; margin-bottom: 115px">
        <h4 class="header-login">Đăng ký</h4>
        <div class="form-group">
          <label class="form-label"
            ><span style="color: red">*</span>Tài khoản</label
          >
          <input
            type="text"
            class="form-control"
            placeholder="Tài khoản..."
            v-model="username" />
          <span style="color: red">{{ errorRegister.username }}</span>
          <span style="color: red">{{ errorRegister.messageUsername }}</span>
        </div>
        <div class="form-group">
          <label class="form-label"
            ><span style="color: red">*</span>Email</label
          >
          <input
            type="text"
            class="form-control"
            placeholder="Email..."
            v-model="email" />
          <span style="color: red">{{ errorRegister.email }}</span>
          <span style="color: red">{{ errorRegister.messageEmail }}</span>
        </div>
        <div class="form-group">
          <label class="form-label"
            ><span style="color: red">*</span>Mật khẩu</label
          >
          <input
            type="password"
            class="form-control"
            placeholder="Mật khẩu..."
            v-model="password" />
          <span style="color: red">{{ errorRegister.password }}</span>
        </div>
        <button
          class="btn btn-primary"
          style="margin: 35px"
          @click="register()">
          Đăng ký
        </button>
      </div>
    </div>
  </div>
</template>
<style scoped>
* {
  font-family: BaiJamjuree !important;
}
.header-login {
  text-align: center;
  padding: 20px;
  border-bottom: 1px solid #e3e6ef;
}
.form-group {
  padding: 10px 40px;
}
.ninjadash-auth-extra-links {
  padding: 10px 40px;
}
label.ant-checkbox-wrapper {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  color: #000000d9;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5715;
  list-style: none;
  font-feature-settings: "tnum";
  display: inline-flex;
  align-items: baseline;
  line-height: unset;
  cursor: pointer;
}
span.ant-checkbox {
  font-size: 13px;
  color: #8c90a4;
}
input.ant-checkbox-input {
  box-sizing: border-box;
  padding: 0;
  border-radius: 1px;
  position: relative;
  top: 0;
  left: 0;
  display: block;
  width: 16px;
  height: 16px;
  direction: ltr;
  background-color: #fff;
  border: 1px solid #d9d9d9;
  border-radius: 2px;
  border-collapse: separate;
  transition: all 0.3s;
}
.ghinho {
  position: relative;
  top: -2px;
  left: 8px;
}
a.forgot-pass-link {
  font-size: 16px;
  color: #8231d3;
  text-decoration: none;
  padding-left: 40px;
}
p.ninjadash-form-divider {
  font-size: 13px;
  color: #f1f2f6;
  text-align: center;
  position: relative;
  margin: 0 -10px 25px -10px;
  border-bottom: 1px solid #e3e6ef;
}
p.ninjadash-form-divider span {
  font-weight: 500;
  padding: 0 15px;
  display: inline-block;
  position: relative;
  z-index: 2;
  background: #ffffff;
  color: #404040;
}
</style>
