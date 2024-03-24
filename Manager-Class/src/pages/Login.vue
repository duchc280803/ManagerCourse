<script setup>
import { ref } from "vue";
import axios from "axios";

// Tạo các ref cho username và password
const username = ref("");
const password = ref("");

const errorLogin = ref({});
const login = async () => {
  // Truy cập giá trị thực sự của các ref
  const formData = {
    username: username.value,
    password: password.value,
  };

  axios
    .post("http://localhost:8080/api/v1/auth/login", formData)
    .then(function (response) {
      console.log(response.data);
      window.localStorage.setItem("logout", true);
      window.localStorage.setItem("token", response.data.accessToken);
      window.localStorage.setItem("username", response.data.username);
      window.localStorage.setItem("role", response.data.role);
      window.location.href = "http://localhost:5173/";
      swal({
        title: "Đăng nhập thành công",
        icon: "success",
        timer: 1500,
        position: "top-end", // Thiết lập vị trí ở góc trên cùng bên phải
        customClass: {
          popup: "small-popup",
        },
      });
    })
    .catch(function (error) {
      errorLogin.value = error.response.data;
    });
};
</script>
<template>
  <div class="layout">
    <div class="ant-row ant-row-center">
      <div
        class="card"
        style="
          width: 759px;
          height: 500px;
          margin-left: 120px;
          margin-bottom: 115px;
        ">
        <h4 class="header-login">Đăng nhập</h4>
        <div class="form-group">
          <label class="form-label"
            ><span style="color: red">*</span>Tài khoản</label
          >
          <input
            type="text"
            class="form-control"
            placeholder="Tài khoản..."
            v-model="username" />
          <span style="color: red">{{ errorLogin.username }}</span>
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
          <span style="color: red">{{ errorLogin.password }}</span>
        </div>
        <div class="row">
          <div class="col-lg-6">
            <RouterLink to="/register">
              <a class="forgot-pass-link" href="#/auth/forgotPassword">Đăng ký</a>
            </RouterLink>
          </div>
          <div class="col-lg-6">
            <a class="forgot-pass-link" style="margin-left: 200px;" href="#/auth/forgotPassword"
              >Quên mật khẩu?</a
            >
          </div>
        </div>
        <button class="btn btn-primary" style="margin: 35px" @click="login()">
          Đăng nhập
        </button>
        <p class="ninjadash-form-divider"><span>Hoặc</span></p>
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
