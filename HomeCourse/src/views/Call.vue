<template>
  <div
    class="contact-page-section pt-50 pb-50 md-pt-70 md-pb-70 event-bg"
    style="padding-top: 105px; padding-bottom: 100px;">
    <div class="container">
      <div class="row rs-contact-box mb-90 md-mb-50">
        <div class="col-lg-4 col-md-12-4 lg-pl-0 sm-mb-30 md-mb-30">
          <div class="address-item" style="padding-bottom: 25px !important">
            <div class="icon-part">
              <img src="https://lotusacademy.edu.vn/assets/images/contact/icon/1.png" alt="" />
            </div>
            <div class="address-text">
              <span class="label">Địa chỉ</span>
              <span class="des">
                <a
                  href="https://www.google.com/maps/place/139+Xu%C3%A2n+Th%E1%BB%A7y,+D%E1%BB%8Bch+V%E1%BB%8Dng+H%E1%BA%ADu,+C%E1%BA%A7u+Gi%E1%BA%A5y,+H%C3%A0+N%E1%BB%99i/@21.0364772,105.7823237,17z/data=!3m1!4b1!4m5!3m4!1s0x3135ab49fc2d9db3:0x447c5befe54ba6ff!8m2!3d21.0364772!4d105.7845124">
                  Tòa nhà Mitec, Đường Dương Đình Nghệ, Phường Yên Hoà, Quận Cầu Giấy, Hà Nội, Việt Nam
                </a>
              </span>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 lg-pl-0 sm-mb-30 md-mb-30">
          <div class="address-item">
            <div class="icon-part">
              <img src="https://lotusacademy.edu.vn/assets/images/contact/icon/2.png" alt="" />
            </div>
            <div class="address-text">
              <span class="label">Email</span>
              <span class="des">
                <a href="mailto:duchcph2257@fpt.edu.vn">
                    hr@newwave.com.vn
                </a>
              </span>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 lg-pl-0 sm-mb-30">
          <div class="address-item">
            <div class="icon-part">
              <img src="https://lotusacademy.edu.vn/assets/images/contact/icon/3.png" alt="" />
            </div>
            <div class="address-text">
              <span class="label">Điện thoại</span>
              <span class="des"><a href="tel:(+84) 24 6663 5848">(+84) 24 6663 5848</a></span>
            </div>
          </div>
        </div>
      </div>
      <div class="row align-items-center">
        <div class="col-lg-6 md-mb-30">
          <!-- Map Section Start -->
          <div class="contact-map3">
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.964220232871!2d105.76310011533214!3d21.034117592970336!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b947cd6e49%3A0x6a87974c6b44d671!2zNjggUGjhu5EgTmd1eeG7hW4gQ8ahIFRo4bqhY2gsIE3hu7kgxJDDrG5oLCBU4burIExpw6ptLCBIw6AgTuG7mWksIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1641234700987!5m2!1svi!2s"
              frameborder="0"
              style="border: 0"></iframe>
          </div>
        </div>
        <div class="col-lg-6 pl-60 md-pl-15">
          <div class="contact-comment-box">
            <div class="inner-part">
              <h2 class="title mb-mb-15">Để lại lời nhắn</h2>
              <p id="form-messages"></p>
            </div>
            <form id="contact-form" method="post" action="/home/guiloinhan">
              <fieldset>
                <div class="row">
                  <div class="col-lg-6 mb-35 col-md-6 col-sm-6">
                    <input
                      class="from-control"
                      type="text"
                      name="HoTen"
                      id="name"
                      placeholder="Họ tên" />
                  </div>
                  <div class="col-lg-6 mb-35 col-md-6 col-sm-6">
                    <input
                      class="from-control"
                      type="text"
                      name="SoDienThoai"
                      id="phone_number"
                      placeholder="Điện thoại" />
                  </div>
                  <div class="col-lg-12 mb-35">
                    <input
                      class="from-control"
                      type="text"
                      name="Email"
                      id="email"
                      placeholder="Email" />
                  </div>
                  <div class="col-lg-12 mb-50">
                    <textarea
                      class="from-control"
                      id="message"
                      name="NoiDungLoiNhan"
                      placeholder="Lời nhắn"></textarea>
                  </div>
                </div>
                <br>
                <div class="form-group mb-0">
                  <input class="btn-send" type="submit" value="Gửi thông tin" />
                </div>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.event-bg {
  background: #f9f8f8;
}
.contact-page-section .rs-contact-box .address-item {
  padding: 50px 40px 50px 40px;
  background: #ffffff;
  box-shadow: 0 0 30px #fff;
  text-align: center;
  margin: 0px 20px 0px 0px;
  border-radius: 5px 5px 5px 5px;
}
.contact-page-section .rs-contact-box .address-item .icon-part {
    padding-bottom: 20px;
}
.contact-page-section .rs-contact-box .address-item .address-text .label {
    display: block;
    color: #101010;
    font-size: 22px;
    font-weight: 600;
    padding-bottom: 10px;
}
.contact-page-section .rs-contact-box .address-item .address-text .des {
    font-size: 17px;
    color: #031a3d;
}
.contact-page-section .rs-contact-box .address-item .address-text .des a {
    color: #031a3d;
}
.contact-page-section .rs-contact-box .address-item .icon-part img {
    width: 65px;
}
.contact-page-section .rs-contact-box .address-item .address-text .des a:hover {
    color: #ff5421;
}
.align-items-center {
    -ms-flex-align: center !important;
    align-items: center !important;
}
.contact-page-section .inner-part .title {
    font-size: 36px;
    color: #101010;
    line-height: 46px;
    font-weight: 700;
    margin-bottom: 14px;
}
.contact-page-section .inner-part p {
    font-size: 19px;
    color: #363636;
    line-height: 32px;
    font-weight: 400;
}
fieldset {
    min-width: 0;
    padding: 0;
    margin: 0;
    border: 0;
}
.mb-35 {
    margin-bottom: 35px;
}
.contact-page-section .contact-comment-box .from-control {
    border-style: solid;
    border-width: 1px 1px 1px 1px;
    border-color: #f1f1f1;
    box-shadow: 0 0 30px #eee;
    color: #363636;
    width: 100%;
    max-width: 100%;
    opacity: 1;
    border-radius: 3px;
    border: 1px solid #ffffff;
    color: #767676;
    background: #ffffff;
    padding: 10px 18px;
}
.contact-page-section .contact-comment-box .form-group .btn-send {
    padding: 16px 0px 16px 0px;
    box-shadow: 0px 28px 50px 0px rgba(0, 0, 0, 0.05);
    outline: none;
    border: none;
    padding: 12px 40px;
    border-radius: 3px;
    display: inline-block;
    text-transform: capitalize;
    font-size: 16px;
    font-weight: 500;
    color: #ffffff;
    background: #ff5421;
    transition: all 0.3s ease;
    position: relative;
    overflow: hidden;
    width: 100%;
    text-transform: uppercase;
}
</style>
