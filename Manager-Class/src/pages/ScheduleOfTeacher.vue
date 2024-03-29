<script setup>
import { ref, watch } from "vue";

const selectedMonth = ref(3);
const weeks = ref([]);
const wakeUpDays = ref([3, 7, 9]); // Danh sách các ngày lịch dậy

const hienThiLich = () => {
  weeks.value = []; // Xóa dữ liệu cũ
  const soNgay = new Date(2024, selectedMonth.value, 0).getDate(); // Lấy số ngày trong tháng

  let week = []; // Mảng chứa các ngày trong một tuần
  for (let i = 1; i <= soNgay; i++) {
    const ngay = i % 7; // Tính toán ngày trong tuần
    week.push(i); // Thêm ngày vào mảng tuần

    // Nếu đã duyệt hết 7 ngày hoặc đã duyệt hết số ngày trong tháng
    if (ngay === 0 || i === soNgay) {
      weeks.value.push(week); // Thêm mảng tuần vào mảng các tuần
      week = []; // Reset mảng tuần
    }
  }
};

// Gọi hàm hiển thị lịch khi component được mounted
hienThiLich();

const status = 1;
</script>
<template>
  <div
    class="card"
    style="
      width: 1192px;
      margin-left: 30px;
      border-radius: 10px;
      margin-bottom: 30px;
    ">
    <div class="card-header">
      <div class="card-title">
        <h3>Lịch dậy của tôi</h3>
      </div>
    </div>
    <div class="card-body">
      <div class="row">
        <div class="col-lg-6">
          <div class="form-group">
            <label for="thang">Chọn tháng:</label>
            <select
              id="thang"
              class="form-control"
              v-model="selectedMonth"
              @change="hienThiLich">
              <option
                v-for="(month, index) in 12"
                :value="index + 1"
                :key="index">
                {{ "Tháng " + (index + 1) }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="form-group">
            <label for="thang">Chọn tháng:</label>
            <select
              id="thang"
              class="form-control">
              <option
                v-for="(month, index) in 12"
                :value="index + 1"
                :key="index">
                {{ "Tháng " + (index + 1) }}
              </option>
            </select>
          </div>
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
              <th>Thứ Hai</th>
              <th>Thứ Ba</th>
              <th>Thứ Tư</th>
              <th>Thứ Năm</th>
              <th>Thứ Sáu</th>
              <th>Thứ Bảy</th>
              <th>Chủ Nhật</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(week, index) in weeks" :key="index">
              <td
                style="width: 138px; height: 112px"
                v-for="(day, idx) in week"
                :key="idx"
                :class="{
                  'text-danger': idx === 6,
                  'wake-up-day': wakeUpDays.includes(day),
                }">
                <h6>{{ day }}</h6>
                <div
                  class="card wrapper-schedule"
                  style="height: 40px; background-color: beige; border-radius: 15px;">
                  <button style="border-radius: 15px;">
                    <div class="row">
                      <div class="col-6">
                        <div
                          class="card"
                          style="
                            width: 40px;
                            height: 30px;
                            margin-top: px;
                            margin-left: 30px;
                          "
                          :style="{
                            backgroundColor:
                              status === 1 ? '#90D26D' : '#FB6D48',
                          }">
                          20:00
                        </div>
                      </div>
                      <div class="col-6">
                        <div
                          class="card"
                          style="
                            width: 40px;
                            height: 30px;
                            margin-top: 2px;
                            margin-left: -10px;
                          "
                          :style="{
                            backgroundColor:
                              status === 1 ? '#90D26D' : '#FB6D48',
                          }">
                          20:00
                        </div>
                      </div>
                    </div>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  </div>
</template>
<style scoped>
a {
  text-decoration: none;
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
td {
  padding-left: 5px;
}
td {
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
