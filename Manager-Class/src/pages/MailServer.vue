<script setup>
import axios from "axios";
import Swal from "sweetalert2";
import { ref } from "vue";

const getConnect = ref(false);
const testConnect = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/email-server/test-connection/${id}`)
    .then(function (response) {
      getConnect.value = response.data;
      getAllEmailServer()
    });
};

const listAllEmailServer = ref([]);
const getAllEmailServer = async () => {
  axios
    .get(`http://localhost:8080/api/v1/email-server/mail-server`)
    .then(function (response) {
      listAllEmailServer.value = response.data;
    });
};
getAllEmailServer()

/**
 * Create a new mail server
 */
const host = ref("");
const port = ref("");
const username = ref("");
const password = ref("");
const createMailServer = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới môn học không?",
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
      const formDataEmail = {
        host: host.value,
        port: port.value,
        username: username.value,
        password: password.value
      };
      axios
        .post(`http://localhost:8080/api/v1/email-server/create`, formDataEmail)
        .then(function (response) {
          listAllEmailServer.value.push(response.data);
          getAllEmailServer();
          let modal = document.getElementById("add");
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
        })
        .catch(function (error) {
          errorCreateSubject.value = error.response.data;
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
  <div class="card" style="margin: 20px" id="emailForm">
    <div class="card-body">
      <div class="row search_table">
        <div class="col-4">
        </div>
        <div class="col-lg-4">
        </div>
        <div class="col-2"></div>
        <div class="col-2">
          <button data-bs-toggle="modal" data-bs-target="#add" style="
                text-decoration: none;
                background-color: #fc6736;
                border-radius: 5px;
                color: black;
                margin-top: 5px;
                font-weight: 600;
              ">
            Thêm mới
          </button>
        </div>
      </div>
      <section class="section-table">
        <table>
          <thead>
            <tr style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
              <th>STT</th>
              <th>Email</th>
              <th>Trạng thái</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center; padding-top: 5px" v-for="(e, index) in listAllEmailServer">
              <td>{{ index + 1 }}</td>
              <td>{{ e.username }}</td>
              <td>{{ e.status == 1 ? "Hoạt động" : "Không hoạt động" }}</td>
              <td>
                <button @click="testConnect(e.id)" style="
                       text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                  Test connect
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  </div>
  <!-- Modal add-->
  <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm mới email server</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Host
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Post..." v-model="host" />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Port
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="number" class="form-control an-select" placeholder="Port..." v-model="port" />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Email
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="email" class="form-control an-select" placeholder="email..."
                    v-model="username">
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Password
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <input type="text" class="form-control an-select" v-model="password" placeholder="Password" />
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button type="button" class="btn btn-primary" @click="createMailServer()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.pap {
  padding-top: 20px;
}
table {
  width: 100%;
}
.section-table {
 
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: hidden;
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
</style>
