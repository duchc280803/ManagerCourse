<script setup>
import axios from "axios";
import { ref } from "vue";
const tieuDe = ref("");
const khoaHoc = ref("");
const hoTen = ref("");
const tieuDeThongTin = ref("");
const noiDungThongTin = ref("");
const getListCourseName = ref([]);
const findByCourseName = async () => {
  axios
    .get(`http://localhost:8080/api/v1/course/course-name`)
    .then(function (response) {
      getListCourseName.value = response.data;
    });
};
findByCourseName();
</script>
<template>
  <div class="card" style="margin: 20px" id="emailForm">
    <div class="form-group">
      <label for="" class="form-lable">Tiêu đề</label>
      <input
        type="text"
        class="form-control"
        placeholder="Tiêu đề ..."
        v-model="tieuDe" />
    </div>
    <div class="form-group">
      <label for="" class="form-lable">Họ tên</label>
      <input
        type="text"
        class="form-control"
        placeholder="Họ tên ..."
        v-model="hoTen" />
    </div>
    <div class="form-group">
      <label for="" class="form-lable">Khóa học</label>
      <select
        class="form-select" v-model="khoaHoc">
        <option
          v-for="item in getListCourseName"
          :key="item"
          :value="item.courseName">
          {{ item.courseName }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <label for="" class="form-lable">Tiêu đề thông tin</label>
      <input
        type="text"
        class="form-control"
        placeholder="Tiêu đề thông tin ..."
        v-model="tieuDeThongTin" />
    </div>
    <div class="form-group">
      <label for="" class="form-lable">Nội dung thông tin</label>
      <textarea
        type="text"
        class="form-control"
        placeholder="Nội dung thông tin ..."
        v-model="noiDungThongTin"></textarea>
    </div>
    <button class="send-email" @click="xemEmail">Xem email</button>
  </div>
  <table class="m_-6748071709643648038container" cellpadding="10">
    <tbody>
      <tr>
        <td align="center" width="50%">
          <img
            src="https://static.topcv.vn/company_logos/YjF8IUn9DGHx3HGzTUPki8DycZsRRqLA_1661485501____8e43ee2aad361f320c7d0a13becc6a92.png"
            alt="Background Image"
            width="200px"
            class="CToWUd"
            data-bit="iit" />
        </td>
      </tr>
      <tr>
        <td colspan="2" class="m_-6748071709643648038header">
          <h1 style="text-align: center">
            {{ tieuDe }}
          </h1>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <h3>
            Chào mừng bạn
            <a style="color: rgb(145, 33, 33)">{{ hoTen }}</a> đăng ký khóa học
            {{ khoaHoc }} của Công ty cổ phần học viện NEWAVE SOLUTIONS
          </h3>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="m_-6748071709643648038course-details">
          <h2>Thông tin khóa học</h2>
          <ul>
            <li>Thời gian học: 4 tháng</li>
            <li>Ngày nhập học: 28/08/2023</li>
            <li>Ngày dự kiến kết thúc: 28/12/2023</li>
            <li>Hình thức học: Offline</li>
            <li>
              Địa chỉ: Tòa nhà Mitec, Đường Dương Đình Nghệ, Phường Yên Hoà, Quận Cầu Giấy, Hà Nội, Việt Nam
            </li>
          </ul>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="m_-6748071709643648038contact-info">
          <p>
            Mọi thắc mắc Học viên liên hệ với Admin của học viện qua số điện
            thoại: 0333747897 để được hỗ trợ sớm nhất.
          </p>
          <p>
            Dưới đây là biên bản cam kết và nội quy của Học Viện. Học viên vui
            lòng đọc kỹ các quy định của Học Viện và thực hiện nghiêm túc các
            quy định dưới.
          </p>
          <p>
            <b
              >Lưu ý: Các trường hợp vi phạm sẽ xử lý đúng theo quy định của Học
              Viện.</b
            >
          </p>
          <p>Trân trọng!</p>
        </td>
      </tr>
    </tbody>
  </table>
</template>
<style scoped>
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
.msg-6748071709643648038 img {
  width: 200px;
  height: auto;
  opacity: 0.9;
  max-height: 48px;
}
.msg-6748071709643648038 .m_-6748071709643648038header {
  text-align: center;
  margin-bottom: 10px;
  color: #321811;
  font-weight: bold;
}
.msg-6748071709643648038 .m_-6748071709643648038intro {
  margin-bottom: 10px;
}
.hq {
  margin: 15px 0;
  clear: both;
}
.hp {
  width: 100%;
  border-top: 1px dotted #d8d8d8;
}
.ho > .aVW {
  -webkit-font-smoothing: antialiased;
  font-family: "Google Sans", Roboto, RobotoDraft, Helvetica, Arial, sans-serif;
  font-size: 0.875rem;
  letter-spacing: normal;
  height: 20px;
  line-height: 20px;
}
.ho .aZi {
  position: absolute;
  top: -1px;
  right: 0;
}
.aQH {
  margin-left: -16px;
  margin-bottom: -16px;
}
.aZo {
  display: block;
  float: left;
  margin: 0 0 16px 16px;
  height: 120px;
  width: 180px;
  position: relative;
}
.aZK {
  height: 0;
  overflow: hidden;
  clear: both;
}
.hx .h7 .gA {
  background: transparent;
  border-top: none;
}
.gB.xu {
  border-top: 0;
  padding: 0;
}
.hx .h7 .mD {
  padding: 2px 8px 3px 8px;
}
.mD {
  white-space: nowrap;
  padding: 4px 8px;
  cursor: pointer;
  color: #222;
  vertical-align: baseline;
}
mL,
.mK,
.mI {
  height: 20px;
  width: 20px;
}
.form-group {
  margin: 5px;
}
a {
  text-decoration: none;
}
.send-email {
  box-shadow: 0px 28px 50px 0px rgba(0, 0, 0, 0.05);
  outline: none;
  border: none;
  padding: 12px 40px;
  border-radius: 13px;
  display: inline-block;
  font-size: 16px;
  font-weight: 500;
  color: #ffffff;
  background: #ff5421;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
  text-transform: uppercase;
  width: 200px;
  margin-left: 958px;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
