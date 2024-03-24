<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";
/**
 * Get list study point
 */
const listStudyPoint = ref([]);
const getListStudyPoint = async () => {
  axios
    .get(`http://localhost:8080/api/v1/point/study-point`)
    .then(function (response) {
      listStudyPoint.value = response.data;
    });
};
getListStudyPoint();

/**
 * Create a new study point
 */
const formDataStudyPoint = ref({});
const errorCreateStudyPoint = ref({});
const createStudyPoint = async (id) => {
  Swal.fire({
    title: "Bạn có muốn thêm mới đầu điểm không?",
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
          `http://localhost:8080/api/v1/point/create`,
          formDataStudyPoint.value
        )
        .then(function (response) {
          listStudyPoint.value.push(response.data);
          getListStudyPoint();
        })
        .catch(function (error) {
          errorCreateStudyPoint.value = error.response.data;
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

/**
 * Get list study point
 */
const getStudyPointDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/point/detail?id=${id}`)
    .then(function (response) {
      formDataStudyPoint.value = response.data;
    });
};

/**
 * Update the study point
 */
const errorUpdateStudyPoint = ref({});
const updateStudyPoint = async (id) => {
  Swal.fire({
    title: "Bạn có muốn sửa đầu điểm không?",
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
      var idSubject = window.localStorage.getItem("idSubject");
      await axios
        .put(
          `http://localhost:8080/api/v1/point/update?idStudyPoint=${id}`,
          formDataStudyPoint.value
        )
        .then(function (response) {
          getListStudyPoint(idSubject);
          let modal = document.getElementById("subject-update");
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
          errorUpdateStudyPoint.value = error.response.data;
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
    <div class="card border" style="margin-bottom: 30px">
      <div class="card-header">
        <div class="card-title">
          <h3>Đầu điểm</h3>
        </div>
      </div>
      <div class="card-body" style="height: 630px">
        <div class="row search_table">
          <div class="col-4"></div>
          <div class="col-lg-4"></div>
          <div class="col-2"></div>
          <div class="col-2">
            <button
              data-bs-toggle="modal"
              data-bs-target="#add"
              style="
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
              <tr
                style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
                <th>STT</th>
                <th>Tên đầu điểm</th>
                <th>Trọng số</th>
                <th>Nội dung</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(st, index) in listStudyPoint"
                :key="st.id"
                style="text-align: center">
                <td>{{ index + 1 }}</td>
                <td>{{ st.pointName }}</td>
                <td>{{ st.weight }}</td>
                <td>
                  {{ st.description }}
                </td>
                <td>
                  <span
                    :style="{
                      'background-color': st.status == 1 ? '#9bcf53' : 'red',
                      'border-radius': '5px',
                      color: st.status == 1 ? 'darkgreen' : 'white',
                    }">
                    {{ st.status == 1 ? "Hoạt động" : "Không hoạt động" }}
                  </span>
                </td>
                <td>
                  <button
                    data-bs-toggle="modal"
                    data-bs-target="#update"
                    @click="getStudyPointDetail(st.id)"
                    style="
                      text-decoration: none;
                      background-color: #fc6736;
                      border-radius: 5px;
                      color: black;
                      font-weight: 600;
                    ">
                    Detail
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </section>
      </div>
    </div>
  </div>
  <!-- Modal add-->
  <div
    class="modal fade"
    id="add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm đầu điểm mới</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div
              class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Tên đầu điểm
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên đầu điểm..."
                    v-model="formDataStudyPoint.pointName" />
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.pointName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Trọng số
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Trọng số..."
                    v-model="formDataStudyPoint.weight" />
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.weight
                }}</span>
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
                  <textarea
                    style="margin-left: 15px"
                    type="text"
                    class="form-control an-select"
                    placeholder="Yêu cầu..."
                    v-model="formDataStudyPoint.description"></textarea>
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.description
                }}</span>
              </div>
              <br />
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Hủy
          </button>
          <button
            type="button"
            class="btn btn-primary"
            @click="createStudyPoint()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="update"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Sửa đầu điểm</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row m-auto">
            <div
              class="col-12 row mb-5 m-0 p-0 mb_24 justify-content-around ng-star-inserted">
              <div class="col-4 ctrl_label">
                <div>
                  Tên đầu điểm
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên đầu điểm..."
                    v-model="formDataStudyPoint.pointName" />
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.pointName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Trọng số
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Trọng số..."
                    v-model="formDataStudyPoint.weight" />
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.weight
                }}</span>
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
                  <textarea
                    style="margin-left: 15px"
                    type="text"
                    class="form-control an-select"
                    placeholder="Yêu cầu..."
                    v-model="formDataStudyPoint.description"></textarea>
                </div>
                <span style="color: red">{{
                  errorCreateStudyPoint.description
                }}</span>
              </div>
              <br />
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Trạng thái
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="formDataStudyPoint.status">
                    <option :value="1">Hoạt động</option>
                    <option :value="2">Không hoạt động</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorUpdateStudyPoint.description
                }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Hủy
          </button>
          <button
            type="button"
            class="btn btn-primary"
            @click="updateStudyPoint(formDataStudyPoint.id)">
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
.card.subject {
  border-radius: 40px;
  background-position: center;
  background-size: cover;
  overflow: hidden;
  background-color: burlywood;
}
span.subject-code {
  margin: 5px;
  font-size: 16px;
  font-weight: 600;
}
span.subject-name {
  margin: 5px;
  font-size: 16px;
  font-weight: 600;
}
a.router-link-active {
  text-decoration: none;
  color: black;
}
tr {
  height: 80px;
}
table {
  width: 100%;
}
.section-table {
  max-height: calc(89% - 1.6rem);
  background-color: #fffb;
  margin: 0.8rem auto;
  border-radius: 0.6rem;
  overflow: hidden;
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
.pap {
  padding-top: 20px;
}
</style>
