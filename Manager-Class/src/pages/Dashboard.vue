<template>
  <!-- Main dashboard content-->
  <div class="container-xl p-5">
    <!-- Colored status cards-->
    <div class="row gx-5">
      <div class="col-xxl-3 col-md-6 mb-5">
        <div class="card card-raised bg-primary text-white">
          <div class="card-body px-4">
            <div class="justify-content-between align-items-center mb-2">
              <div class="text-white">
                <h3>Tổng sinh viên</h3>
              </div>
            </div>
            <div class="card-text">
              <div class="d-inline-flex align-items-center"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-md-6 mb-5">
        <div class="card card-raised bg-warning text-white">
          <div class="card-body px-4">
            <div class="justify-content-between align-items-center mb-2">
              <div class="text-white">
                <h3>Tổng giảng viên</h3>
              </div>
              <div class="icon-circle bg-white-50 text-warning"></div>
            </div>
            <div class="card-text">
              <div class="d-inline-flex align-items-center"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-md-6 mb-5">
        <div class="card card-raised bg-secondary text-white">
          <div class="card-body px-4">
            <div class="justify-content-between align-items-center mb-2">
              <div class="text-white">
                <h3>Tổng khóa học</h3>
              </div>
              <div class="icon-circle bg-white-50 text-secondary"></div>
            </div>
            <div class="card-text">
              <div class="d-inline-flex align-items-center"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-md-6 mb-5">
        <div class="card card-raised bg-info text-white">
          <div class="card-body px-4">
            <div class="justify-content-between align-items-center mb-2">
              <div class="text-white">
                <h3>Doanh số</h3>
              </div>
              <div class="icon-circle bg-white-50 text-info"></div>
            </div>
            <div class="card-text">
              <div class="d-inline-flex align-items-center"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="card card-raised">
      <div class="card-header bg-primary text-white px-4">
        <div class="d-flex justify-content-between align-items-center">
          <div class="me-4">
            <h2 class="card-title text-white mb-0">Thông tin khóa học</h2>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">Tải xuống</i>
            </button>
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">In</i>
            </button>
          </div>
        </div>
      </div>
      <div class="card-body p-4">
        <!-- Simple DataTables example-->
        <table class="table">
          <thead>
            <tr>
              <th>Tên khóa học</th>
              <th>Mô tả</th>
              <th>Đối tượng</th>
              <th>Thời lượng</th>
              <th>Hình thức học</th>
              <th>Giảng viên</th>
              <th>Chi phí</th>
              <th>Nội dung học</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Web Fullstack</td>
              <td>Học</td>
              <td>Sinh viên, học sinh</td>
              <td>35 buổi</td>
              <td>Tại học viện</td>
              <td>Hoàng Công Đức</td>
              <td>8.000.000</td>
              <td>Web</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <br />
    <div class="card card-raised">
      <div class="card-header bg-primary text-white px-4">
        <div class="d-flex justify-content-between align-items-center">
          <div class="me-4">
            <h2 class="card-title text-white mb-0">Thông tin giảng viên</h2>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">Tải xuống</i>
            </button>
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">In</i>
            </button>
          </div>
        </div>
      </div>
      <div class="card-body p-4">
        <!-- Simple DataTables example-->
        <table class="table">
          <thead>
            <tr>
              <th>Họ và tên</th>
              <th>Giới tính</th>
              <th>Tuổi</th>
              <th data-type="date" data-format="YYYY/MM/DD">Năm sinh</th>
              <th>Giảng dạy</th>
              <th>Địa chỉ</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Hoàng Công Đức</td>
              <td>Nam</td>
              <td>20</td>
              <td>2003/08/28</td>
              <td>Java backend</td>
              <td>Hà nội</td>
              <td>
                <button class="btn btn-primary">Xem chi tiết</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <br />
    <div class="card card-raised">
      <div class="card-header bg-primary text-white px-4">
        <div class="d-flex justify-content-between align-items-center">
          <div class="me-4">
            <h2 class="card-title text-white mb-0">Thông tin sinh viên</h2>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">Tải xuống</i>
            </button>
            <button class="btn btn-lg btn-text-white btn-icon" type="button">
              <i class="material-icons">In</i>
            </button>
          </div>
        </div>
      </div>
      <div class="card-body p-4">
        <!-- Simple DataTables example-->
        <table class="table">
          <thead>
            <tr>
              <th>Họ và tên</th>
              <th>Giới tính</th>
              <th>Tuổi</th>
              <th data-type="date" data-format="YYYY/MM/DD">Năm sinh</th>
              <th>Khóa học</th>
              <th data-type="date" data-format="YYYY/MM/DD">Ngày vào học</th>
              <th data-type="date" data-format="YYYY/MM/DD">Ngày dừng học</th>
              <th>Địa chỉ</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Hoàng Công Đức</td>
              <td>Nam</td>
              <td>20</td>
              <td>2003/08/28</td>
              <td>Java backend</td>
              <td>2023/09/01</td>
              <td>2023/12/29</td>
              <td>Hà nội</td>
              <td>
                <button class="btn btn-primary">Xem chi tiết</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<style>
* {
  font-family: BaiJamjuree !important;
}
</style>