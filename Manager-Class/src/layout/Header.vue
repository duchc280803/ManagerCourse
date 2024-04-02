<script setup>
import { ref } from "vue";
const showPopover = ref(false);
const togglePopover = () => {
  if (!showPopover.value) {
    showPopover.value = true;
  } else {
    showPopover.value = false;
  }
};
var role = window.localStorage.getItem("role");
const logout = async () => {
  window.location.href = "http://localhost:5173/login";
  window.localStorage.setItem("logout", false);
  window.localStorage.remove("token");
  window.localStorage.remove("role");
  window.localStorage.remove("username");
};
var auth = window.localStorage.getItem("logout") || "false";
console.log(auth);
</script>
<template>
  <div class="dSWqCR">
    <section class="ant-layout layout">
      <header
        v-show="auth === 'true'"
        class="ant-layout-header"
        style="position: fixed; width: 100%; top: 0px; left: 0px">
        <div class="ninjadash-header-content d-flex">
          <div class="ninjadash-header-content__left">
            <div class="navbar-brand logo-nav">
              <a href="" class="router-link-active ninjadash-logo">
                <img src="../assets/img/edu.png" alt="logo" />
              </a>
            </div>
          </div>
          <div class="ninjadash-header-content__right d-flex">
            <div class="ninjadash-nav-actions">
              <div class="cDrxbr">
                <div
                  class="ninjadash-nav-actions__item ninjadash-nav-actions__author">
                  <a
                    @click="togglePopover()"
                    href="#"
                    class="ninjadash-nav-action-link"
                    ><span class="ant-avatar ant-avatar-circle"></span
                    ><span
                      data-v-ec208c2e=""
                      class="ninjadash-nav-actions__author--name"
                      >Hoàng Công Đức</span
                    >
                    <div class="unicon">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="inherit">
                        <path
                          d="M17,9.17a1,1,0,0,0-1.41,0L12,12.71,8.46,9.17a1,1,0,0,0-1.41,0,1,1,0,0,0,0,1.42l4.24,4.24a1,1,0,0,0,1.42,0L17,10.59A1,1,0,0,0,17,9.17Z"></path>
                      </svg>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="ant-popover-inner" v-show="showPopover">
          <div class="ant-popover-inner-content">
            <div class="user-dropdown">
              <div class="user-dropdown__info">
                <span
                  class="ant-avatar ant-avatar-circle"
                  style="
                    width: 42px;
                    height: 42px;
                    line-height: 42px;
                    font-size: 18px;
                  "
                  ><span
                    class="ant-avatar-string"
                    style="
                      line-height: 42px;
                      transform: scale(1) translateX(-50%);
                    "
                    ><!----></span
                  ></span
                >
                <figcaption data-v-ec208c2e="" style="margin-left: 0.4rem">
                  <h1 data-v-ec208c2e="" class="kPHihb" id="">
                    Hoàng Công Đức
                  </h1>
                  <p data-v-ec208c2e="">duchc03.developer@gmail.com</p>
                </figcaption>
              </div>
              <ul class="user-dropdown__links">
                <li class="dropdown-li">
                  <RouterLink to="/personal-information">
                    <a href="">
                      <div class="unicon">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                          fill="inherit">
                          <path
                            d="M15.71,12.71a6,6,0,1,0-7.42,0,10,10,0,0,0-6.22,8.18,1,1,0,0,0,2,.22,8,8,0,0,1,15.9,0,1,1,0,0,0,1,.89h.11a1,1,0,0,0,.88-1.1A10,10,0,0,0,15.71,12.71ZM12,12a4,4,0,1,1,4-4A4,4,0,0,1,12,12Z"></path>
                        </svg>
                        Thông tin cá nhân
                      </div>
                    </a>
                  </RouterLink>
                </li>
                <li class="dropdown-li">
                  <a href="">
                    <div class="unicon">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="inherit">
                        <path
                          d="M22,11.05c0-.11,0-.22,0-.33l-.09-.6-.09-.39c0-.17-.08-.34-.13-.51s-.08-.27-.13-.4a2.17,2.17,0,0,1-.07-.24s0,0,0-.05a10.1,10.1,0,0,0-5.9-5.9s0,0-.05,0l-.23-.07-.42-.13c-.15,0-.31-.08-.46-.12l-.46-.1-.46-.07c-.16,0-.31,0-.48-.06s-.35,0-.52,0L12,2l-.39,0c-.17,0-.35,0-.52,0s-.32,0-.48.06l-.46.07-.46.1c-.15,0-.31.07-.46.12l-.42.13-.23.07s0,0-.05,0a10.1,10.1,0,0,0-5.9,5.9s0,0,0,.05a2.17,2.17,0,0,1-.07.24c0,.13-.09.26-.13.4s-.09.34-.13.51l-.09.39-.09.6c0,.11,0,.22,0,.33,0,.31,0,.63,0,.95s0,.64,0,.95c0,.11,0,.22,0,.33l.09.6.09.39c0,.17.08.34.13.51s.08.27.13.4a2.17,2.17,0,0,1,.07.24.43.43,0,0,1,0,.07,10,10,0,0,0,5.89,5.88s0,0,.05,0l.24.07.4.13.51.13.39.09.6.09.33,0c.31,0,.63.05.95.05s.64,0,.95-.05l.33,0,.6-.09.39-.09.51-.13.4-.13.24-.07s0,0,.05,0a10,10,0,0,0,5.89-5.88.43.43,0,0,1,0-.07c0-.08.05-.16.07-.24s.09-.26.13-.4.09-.34.13-.51l.09-.39.09-.6c0-.11,0-.22,0-.33,0-.31.05-.63.05-.95S22,11.36,22,11.05Zm-6.3-6.16a8,8,0,0,1,3.46,3.46l-2.86,1a5.14,5.14,0,0,0-1.64-1.64Zm-5.36-.7c.21-.05.41-.08.61-.11l.24,0a8.24,8.24,0,0,1,1.72,0l.24,0c.2,0,.4.06.61.11h.06l-1,2.86A4.49,4.49,0,0,0,12,7a4.4,4.4,0,0,0-.73.06l-1-2.86Zm-1.94.7,1,2.86A5.14,5.14,0,0,0,7.75,9.39l-2.86-1A8,8,0,0,1,8.35,4.89ZM4.19,13.71a4.17,4.17,0,0,1-.1-.6c0-.09,0-.17,0-.25a7.42,7.42,0,0,1,0-1.72c0-.08,0-.16,0-.25a4.17,4.17,0,0,1,.1-.6s0,0,0-.06l2.86,1a4.47,4.47,0,0,0,0,1.46l-2.86,1S4.19,13.73,4.19,13.71Zm4.16,5.4a8,8,0,0,1-3.46-3.46l2.86-1a5.14,5.14,0,0,0,1.64,1.64Zm5.36.7c-.21.05-.41.08-.61.11l-.24,0a8.24,8.24,0,0,1-1.72,0l-.24,0c-.2,0-.4-.06-.61-.11h-.06l1-2.86a4.47,4.47,0,0,0,1.46,0l1,2.86Zm-.67-5h0c-.17.06-.34.1-.5.14a2.73,2.73,0,0,1-1,0c-.16,0-.33-.08-.5-.14h0A3,3,0,0,1,9.2,13v0a3.23,3.23,0,0,1-.14-.51,2.63,2.63,0,0,1,0-1A3.23,3.23,0,0,1,9.19,11v0A3,3,0,0,1,11,9.2h0c.17-.06.34-.1.5-.14a2.73,2.73,0,0,1,1,0c.16,0,.33.08.5.14h0A3,3,0,0,1,14.8,11v0a3.23,3.23,0,0,1,.14.51,2.63,2.63,0,0,1,0,1,3.23,3.23,0,0,1-.14.51v0A3,3,0,0,1,13,14.8Zm2.61,4.31-1-2.86a5.14,5.14,0,0,0,1.64-1.64l2.86,1A8,8,0,0,1,15.65,19.11ZM20,12.86c0,.08,0,.16,0,.25a4.17,4.17,0,0,1-.1.6s0,0,0,.06l-2.86-1a4.47,4.47,0,0,0,0-1.46l2.86-1s0,0,0,.06a4.17,4.17,0,0,1,.1.6c0,.09,0,.17,0,.25a7.42,7.42,0,0,1,0,1.72Z"></path>
                      </svg>
                      Trang cá nhân
                    </div>
                  </a>
                </li>
              </ul>
              <a class="user-dropdown__bottomAction" @click="logout()">
                <span class="anticon anticon-logout">
                  <svg
                    focusable="false"
                    class=""
                    data-icon="logout"
                    width="1em"
                    height="1em"
                    fill="currentColor"
                    aria-hidden="true"
                    viewBox="64 64 896 896">
                    <path
                      d="M868 732h-70.3c-4.8 0-9.3 2.1-12.3 5.8-7 8.5-14.5 16.7-22.4 24.5a353.84 353.84 0 01-112.7 75.9A352.8 352.8 0 01512.4 866c-47.9 0-94.3-9.4-137.9-27.8a353.84 353.84 0 01-112.7-75.9 353.28 353.28 0 01-76-112.5C167.3 606.2 158 559.9 158 512s9.4-94.2 27.8-137.8c17.8-42.1 43.4-80 76-112.5s70.5-58.1 112.7-75.9c43.6-18.4 90-27.8 137.9-27.8 47.9 0 94.3 9.3 137.9 27.8 42.2 17.8 80.1 43.4 112.7 75.9 7.9 7.9 15.3 16.1 22.4 24.5 3 3.7 7.6 5.8 12.3 5.8H868c6.3 0 10.2-7 6.7-12.3C798 160.5 663.8 81.6 511.3 82 271.7 82.6 79.6 277.1 82 516.4 84.4 751.9 276.2 942 512.4 942c152.1 0 285.7-78.8 362.3-197.7 3.4-5.3-.4-12.3-6.7-12.3zm88.9-226.3L815 393.7c-5.3-4.2-13-.4-13 6.3v76H488c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h314v76c0 6.7 7.8 10.5 13 6.3l141.9-112a8 8 0 000-12.6z"></path>
                  </svg> </span
                >Đăng xuất
              </a>
            </div>
          </div>
        </div>
      </header>
      <section class="ant-layout ant-layout-has-sider">
        <aside
          v-show="auth === 'true'"
          class="ant-layout-sider ant-layout-sider-light"
          style="
            margin: 72px 0px 0px;
            padding: 20px 20px 55px 0px;
            overflow-y: auto;
            height: 100vh;
            position: fixed;
            left: 0px;
            z-index: 998;
            flex: 0 0 280px;
            max-width: 280px;
            min-width: 280px;
            width: 280px;
          ">
          <div class="ant-layout-sider-children">
            <div class="ps">
              <div class="menu-fix">
                <div class="sidebar-nav scroll-sidebar">
                  <ul id="sidebarnav">
                    <RouterLink to="/personal-information">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/dashboard"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/user.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Thông tin cá nhân</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/teacher" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/teacher.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý giảng viên</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/student" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a class="sidebar-link" aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/students.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý học viên</span>
                        </a>
                      </li>
                    </RouterLink>
                    <a
                      v-show="role === 'STUDENT'"
                      href="#pageSubmenu"
                      class="dropdown-toggle collapsed sidebar-link"
                      data-bs-toggle="collapse"
                      data-bs-target="#pageSubmenu1">
                      <span>
                        <img
                          src="../assets/img/students.png"
                          width="180"
                          style="width: 21px; height: 21px" />
                      </span>
                      <span class="hide-menu">Học viên</span>
                    </a>
                    <ul class="list-unstyled collapse" id="pageSubmenu1">
                      <RouterLink to="/student/course">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/product"
                            style="font-weight: bold"
                            >Khóa học của tôi</a
                          >
                        </li>
                      </RouterLink>
                      <!-- <RouterLink to="/all-course">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/size"
                            style="font-weight: bold"
                            >Tất cả khóa học</a
                          >
                        </li>
                      </RouterLink> -->
                      <RouterLink to="/schedule-of-student">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/size"
                            style="font-weight: bold"
                            >Lịch học</a
                          >
                        </li>
                      </RouterLink>
                    </ul>
                    <a
                      v-show="role === 'STUDENT'"
                      href="#pageSubmenu"
                      class="dropdown-toggle collapsed sidebar-link"
                      data-bs-toggle="collapse"
                      data-bs-target="#pageSubmenu2">
                      <span>
                        <img
                          src="../assets/img/hand-gesture.png"
                          width="180"
                          style="width: 21px; height: 21px" />
                      </span>
                      <span class="hide-menu">Điểm</span>
                    </a>
                    <ul class="list-unstyled collapse" id="pageSubmenu2">
                      <RouterLink to="/point-subjects">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/product"
                            style="font-weight: bold"
                            >Điểm theo môn</a
                          >
                        </li>
                      </RouterLink>
                      <!-- <RouterLink to="/point-sum">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/size"
                            style="font-weight: bold"
                            >Điểm tổng môn</a
                          >
                        </li>
                      </RouterLink> -->
                    </ul>
                    <a
                      v-show="role === 'TEACHER'"
                      href="#pageSubmenu"
                      class="dropdown-toggle collapsed sidebar-link"
                      data-bs-toggle="collapse"
                      data-bs-target="#pageSubmenu">
                      <span>
                        <img
                          src="../assets/img/teacher.png"
                          width="180"
                          style="width: 21px; height: 21px" />
                      </span>
                      <span class="hide-menu">Giảng viên</span>
                    </a>
                    <ul class="list-unstyled collapse" id="pageSubmenu">
                      <RouterLink to="/class/teacher">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/product"
                            style="font-weight: bold"
                            >Học viên</a
                          >
                        </li>
                      </RouterLink>
                      <RouterLink to="/schedule">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/product"
                            style="font-weight: bold"
                            >Lịch dậy của tôi</a
                          >
                        </li>
                      </RouterLink>
                      <RouterLink to="/mark-point">
                        <li class="sidebar-item" style="margin-left: 37px">
                          <a
                            class="sidebar-link"
                            href="#/size"
                            style="font-weight: bold"
                            >Chấm điểm</a
                          >
                        </li>
                      </RouterLink>
                    </ul>
                    <RouterLink to="/class" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/online-learning.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý lớp</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/course" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/book.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý khóa học</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/subject" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/text-books.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý môn học</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/schedule" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/project.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý lịch học</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/class-room" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/chalkboard.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Quản lý phòng học</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/send-mail" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/mail.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Send mail</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/contact" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/contacts.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Contact</span>
                        </a>
                      </li>
                    </RouterLink>
                    <RouterLink to="/mail-server" v-show="role === 'ADMIN'">
                      <li class="sidebar-item">
                        <a
                          class="sidebar-link"
                          href="#/order-counter"
                          aria-expanded="false">
                          <span>
                            <img
                              src="../assets/img/mail.png"
                              width="180"
                              alt=""
                              style="width: 21px; height: 21px" />
                          </span>
                          <span class="hide-menu">Mail server</span>
                        </a>
                      </li>
                    </RouterLink>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </aside>
        <section class="ant-layout ninjadash-main-layout">
          <main class="ant-layout-content">
            <div class="news">
              <div
                class="ant-row news-container"
                style="width: 100%; row-gap: 0px">
                <RouterView />
              </div>
            </div>
          </main>
        </section>
      </section>
    </section>
  </div>
</template>
<style scoped>
* {
  font-family: BaiJamjuree !important;
}
.dSWqCR .ant-layout.layout {
  background-color: #f8f9fb !important;
}
.dSWqCR .ant-layout .ant-layout-header {
  padding: 0 30px 0 0;
  height: 72px;
}
.dSWqCR header {
  box-shadow: 0 5px 20px #adb5d905;
  z-index: 998;
  background-color: #ffffff;
}
.ant-layout-header {
  height: 64px;
  padding: 0 50px;
  color: #000000d9;
  line-height: 64px;
  background: #001529;
}
.dSWqCR .ninjadash-header-content {
  height: 100%;
}
.dSWqCR .ninjadash-header-content .ninjadash-header-content__left {
  min-width: 280px;
  padding: 0 20px 0 30px;
  background-color: #ffffff;
}
.dSWqCR
  .ninjadash-header-content
  .ninjadash-header-content__left
  .ninjadash-logo
  img {
  max-width: 70px;
  width: 100%;
}
.dSWqCR .ninjadash-header-content .ninjadash-header-content__right {
  flex: auto;
}
.dSWqCR .ninjadash-nav-actions {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex: auto;
}
.cDrxbr {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: end;
  -webkit-justify-content: flex-end;
  -ms-flex-pack: end;
  justify-content: flex-end;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}
.dSWqCR
  .ninjadash-nav-actions
  .ninjadash-nav-actions__author
  .ninjadash-nav-action-link {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  fill: #8c90a4;
}
.ant-layout,
.ant-layout * {
  box-sizing: border-box;
}
.ant-avatar {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  color: #000000d9;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5715;
  list-style: none;
  font-feature-settings: "tnum";
  position: relative;
  display: inline-block;
  overflow: hidden;
  color: #fff;
  white-space: nowrap;
  text-align: center;
  vertical-align: middle;
  background: #ccc;
  width: 32px;
  height: 32px;
  line-height: 32px;
  border-radius: 50%;
}
.dSWqCR .ninjadash-nav-actions__author--name {
  font-size: 14px;
  display: inline-block;
  font-weight: 500;
  margin: 0 6px 0 10px;
  color: #404040;
}
.cDrxbr .ninjadash-nav-action-link {
  text-decoration: none;
  box-shadow: none;
  padding: 0px 8px;
}
.ant-layout-sider-light {
  background: #fff;
}
.dSWqCR .ant-layout-sider .ant-layout-sider-children .ant-menu {
  font-size: 14px;
  overflow-x: hidden;
  border-right: 0 none;
  background: #ffffff;
}
.menu-root.ant-menu-inline {
  box-shadow: none;
}
.dSWqCR .ninjadash-main-layout {
  margin-left: 280px;
  margin-top: 64px;
  -webkit-transition: 0.3s ease;
  transition: 0.3s ease;
}
.ant-layout {
  display: flex;
  flex: auto;
  flex-direction: column;
  min-height: 0;
  background: #f0f2f5;
}
.news {
  padding-top: 1.5rem;
  background-color: #f0f2f5;
  display: flex;
  flex-direction: row;
}
.news .news-container {
  margin-left: 0 !important;
  margin-right: 0 !important;
  justify-content: center;
  width: 100%;
}
.left-sidebar .scroll-sidebar {
  overflow-y: auto;
  padding: 0 24px;
  height: calc(100vh - 80px);
  border-radius: 7px;
}
ul {
  list-style: none;
  padding-left: 0;
}
a.sidebar-link {
  color: #2a3547;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  font-size: 14px;
  white-space: nowrap;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  line-height: 25px;
  position: relative;
  margin: 0px 0px 2px;
  padding: 10px;
  border-radius: 7px;
  gap: 15px;
  font-weight: 400;
}
a {
  text-decoration: none;
}
span {
  color: black;
  font-weight: bold;
}
li.sidebar-item {
  padding-left: 25px;
}
li.sidebar-item:hover {
  background-color: #00ffff;
  border-radius: 10px;
  background: #8231d305;
  color: #8231d3;
  padding-left: 22px;
}
.ant-popover-inner {
  width: 310px;
  height: 308px;
  border-radius: 0 0 6px 6px;
  background-color: #fff;
  margin-top: -20px;
  margin-left: 1170px;
  box-shadow: 0 5px 20px #9299b820;
}
.ant-popover-inner-content {
  margin: 15px;
}
.user-dropdown {
  max-width: 280px;
}
.user-dropdown__info {
  padding: 0px 25px;
  border-radius: 8px;
  margin-bottom: 12px;
  background: #f4f5f7;
}
h1.kPHihb {
  font-size: 14px;
  margin-bottom: 2px;
  color: #0a0a0a;
  padding-left: 50px;
  margin-top: -45px;
  font-weight: 600;
}
p {
  padding-left: 50px;
  margin-top: -25px;
  font-size: 15px;
}
.unicon {
  font-size: 15px;
  font-weight: 400;
  color: black;
}
li.dropdown-li {
  padding-left: 10px;
}
a.user-dropdown__bottomAction {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-weight: 500;
  text-align: center;
  position: relative;
  width: calc(100% + 30px);
  left: -15px;
  right: -15px;
  height: calc(100% + 15px);
  bottom: -15px;
  border-radius: 0 0 6px 6px;
  padding: 15px 0;
  background: #f4f5f7;
  color: #8c90a4;
  height: 60px;
}
span.anticon.anticon-logout {
  padding: 10px;
}
a.dropdown-toggle.sidebar-link.collapsed {
  padding-left: 35px;
}
a.dropdown-toggle.sidebar-link {
  padding-left: 35px;
}
</style>
