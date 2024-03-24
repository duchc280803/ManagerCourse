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
        <h3>Học viên của tôi</h3>
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
              <th>Học viên</th>
              <th>Lớp</th>
              <th>Số điện thoại</th>
              <th>Giới tính</th>
              <th>Trạng thái</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr style="text-align: center">
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </section>
      <nav aria-label="Page navigation example" style="padding-left: 985px">
        <ul class="pagination">
          <li class="page-item">
            <button class="page-link">Previous</button>
          </li>
          <li class="page-item">
            <a class="page-link" href="#"></a>
          </li>
          <li class="page-item">
            <button class="page-link">Next</button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>
<style scoped>
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
