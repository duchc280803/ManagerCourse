<template>
  <header id="header" class="header has-sticky sticky-jump">
    <div class="header-wrapper">
      <div id="masthead" class="header-main ">
        <div class="header-inner flex-row container logo-left" role="navigation">

          <!-- Logo -->
          <div id="logo" class="flex-col logo">

            <!-- Header logo -->
            <a href="https://fsoft-academy.edu.vn/" title="FPT Software Academy - FPT Software Academy" rel="home">
              <img width="124" height="49"
                src="https://static.topcv.vn/company_logos/YjF8IUn9DGHx3HGzTUPki8DycZsRRqLA_1661485501____8e43ee2aad361f320c7d0a13becc6a92.png"
                class="header_logo header-logo" alt="FPT Software Academy"></a>
          </div>
          <div class="flex-col show-for-medium flex-left">
            <ul class="mobile-nav nav nav-left ">
            </ul>
          </div>

          <!-- Left Elements -->
          <div class="flex-col hide-for-medium flex-left
            flex-grow">
            <ul class="header-nav header-nav-main nav nav-left  nav-line-bottom nav-uppercase">
            </ul>
          </div>

          <!-- Right Elements -->
          <div class="flex-col hide-for-medium flex-right">
            <ul class="header-nav header-nav-main nav nav-right  nav-line-bottom nav-uppercase">
              <li id="menu-item-533"
                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-533 menu-item-design-default">
                <RouterLink to="home">
                  <a href="https://fsoft-academy.edu.vn/gioi-thieu/" class="nav-top-link">Trang chủ</a>
                </RouterLink>
              </li>
              <li id="menu-item-298"
                class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-258 current_page_item menu-item-298 active menu-item-design-default">
                <RouterLink to="dao-tao"><a href="https://fsoft-academy.edu.vn/dao-tao-mo-rong/" aria-current="page"
                    class="nav-top-link">Đào
                    tạo
                    mở rộng</a>
                </RouterLink>
              </li>
              <li id="menu-item-772"
                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-772 menu-item-design-default">
                <RouterLink to="contact">
                  <a href="https://fsoft-academy.edu.vn/lien-he/" class="nav-top-link">Liên hệ</a>
                </RouterLink>
              </li>
            </ul>
          </div>
          <div class="flex-col show-for-medium flex-right">
            <ul class="mobile-nav nav nav-right ">
              <li class="nav-icon has-icon">
                <a href="#" data-open="#main-menu" data-pos="left" data-bg="main-menu-overlay" data-color=""
                  class="is-small" aria-label="Menu" aria-controls="main-menu" aria-expanded="false">

                  <i class="icon-menu"></i>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="container">
          <div class="top-divider full-width"></div>
        </div>
      </div>

      <div class="header-bg-container fill">
        <div class="header-bg-image fill"></div>
        <div class="header-bg-color fill"></div>
      </div>
    </div>
  </header>
  <div style="background: #f9f8f8;">
    <div class="container img">
      <img class="banner-img" alt="ok"
        src="https://aptech.fpt.edu.vn/wp-content/uploads/2023/12/HB-12-NAM-DEN-SACH-2024-Banner-web-destop.jpg">
    </div>
    <RouterView></RouterView>
  </div>
</template>
<style scoped>
.header {
  background-position: 50% 0;
  background-size: cover;
  position: fixed;
  /* Sử dụng position: fixed để giữ header ở một vị trí cố định trên màn hình */
  top: 0;
  /* Header sẽ được giữ ở đầu trang */
  width: 100%;
  z-index: 30;
  transition: background-color .3s, opacity .3s;
}

.header-main {
  height: 90px;
  z-index: 9999;
  background: #fff;
  position: relative;
  z-index: 10;
}

.header .flex-row {
  height: 100%;
}

.header .flex-row {
  height: 100%;
}

.container-width,
.full-width .ubermenu-nav,
.container,
.row {
  max-width: 1170px;
}

.flex-row {
  align-items: center;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  width: 100%;
}

.container,
.container-width,
.full-width .ubermenu-nav,
.row {
  max-width: 1080px;
}

.container,
.container-width,
.full-width .ubermenu-nav,
.row,
body {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}

#logo {
  width: 200px;
}

.logo-left .logo {
  margin-left: 0;
  margin-right: 30px;
}

.logo {
  line-height: 1;
  margin: 0;
}

.flex-col {
  max-height: 100%;
}

.logo a {
  color: #446084;
  display: block;
  font-size: 32px;
  font-weight: bolder;
  margin: 0;
  text-decoration: none;
  text-transform: uppercase;
}

#logo img {
  max-height: 90px;
}

.header-wrapper:not(.stuck) .logo img {
  transition: max-height .5s;
}

.logo img {
  display: block;
  width: auto;
}

.flex-right {
  margin-left: auto;
}

.flex-col {
  max-height: 100%;
}

.header-nav {
  max-width: 1080px;
  margin: 0 auto;
}

.nav-right {
  justify-content: flex-end;
}

.nav {
  align-items: center;
  display: inline-block;
  display: flex;
  flex-flow: row wrap;
  width: 100%;
}

.nav li:first-child {
  margin-left: 0 !important;
}

.header-nav>li {
  margin: 0 15px;
}

.header-main .nav>li>a {
  text-transform: none;
  font-size: 14px;
  color: #333;
}

.nav>li>a {
  font-family: "inherit", sans-serif;
}

.nav>li>a {
  font-size: .8em;
}

.nav-uppercase>li>a {
  font-weight: bolder;
  letter-spacing: .02em;
  text-transform: uppercase;
}

.nav-column>li>a,
.nav-dropdown>li>a,
.nav-vertical-fly-out>li>a,
.nav>li>a {
  color: hsla(0, 0%, 40%, .85);
  transition: all .2s;
}

a {
  color: #334862;
  text-decoration: none;
}

#image_616851210 {
  width: 100%;
}

#image_182394615 {
  width: 100%;
}

img {
  display: inline-block;
  height: auto;
  max-width: 100%;
  vertical-align: middle;
}

.container.img {
  margin-bottom: 20px;
}

.banner-img {
  margin-top: 110px;
}

@media (min-width:550px) {
  #image_182394615 {
    width: 100%;
  }
}

@media (min-width:550px) {
  #image_616851210 {
    width: 100%;
  }
}
</style>
