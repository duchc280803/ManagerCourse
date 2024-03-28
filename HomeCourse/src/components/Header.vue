<script setup>
import { RouterLink, RouterView } from "vue-router";

document.addEventListener("DOMContentLoaded", () => {
  const name = document.getElementById("name");
  const email = document.getElementById("email");
  const phoneNumber = document.getElementById("phoneNumber");
  const content = document.getElementById("content");
  const submit = document.getElementById("submit");
  submit.addEventListener("click", async (e) => {
    e.preventDefault();
    const data = {
      name: name.value,
      email: email.value,
      phoneNumber: phoneNumber.value,
      content: content.value,
    };
    await postGoogleForm(data); // Truyền data vào hàm postGoogleForm
  });

  async function postGoogleForm(data) {
    // Thêm tham số data vào hàm
    const form_url =
      "https://docs.google.com/forms/u/0/d/e/1FAIpQLSdxCOSDfeHqZ8OK1egTUrB5gsLvdFtCiD0_QLvsO8kNBelNag/formResponse";
    const formData = new FormData();
    formData.append("entry.1438368145", data.name);
    formData.append("entry.1205936277", data.email);
    formData.append("entry.1457501686", data.phoneNumber);
    formData.append("entry.1730882537", data.content);
    try {
      const response = await fetch(form_url, {
        method: "POST",
        body: formData,
      });
      if (response.ok) {
        console.log("Form submitted successfully!");
        // Xử lý sau khi gửi form thành công (nếu cần)
      } else {
        console.error("Form submission failed!");
        // Xử lý khi gửi form không thành công (nếu cần)
      }
    } catch (error) {
      console.error("Error submitting form:", error);
      // Xử lý khi có lỗi xảy ra trong quá trình gửi form
    }
  }
});
</script>
<template>
  <header>
    <div class="menu">
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
                      <a href="/about-us">Giới thiệu</a>
                    </li>
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
.menu {
  width: 1519px;
  height: 90px;
  background: #c4e4ff !important;
}
.main-menu {
  position: relative;
}
.text-right {
  text-align: right !important;
}
li.current-menu-item {
  margin-left: 10px;
}
.nav-menu {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex; /* Sử dụng flexbox để căn chỉnh các mục */
}

.nav-menu li {
  flex: 1; /* Mỗi mục sẽ chiếm một phần bằng nhau trong flex container */
  text-align: center; /* Căn giữa nội dung của mỗi mục */
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
.rs-menu-area {
  padding-top: 30px;
}
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #ffffff; /* Màu nền của header */
  z-index: 1000; /* Đảm bảo header hiển thị trên các phần tử khác */
  /* Các thuộc tính khác của header */
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
</style>
