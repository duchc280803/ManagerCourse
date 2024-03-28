<script setup>
import { ref, watch, computed } from "vue";
import axios from "axios";
import Swal from "sweetalert2";
var role = window.localStorage.getItem("role");
const token = window.localStorage.getItem("token");
const config = {
  headers: {
    Authorization: "Bearer " + token,
  },
};

const listClass = ref([]);
const listClassRole = ref([]);
const listSchedule = ref([]);
const subjectByClass = ref([]);
const subjectByClassForSchedule = ref([]);

var pageNumber = 0;
var pageSize = 6;

const selectedClassName = ref("");
const selectedSubjectId = ref("");

const fetchClassData = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/v1/class/findAllClassName"
    );
    listClass.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassData();

const fetchClassDataForRole = async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/v1/class/findAllClassName",
      config
    );
    listClassRole.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassDataForRole();

const fetchScheduleData = async () => {
  try {
    const classId = selectedClassName.value?.id || "";
    const subjectId = selectedSubjectId.value || "";
    const response = await axios.get(
      `http://localhost:8080/api/v1/schedule/show?pageNumber=${pageNumber}&pageSize=${pageSize}&id=${classId}&idSubject=${subjectId}`,
      config
    );
    listSchedule.value = response.data;
  } catch (error) {
    console.error("Error fetching schedule data:", error);
  }
};

const futureScheduleList = computed(() => {
  const currentDate = new Date();
  return listSchedule.value.filter((s) => {
    // Chỉ hiển thị các mục với ngày sau ngày hiện tại
    return new Date(s.dateLean) > currentDate;
  });
});

const fetchSubjectsByClass = async () => {
  try {
    const classId = selectedClassName.value?.id || "";
    const response = await axios.get(
      `http://localhost:8080/api/v1/subject/subject-by-class?id=${classId}`
    );
    subjectByClass.value = response.data;
  } catch (error) {
    console.error("Error fetching subjects by class:", error);
  }
};

const nextPage = () => {
  pageNumber++;
  fetchScheduleData();
};

const previousPage = () => {
  if (pageNumber > 0) {
    pageNumber--;
    fetchScheduleData();
  }
};

watch(selectedClassName, () => {
  fetchSubjectsByClass();
  fetchScheduleData();
});

watch(selectedSubjectId, fetchScheduleData);

const classRoomName = ref([]);
const fetchClassRoomNameData = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/class-room/classroom-name`
    );
    classRoomName.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
fetchClassRoomNameData();

const idClass = ref("");
const fetchSubjectsByClassForSchedule = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/subject/subject-by-class-schedule?id=${idClass.value.id}`
    );
    subjectByClassForSchedule.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
watch(idClass, () => {
  fetchSubjectsByClassForSchedule();
});
const idSubject = ref("");
const idClassRoom = ref("");
const day = ref("");
const timeStart = ref("");
const timeEnd = ref("");
const errorSchedule = ref({});
const createSchedule = async () => {
  Swal.fire({
    title: "Bạn có muốn thêm mới lịch học không?",
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
      const scheduleData = {
        idClass: idClass.value.id,
        idSubject: idSubject.value,
        idClassRoom: idClassRoom.value,
        day: day.value,
        timeEnd: timeEnd.value,
        timeStart: timeStart.value,
      };
      axios
        .post(`http://localhost:8080/api/v1/schedule/create`, scheduleData)
        .then(function (response) {
          listSchedule.value.push(response.data);
          fetchScheduleData();
          let modal = document.getElementById("schedule-add");
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
          errorSchedule.value = error.response.data;
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
 * Get details of a schedule
 */
const scheduleUpdate = ref({});
const scheduleDetail = async (id) => {
  axios
    .get(`http://localhost:8080/api/v1/schedule/detail?id=${id}`)
    .then(function (response) {
      scheduleUpdate.value = response.data;
    });
};

const subjectByClassUpdate = ref([]);
const getListSubjectByClassUpdate = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/subject/subject-by-class?id=${scheduleUpdate.value.idClass}`
    );
    subjectByClassUpdate.value = response.data;
  } catch (error) {
    console.error("Error fetching class data:", error);
  }
};
watch(scheduleUpdate, () => {
  getListSubjectByClassUpdate();
});

/**
 * Update the class
 */
const errorScheduleUpdate = ref({});
const updateSchedule = async (id) => {
  Swal.fire({
    title: "Bạn có muốn cập nhập lịch học không?",
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
          `http://localhost:8080/api/v1/schedule/update?id=${id}`,
          scheduleUpdate.value
        )
        .then(function (response) {
          fetchScheduleData();
          let modal = document.getElementById("schedule-update");
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
          errorScheduleUpdate.value = error.response.data;
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
      margin-left: 30px;
      height: 605px;
      border-radius: 10px;
      margin-bottom: 30px;
    ">
    <div class="card-header">
      <div class="card-title">
        <h3>Lịch học</h3>
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
            <select
              class="form-select"
              aria-label="Default select example"
              v-model="selectedClassName">
              <option value="" disabled>Chọn lớp</option>
              <option
                :value="cl"
                :key="index"
                v-for="(cl, index) in listClassRole">
                {{ cl.className }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="input-group-prepend position-relative">
            <select
              class="form-select"
              aria-label="Default select example"
              v-model="selectedSubjectId">
              <option value="" disabled>Chọn môn</option>
              <option :value="s.id" v-for="(s, index) in subjectByClass">
                {{ s.subjectName }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-2"></div>
        <div class="col-2" v-show="role === 'ADMIN'">
          <button
            data-bs-toggle="modal"
            data-bs-target="#schedule-add"
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
              <th>Tên môn</th>
              <th>Tên phòng</th>
              <th>Tầng</th>
              <th>Thứ</th>
              <th>Ngày</th>
              <th>Thời gian</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(s, index) in futureScheduleList"
              :key="index"
              style="text-align: center">
              <td>{{ index + 1 + pageNumber * pageSize }}</td>
              <td>{{ s.subjectName }}</td>
              <td>{{ s.classRoomName }}</td>
              <td>{{ s.floor }}</td>
              <td>
                Thứ {{ s.dateLean ? new Date(s.dateLean).getDay() + 1 : "" }}
              </td>
              <td>{{ s.dateLean }}</td>
              <td>{{ s.timeStart }} - {{ s.timeEnd }}</td>
              <td>
                <button
                  data-bs-toggle="modal"
                  data-bs-target="#schedule-update"
                  @click="scheduleDetail(s.id)"
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
    id="schedule-add"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Thêm lịch học</h5>
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
                  Lớp học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="idClass">
                    <option value="" disabled>Chọn lớp</option>
                    <option
                      :value="cl"
                      :key="index"
                      v-for="(cl, index) in listClass">
                      {{ cl.className }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{ errorSchedule.idClass }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Môn học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="idSubject">
                    <option value="" disabled>Chọn môn</option>
                    <option :value="s.id" v-for="(s, index) in subjectByClassForSchedule">
                      {{ s.subjectName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{ errorSchedule.idSubject }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Phòng học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="idClassRoom">
                    <option value="" disabled>Chọn phòng học</option>
                    <option
                      :value="clr.id"
                      v-for="(clr, index) in classRoomName">
                      {{ clr.classRoomName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{ errorSchedule.idClassRoom }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="day">
                    <option value="" disabled>Chọn ngày học</option>
                    <option :value="1">Thứ 2, Thứ 4, Thứ 6</option>
                    <option :value="2">Thứ 3, Thứ 5, Thứ 7</option>
                  </select>
                </div>
                <span style="color: red">{{ errorSchedule.day }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian bắt đầu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="time"
                    class="form-control an-select"
                    v-model="timeStart" />
                </div>
                <span style="color: red">{{ errorSchedule.timeStart }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian kết thúc
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="time"
                    class="form-control an-select"
                    v-model="timeEnd" />
                </div>
                <span style="color: red">{{ errorSchedule.timeEnd }}</span>
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
            @click="createSchedule()">
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal update-->
  <div
    class="modal fade"
    id="schedule-update"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content lg" style="width: 950px; margin-left: -200px">
        <div class="modal-header">
          <h5 class="modal-title">Sửa lịch học</h5>
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
                  Lớp học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    disabled
                    class="form-select"
                    aria-label="Default select example"
                    v-model="scheduleUpdate.idClass">
                    <option :value="cl.id" v-for="(cl, index) in listClass">
                      {{ cl.className }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorScheduleUpdate.idClass
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Môn học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    disabled
                    class="form-select"
                    aria-label="Default select example"
                    v-model="scheduleUpdate.idSubject">
                    <option value="" disabled>Chọn môn</option>
                    <option
                      :value="s.id"
                      v-for="(s, index) in subjectByClassUpdate">
                      {{ s.subjectName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorScheduleUpdate.idSubject
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Phòng học
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="scheduleUpdate.idClassRoom">
                    <option value="" disabled>Chọn phòng học</option>
                    <option
                      :value="clr.id"
                      v-for="(clr, index) in classRoomName">
                      {{ clr.classRoomName }}
                    </option>
                  </select>
                </div>
                <span style="color: red">{{
                  errorScheduleUpdate.idClassRoom
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <select
                    class="form-select"
                    aria-label="Default select example"
                    v-model="scheduleUpdate.day">
                    <option value="" disabled>Chọn ngày học</option>
                    <option :value="1">Thứ 2, Thứ 4, Thứ 6</option>
                    <option :value="2">Thứ 3, Thứ 5, Thứ 7</option>
                  </select>
                </div>
                <span style="color: red">{{ errorScheduleUpdate.day }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian bắt đầu
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="time"
                    class="form-control an-select"
                    v-model="scheduleUpdate.timeStart" />
                </div>
                <span style="color: red">{{
                  errorScheduleUpdate.timeStart
                }}</span>
              </div>
              <div class="pap"></div>
              <div class="col-4 ctrl_label">
                <div>
                  Thời gian kết thúc
                  <span class="text-danger ng-star-inserted">*</span>
                </div>
              </div>
              <div class="col-7">
                <div>
                  <input
                    type="time"
                    class="form-control an-select"
                    v-model="scheduleUpdate.timeEnd" />
                </div>
                <span style="color: red">{{
                  errorScheduleUpdate.timeEnd
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
            @click="updateSchedule(scheduleUpdate.id)">
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
  overflow: auto;
  overflow: overlay;
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
