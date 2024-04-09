<script setup>
import ScInput from "@/components/CsInput.vue"
import CsSelect from "@/components/CsSelect.vue"

const technologyOptions = [
  { value: 'Công nghệ', label: 'Công nghệ' },
  { value: '.NET', label: '.NET' },
  { value: '.NET/SharePoint', label: '.NET/SharePoint' },
  { value: 'Agile', label: 'Agile' },
  { value: 'Android', label: 'Android' },
  { value: 'Angular', label: 'Angular' },
  { value: 'Application Consultant', label: 'Application Consultant' },
  { value: 'Application Security', label: 'Application Security' },
  { value: 'Business Analyst', label: 'Business Analyst' },
  { value: 'C/C++', label: 'C/C++' },
  { value: 'C/Embedded', label: 'C/Embedded' },
  { value: 'CAD', label: 'CAD' },
  { value: 'Cloud', label: 'Cloud' },
  { value: 'Cloud Security', label: 'Cloud Security' },
  { value: 'Cobol', label: 'Cobol' },
  { value: 'Data', label: 'Data' },
  { value: 'DevOps', label: 'DevOps' },
  { value: 'Erlang', label: 'Erlang' },
  { value: 'Flexcube', label: 'Flexcube' },
  { value: 'Flutter', label: 'Flutter' },
  { value: 'Front-end', label: 'Front-end' },
  { value: 'Golang', label: 'Golang' },
  { value: 'iOS', label: 'iOS' },
  { value: 'Java', label: 'Java' },
  { value: 'Khác', label: 'Khác' },
  { value: 'Node JS', label: 'Node JS' },
  { value: 'OutSystem', label: 'OutSystem' },
  { value: 'PHP', label: 'PHP' },
  { value: 'Python', label: 'Python' },
  { value: 'QA', label: 'QA' },
  { value: 'React', label: 'React' },
  { value: 'React Native', label: 'React Native' },
  { value: 'RPA', label: 'RPA' },
  { value: 'Technical Consultant', label: 'Technical Consultant' },
  { value: 'Testing', label: 'Testing' },
  { value: 'Thiết kế giảng dạy', label: 'Thiết kế giảng dạy' },
  { value: 'Tuyển dụng & Thu hút nhân tài', label: 'Tuyển dụng & Thu hút nhân tài' }
];

</script>
<template>
  <footer class="footer-wrapper">
    <section class="section footer-bottom pb-sm-0 container" id="section_2024184034">
      <div class="section-content">
        <div class="row">
          <div id="col" class="col">
            <div class="col-inner">
              <p><strong>POWERED BY</strong></p>
              <div class="img">
                <div class="img-inner dark">
                  <img width="215" height="68"
                    src="https://static.topcv.vn/company_logos/YjF8IUn9DGHx3HGzTUPki8DycZsRRqLA_1661485501____8e43ee2aad361f320c7d0a13becc6a92.png"
                    class="attachment-large size-large" alt="" decoding="async" loading="lazy">
                </div>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="col-inner">
              <p><span style="font-size: 110%;"><strong>GIA NHẬP NEWAVE SOLUTIONS ACADEMY</strong></span></p>
              <div class="wpcf7 js">
                <div class="row row-footer-form">
                  <div class="col-lg-6">
                    <div class="col-inner">
                      <p><span class="wpcf7-form-control-wrap" data-name="your-name">
                          <ScInput type="text" placeholder="Họ và tên *"></ScInput>
                        </span>
                      </p>
                    </div>
                  </div>
                  <div class="col-lg-6">
                    <div class="col-inner">
                      <p><span class="wpcf7-form-control-wrap" data-name="your-phone">
                          <ScInput type="text" placeholder="Số điện thoại *"></ScInput>
                        </span>
                      </p>
                    </div>
                  </div>
                  <div class="col-lg-6">
                    <div class="col-inner">
                      <p><span class="wpcf7-form-control-wrap" data-name="your-email">
                          <ScInput type="email" placeholder="Địa chỉ email"></ScInput>
                        </span>
                      </p>
                    </div>
                  </div>
                  <div class="col-lg-6">
                    <div class="col-inner">
                      <p><span class="wpcf7-form-control-wrap" data-name="technology">
                        <CsSelect :option="technologyOptions">
                          </CsSelect></span><br>
                      </p>
                    </div>
                  </div>
                  <div class="col small-12 medium-6 large-4 pb-0">
                    <div class="col-inner">
                      <div class="submit-actions">
                        <p><input class="wpcf7-form-control wpcf7-submit has-spinner contact-form-1 form-footer"
                            type="submit" value="Đăng ký ngay"><span class="wpcf7-spinner"></span>
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <div class="absolute-footer light medium-text-center text-center">
      <div class="container clearfix">
        <div class="footer-primary pull-left">
          <div class="copyright-footer">
            © 2024 Newave Solution </div>
        </div>
      </div>
    </div>
  </footer>
</template>
<style scoped>
.footer-wrapper {
  position: relative;
  width: 100%;
}

footer {
  font-size: 12px;
}

#section_2024184034 {
  padding-top: 30px;
  padding-bottom: 30px;
  background-color: rgb(255, 255, 255);
}

.footer-bottom {
  padding-bottom: 0 !important;
}

.section {
  align-items: center;
  display: flex;
  flex-flow: row;
  min-height: auto;
  padding: 30px 0;
  position: relative;
  width: 100%;
}

.relative {
  z-index: auto !important;
}

.relative {
  position: relative !important;
}

.section-content {
  z-index: 1;
}

.section-bg,
.section-content {
  width: 100%;
}

.col {
  padding: 15px;
}

img {
  display: inline-block;
  height: auto;
  max-width: 100%;
  vertical-align: middle;
}

.row-footer-form input,
.row-footer-form select {
  height: 40px;
  border-radius: 4px;
  border: 1px solid rgba(0, 0, 0, .09);
  outline: none;
  box-shadow: none;
  margin-bottom: -4px;
}

.button:hover,
.dark .button.is-form:hover,
input[type=button]:hover,
input[type=reset]:hover,
input[type=submit]:hover {
  box-shadow: inset 0 0 0 100px rgba(0, 0, 0, .2);
  color: #fff;
  opacity: 1;
  outline: none;
}

.submit-actions input {
  border-radius: 4px;
  border: 1px solid rgba(0, 0, 0, .09);
  background: #73b048 !important;
  width: 100%;
}

.row-footer-form input,
.row-footer-form select {
  height: 40px;
  border-radius: 4px;
  border: 1px solid rgba(0, 0, 0, .09);
  outline: none;
  box-shadow: none;
  margin-bottom: -4px;
}

@media screen and (min-width: 850px) {
  .large-3 {
    flex-basis: 25%;
    max-width: 25%;
  }
}

@media screen and (min-width: 550px) {
  .medium-3 {
    flex-basis: 25%;
    max-width: 25%;
  }
}
</style>
