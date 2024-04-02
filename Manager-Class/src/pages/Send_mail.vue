<script setup>
import axios from "axios";
import Swal from "sweetalert2";
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
const getEmailTemplate = ref({});
const findByEmailTemplate1 = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/templates/${id}`)
    .then(function (response) {
      getEmailTemplate.value = response.data;
    });
};

/**
 * Create a new mail server
 */
const subject = ref("");
const content = ref("");
const typeTemplate = ref("")
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
        subject: subject.value,
        content: content.value,
        typeTemplate: typeTemplate.value
      };
      axios
        .post(`http://localhost:8080/api/v1/templates/create`, formDataEmail)
        .then(function (response) {
          getListEmailTemplate.value.push(response.data);
          findByEmailTemplate();
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

const updateMailServer = async (id) => {
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
      axios
        .put(`http://localhost:8080/api/v1/templates/update?id=${id}`, getEmailTemplate.value)
        .then(function (response) {
          findByEmailTemplate();
          let modal = document.getElementById("update");
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
              <th>Tên mẫu email</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center; padding-top: 5px" v-for="(e, index) in getListEmailTemplate">
              <td>{{ index + 1 }}</td>
              <td>{{ e.subject }}</td>
              <td>
                <button @click="findByEmailTemplate1(e.id)" data-bs-toggle="modal" data-bs-target="#exampleModal" style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                  Xem mẫu
                </button>
                <button @click="findByEmailTemplate1(e.id)" data-bs-toggle="modal" data-bs-target="#update" style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                  Sửa
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  </div>
  <div class="modal fade" id="add" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Thông tin mẫu</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Tiêu đề
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Tiêu đề..." v-model="subject" />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Nội dung
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea type="text" class="form-control an-select" placeholder="Tiêu đề..." v-model="content"
                    style="height: 300px;"></textarea>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_lable">
                <div>Kiểu mẫu<span class="text-danger ng-star-inserted">*</span></div>
              </div>
              <div class="col-7">
                <select class="form-select" aria-label="Default select example" v-model="typeTemplate">
                  <option disabled value="">Chọn mẫu</option>
                  <option :value="1">
                    Mẫu đăng ký tài khoản
                  </option>
                  <option :value="3">
                    Mẫu vào lớp học
                  </option>
                  <option :value="2">
                    Mẫu đăng ký khóa học
                  </option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="createMailServer()">Lưu</button>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="update" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Thông tin mẫu</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Tiêu đề
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Tiêu đề..."
                    v-model="getEmailTemplate.subject" />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Nội dung
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea type="text" class="form-control an-select" placeholder="Tiêu đề..."
                    v-model="getEmailTemplate.content" style="height: 300px;"></textarea>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_lable">
                <div>Kiểu mẫu<span class="text-danger ng-star-inserted">*</span></div>
              </div>
              <div class="col-7">
                <select class="form-select" aria-label="Default select example" v-model="getEmailTemplate.typeTemplate"
                  disabled>
                  <option disabled value="">Chọn mẫu</option>
                  <option :value="1">
                    Mẫu đăng ký tài khoản
                  </option>
                  <option :value="3">
                    Mẫu vào lớp học
                  </option>
                  <option :value="2">
                    Mẫu đăng ký khóa học
                  </option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="updateMailServer(getEmailTemplate.id)">Lưu</button>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Thông tin mẫu</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Tiêu đề
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input type="text" class="form-control an-select" placeholder="Tiêu đề..."
                    v-model="getEmailTemplate.subject" disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Nội dung
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <textarea disabled type="text" class="form-control an-select" placeholder="Tiêu đề..."
                    v-model="getEmailTemplate.content" style="height: 300px;"></textarea>
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_lable">
                <div>Kiểu mẫu<span class="text-danger ng-star-inserted">*</span></div>
              </div>
              <div class="col-7">
                <select class="form-select" aria-label="Default select example" v-model="getEmailTemplate.typeTemplate"
                  disabled>
                  <option disabled value="">Chọn mẫu</option>
                  <option :value="1">
                    Mẫu đăng ký tài khoản
                  </option>
                  <option :value="3">
                    Mẫu vào lớp học
                  </option>
                  <option :value="2">
                    Mẫu đăng ký khóa học
                  </option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
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

.pap {
  padding-top: 20px;
}
</style>
