<script setup>
import { ref } from "vue";
import axios from "axios";

const fullName = ref("");
const phoneNumber = ref("");
const email = ref("");
const message = ref("");

// selectContact();

const createContact = async () => {
  const contactData = {
    fullName: fullName.value,
    phoneNumber: phoneNumber.value,
    email: email.value,
    message: message.value,
  };
  axios
    .post(`http://localhost:8080/api/v1/contact/create`, contactData)
    .then(function (response) {
    });
};
</script>
<template>
  <header>
    <div class="full-width-header home8-style4 main-home">
      <div class="menu-area sticky">
        <div class="container">
          <div class="row">
            <div class="col-lg-2">
              <div class="logo">
                <img
                  src="https://static.topcv.vn/company_logos/YjF8IUn9DGHx3HGzTUPki8DycZsRRqLA_1661485501____8e43ee2aad361f320c7d0a13becc6a92.png"
                  alt=""
                  srcset=""
                  style="width: 100px; margin-top: 15px" />
              </div>
            </div>
            <div class="col-lg-8 text-right">
              <div class="rs-menu-area">
                <div class="main-menu">
                  <nav class="rs-menu rs-menu-close">
                    <ul class="nav-menu" id="lst-menu-item">
                      <RouterLink to="/home">
                        <li class="current-menu-item">
                          <a href="/">Home</a>
                        </li>
                      </RouterLink>
                      <li id="header_menu-parent">
                        <a href="/khoahoc">Khóa học</a>
                      </li>
                      <RouterLink to="/call">
                        <li id="header_menu-parent">
                          <a href="/home/contact">Liên hệ</a>
                        </li>
                      </RouterLink>
                    </ul>
                    <!-- //.nav-menu -->
                  </nav>
                </div>
                <!-- //.main-menu -->
              </div>
            </div>
            <div class="col-lg-2">
              <a
                class="readon orange-btn main-home d-none d-xl-block"
                target="_blank"
                href=""
                data-toggle="modal"
                data-target="#contactModal"
                >Liên hệ ngay</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div class="main-content">
    <RouterView></RouterView>
  </div>
  <!-- Modal update-->
  <div
    class="modal"
    id="contactModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg">
        <div class="modal-header">
          <h5 class="modal-title">Để lại thông tin tại đây</h5>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="" class="form-label">Họ và tên</label>
            <input
              type="text"
              class="form-control"
              placeholder="Họ và tên ..."
              id="name" />
          </div>
          <div class="form-group">
            <label for="" class="form-label">Số điện thoại</label>
            <input
              type="text"
              class="form-control"
              placeholder="Số điện thoại ..."
              id="phoneNumber" />
          </div>
          <div class="form-group">
            <label for="" class="form-label">Email</label>
            <input
              type="text"
              class="form-control"
              placeholder="Email ..."
              id="email" />
          </div>
          <div class="form-group">
            <label for="" class="form-label">Lời nhắn</label>
            <textarea
              class="form-control"
              placeholder="Lời nhắn ..."
              id="content"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Close
          </button>
          <button type="button" class="btn btn-warning" id="submit">Gửi</button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #ffffff;
  z-index: 1000;
}
.full-width-header.home8-style4.main-home {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}
.full-width-header.home8-style4.main-home .menu-area {
  height: 90px;
  background: #c4e4ff;
}
nav ul {
  float: left;
}
nav ul li {
  display: inline-block;
  line-height: 80px;
  margin: 0.5px;
  margin-right: 50px;
}
nav ul li a {
  text-transform: uppercase;
}
.rs-menu-area {
  display: flex;
  align-items: center;
  justify-content: center;
}
li.current-menu-item a {
  color: #ff5421 !important   ;
  padding-right: 18px;
  font-size: 16px;
  font-weight: 700;
  text-transform: uppercase;
}
li#header_menu-parent a {
  color: black !important   ;
  padding-right: 18px;
  font-size: 16px;
  font-weight: 700;
  text-transform: uppercase;
}
.readon.orange-btn {
  color: #ffffff;
  background: #ff5421;
  border-color: #ff5421;
  border-radius: 5px 5px 5px 5px;
  outline: none;
  padding: 12px 35px;
  display: inline-block;
  font-size: 14px;
  font-weight: 500;
  text-transform: uppercase;
}
.readon.orange-btn.main-home {
  padding: 10px 20px 10px 20px !important;
  margin-top: 25px;
}
/* Thiết lập điều kiện để hiển thị menu tùy thuộc vào độ rộng trình duyệt */
@media screen and (max-width: 768px) {
  /* Đặt độ rộng tối thiểu để hiển thị menu */
  .burger {
    display: none;
  }

  #burger:checked ~ #menu {
    display: block;
  }
}

@media (max-width: 991px) {
  .burger {
    display: none;
  }
}
</style>
