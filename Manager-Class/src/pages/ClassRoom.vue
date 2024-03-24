<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import Swal from "sweetalert2";

/**
 * Get list classroom
 */
const listClassRoom = ref([]);
var pageNumber = 0;
var pageSize = 6;
const fetchClassRoomData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class-room/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
    listClassRoom.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassRoomData();
const nextPage = function () {
  pageNumber++;
  fetchClassRoomData();
};

const previousPage = function () {
  if (pageNumber > 0) {
    pageNumber--;
    fetchClassRoomData();
  }
};

/**
 * Search
 */
const search = ref("");
const searchClass = async () => {
  axios
    .get(
      `http://localhost:8080/api/v1/class-room/search?classRoomName=${search.value}`
    )
    .then(function (response) {
      listClassRoom.value = response.data;
    });
};
watch(search, () => {
  searchClass();
});

/**
 * Create a new class
 */
const codeClassRoom = ref("CLASS_ROOM_CODE" + new Date().getTime());
const classRoomName = ref("");
const typeClassRoom = ref("");
const quantityStudent = ref("");
const floor = ref("");
const status = ref(1);

const errorCreateClass = ref({});
const createClassRoom = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới phòng học không?",
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
      const classData = {
        codeClassRoom: codeClassRoom.value,
        classRoomName: classRoomName.value,
        quantityStudent: quantityStudent.value,
        typeClassRoom: typeClassRoom.value,
        floor: floor.value,
        status: status.value,
      };
      axios
        .post(`http://localhost:8080/api/v1/class-room/create`, classData)
        .then(function (response) {
          listClassRoom.value.push(response.data);
          fetchClassRoomData();
          let modal = document.getElementById("classroom-add");
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
          errorCreateClass.value = error.response.data;
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
 * Get details of a class room
 */
const classRoomUpdate = ref({});
const classRoomDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/class-room/detail?id=${id}`)
    .then(function (response) {
      classRoomUpdate.value = response.data;
    });
};
/**
 * Update the class
 */
const errorUpdateClassRoom = ref({});
const updateclassRoom = async (id) => {
  Swal.fire({
    title: "Bạn có muốn cập nhập phòng học không?",
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
      await axios
        .put(
          `http://localhost:8080/api/v1/class-room/update?id=${id}`,
          classRoomUpdate.value
        )
        .then(function (response) {
          fetchClassRoomData();
          let modal = document.getElementById("classroom-update");
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
          errorUpdateClassRoom.value = error.response.data;
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
  <div
    class="card"
    style="
      width: 1192px;
      height: 585px;
      margin-left: 30px;
      border-radius: 10px;
      margin-bottom: 30px;
    ">
    <div class="card-header">
      <div class="card-title">
        <h3>Phòng học</h3>
      </div>
    </div>
    <div class="row search_table">
      <div class="col-4">
        <div
          class="input-group-prepend position-relative"
          style="margin-left: 20px; margin-top: 20px"></div>
      </div>
      <div class="col-lg-4"></div>
      <div class="col-2"></div>
      <div class="col-2"></div>
    </div>
    <div class="card-body">
      <div class="row search_table">
        <div class="col-4">
          <div class="input-group-prepend position-relative">
            <input
              type="text"
              placeholder="Tìm kiếm"
              class="form-control search"
              v-model.trim="search" />
          </div>
        </div>
        <div class="col-lg-4">
          <button
            style="
              text-decoration: none;
              background-color: #fc6736;
              border-radius: 5px;
              color: black;
              margin-left: 25px;
              margin-top: 5px;
              font-weight: 600;
            ">
            Làm mới
          </button>
        </div>
        <div class="col-2"></div>
        <div class="col-2">
          <button
            data-bs-toggle="modal"
            data-bs-target="#classroom-add"
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
              <th>Mã phòng</th>
              <th style="width: 100px">Tên phòng</th>
              <th style="width: 100px">Loại phòng học</th>
              <th style="width: 100px">Số lượng người</th>
              <th>Tầng</th>
              <th style="width: 100px">Trạng thái</th>
              <th style="width: 100px">Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center" v-for="(cl, index) in listClassRoom">
              <td>{{ index + 1 + pageNumber * pageSize }}</td>
              <td>{{ cl.codeClassRoom }}</td>
              <td>{{ cl.classRoomName }}</td>
              <td>
                {{ cl.typeClassRoom == 1 ? "Phòng bình thường" : "Phòng vip" }}
              </td>
              <td>{{ cl.quantityStudent }}</td>
              <td>{{ cl.floor == 1 ? "Tầng 1" : 2 ? "Tầng 2" : "Tầng 3" }}</td>
              <td>
                <span
                  :style="{
                    'background-color': cl.status == 1 ? '#9bcf53' : 'red',
                    'border-radius': '5px',
                    color: cl.status == 1 ? 'darkgreen' : 'white',
                  }">
                  {{ cl.status == 1 ? "Đang mở" : "Đóng" }}
                </span>
              </td>
              <td>
                <button
                  data-bs-toggle="modal"
                  data-bs-target="#classroom-update"
                  @click="classRoomDetail(cl.id)"
                  style="
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
      <nav aria-label="Page navigation example" style="padding-left: 985px">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link" @click="previousPage()">Previous</button>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">{{ pageNumber + 1 }}</a>
          </li>
          <li class="page-item">
            <button class="page-link" @click="nextPage()">Next</button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
  <!-- Modal add-->
  <div
    class="modal fade"
    id="classroom-add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm lịch học mới</h5>
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
                  Mã phòng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã phòng..."
                    v-model="codeClassRoom"
                    disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên phòng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên phòng..."
                    v-model="classRoomName" />
                </div>
                <span style="color: red">{{
                  errorCreateClass.classRoomName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Loại phòng học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="typeClassRoom">
                    <option value="" disabled>Chọn loại phòng học</option>
                    <option :value="1">Phòng thường</option>
                    <option :value="2">Phòng vip</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorCreateClass.typeClassRoom
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng người
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng người..."
                    v-model="quantityStudent" />
                </div>
                <span style="color: red">{{
                  errorCreateClass.quantityStudent
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>Tầng</div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="floor">
                    <option value="" disabled>Chọn tầng</option>
                    <option :value="1">Tầng 1</option>
                    <option :value="2">Tầng 2</option>
                    <option :value="3">Tầng 3</option>
                  </select>
                </div>
                <span style="color: red">{{ errorCreateClass.floor }}</span>
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
            @click="createClassRoom()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="classroom-update"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm lịch học mới</h5>
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
                  Mã phòng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Mã phòng..."
                    v-model="classRoomUpdate.codeClassRoom"
                    disabled />
                </div>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Tên phòng
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="text"
                    class="form-control an-select"
                    placeholder="Tên phòng..."
                    v-model="classRoomUpdate.classRoomName" />
                </div>
                <span style="color: red">{{
                  errorUpdateClassRoom.classRoomName
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Loại phòng học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classRoomUpdate.typeClassRoom">
                    <option value="" disabled>Chọn loại phòng học</option>
                    <option :value="1">Phòng thường</option>
                    <option :value="2">Phòng vip</option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorUpdateClassRoom.typeClassRoom
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Số lượng người
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="number"
                    class="form-control an-select"
                    placeholder="Số lượng người..."
                    v-model="classRoomUpdate.quantityStudent" />
                </div>
                <span style="color: red">{{
                  errorUpdateClassRoom.quantityStudent
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>Tầng</div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="classRoomUpdate.floor">
                    <option value="" disabled>Chọn tầng</option>
                    <option :value="1">Tầng 1</option>
                    <option :value="2">Tầng 2</option>
                    <option :value="3">Tầng 3</option>
                  </select>
                </div>
                <span style="color: red">{{ errorUpdateClassRoom.floor }}</span>
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
            @click="updateclassRoom(classRoomUpdate.id)">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.pap {
  padding: 15px;
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
* {
  font-family: BaiJamjuree !important;
}
</style>
