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
        <h3>Khóa học: Java backend</h3>
      </div>
    </div>
    <div class="card-body">
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
              <th>Tên lớp</th>
              <th>Mã môn</th>
              <th>Tên môn</th>
              <th>Điểm</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center">
              <td>1</td>
              <td>Document</td>
              <td>10</td>
              <td>7.0</td>
              <td>Tốt</td>
              <td>Passed</td>
            </tr>
          </tbody>
        </table>
      </section>
      <section class="section-sum">
        <div>
          <span>Điểm trung bình: </span>
        </div>
        <div><span>Thống kê </span></div>
        <section class="section-table">
          <table>
            <thead>
              <tr
                style="
                  background-color: #d5d1defe;
                  height: 52px;
                  text-align: center;
                ">
                <th>Tổng môn chưa học</th>
                <th>Tổng môn passed</th>
                <th>Tổng môn fail</th>
                <th>Tổng môn đang học</th>
              </tr>
            </thead>
            <tbody>
              <tr style="text-align: center">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
          </table>
        </section>
      </section>
    </div>
  </div>
</template>
<style scoped>
.card.border {
  box-shadow: 0 0 30px #523f690d;
  border: 1px solid #523f690d;
  border-radius: 20px;
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
