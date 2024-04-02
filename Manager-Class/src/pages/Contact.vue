<script setup>
import { ref } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

const listContact = ref([]);
const contact = ref("");

const selectContact = async () => {
  axios
    .get(`http://localhost:8080/api/v1/contact/show`)
    .then(function (response) {
      listContact.value = response.data;
    });
};
selectContact();

const findByContact = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/contact/find-contact?id=${id}`)
    .then(function (response) {
      contact.value = response.data;
    });
};

const getListCourseName = ref([]);
const findByCourseName = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/course-name`)
    .then(function (response) {
      getListCourseName.value = response.data;
    });
};
findByCourseName();

const status = ref("");
const courseName = ref("");
const convertStatusContact = async () => {
  Swal.fire({
    title: "Bạn có muốn chuyển đổi trạng thái contact không?",
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
      axios
        .post(
          `http://localhost:8080/api/v1/contact/convert?id=${contact.value.id}&status=${status.value}&course=${courseName.value}`
        )
        .then(function (response) {
          console.log(status.value);
          selectContact();
          let modal = document.getElementById("exampleModal");
          if (modal) {
            modal.classList.remove("show");
            modal.setAttribute("aria-hidden", "true");
            modal.style.display = "none";
            let modalBackdrop =
              document.getElementsByClassName("modal-backdrop")[0];
            if (modalBackdrop) {
              modalBackdrop.parentNode.removeChild(modalBackdrop);
            }
          }
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
              <tr style="
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
                <th style="width: 100px">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr style="text-align: center" v-for="(c, index) in listContact">
                <td>{{ index + 1 }}</td>
                <td>{{ c.fullName }}</td>
                <td>{{ c.phoneNumber }}</td>
                <td>{{ c.email }}</td>
                <td>{{ c.message }}</td>
                <td>
                  {{
                c.status == 1
                  ? "Chờ xác nhận"
                  : c.status == 2
                    ? "Chờ xử lý"
                    : c.status == 3
                      ? "Không nghe máy"
                      : "Học viên"
              }}
                </td>
                <td>
                  <button @click="findByContact(c.id)" data-bs-toggle="modal" data-bs-target="#exampleModal" style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Chuyển đổi
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </section>
      </div>
    </div>
  </div>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">
            Chuyển đổi thông tin
          </h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <select class="form-select" aria-label="Default select example" v-model="status">
            <option value="" disabled>Chọn trạng thái để chuyển đổi</option>
            <option :value="2">Đang xử lý</option>
            <option :value="3">Không nghe máy</option>
            <option :value="4">Học viên</option>
          </select>
          <div class="pap"></div>
          <select v-if="status == 4" class="form-select" aria-label="Default select example" v-model="courseName">
            <option disabled value="">Khóa học</option>
            <option v-for="(cs, index) in getListCourseName" :key="cs" :value="cs.courseName">
              {{ cs.courseName }}
            </option>
          </select>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Close
          </button>
          <button type="button" class="btn btn-primary" @click="convertStatusContact()">
            Lưu
          </button>
        </div>
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
</style>
