<script setup>
import CsInput from '@/components/CsInput.vue';
import { ref } from "vue";
import axios from "axios";
/**
 * Get list course
 */
const listCourse = ref([]);
let pageNumber = 0;
let pageSize = 9;
const getListCourse = async () => {
    axios
        .get(
            `http://localhost:8080/api/v1/course/show?pageNumber=${pageNumber}&pageSize=${pageSize}`
        )
        .then(function (response) {
            if (response.data.length > 0) {
                listCourse.value = response.data;
            } else {
                // Nếu không có dữ liệu, bạn có thể không làm gì hoặc hiển thị thông báo
                console.log("Không có dữ liệu");
            }
        });
};
getListCourse();
const nextPage = function () {
    pageNumber++;
    getListCourse();
};

const previousPage = function () {
    if (pageNumber > 0) {
        pageNumber--;
        getListCourse();
    }
};

const fullName = ref("");
const phoneNumber = ref("");
const email = ref("");

/**
 * Create a new student
 */
const resetForm = () => {
    fullName.value = "";
    phoneNumber.value = "";
    email.value = "";
};
const createStudent = async () => {
    const newStudent = {
        fullName: fullName.value,
        phoneNumber: phoneNumber.value,
        email: email.value,
    };
    await axios.post(`http://localhost:8080/api/v1/student/create-for-user`, newStudent);
    resetForm(); // Reset form data and error messages after successful create operation
    closeModal("#exampleModal"); // Close modal
};

const closeModal = (modalId) => {
    const modal = document.querySelector(modalId);
    const backdrop = document.querySelector(".modal-backdrop");
    if (modal && backdrop) {
        modal.classList.remove("show");
        modal.style.display = "none";
        backdrop.remove();
    }
};
</script>
<template>
    <div class="container">
        <div class="row">
            <div class="col small-12 large-12 pb-0 pt-0">
                <div class="box-search flex mb-haft">
                    <div class="search-keyword">
                        <CsInput type="text" name="search" placeholder="Tìm kiếm khóa học"></CsInput>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-3">
                <div class="col-inner">
                    <div class="sidebar-filter">
                        <div class="course-filter-title">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32"
                                fill="none">
                                <path
                                    d="M14.6667 26.6668C14.2889 26.6668 13.9723 26.5391 13.7167 26.2835C13.4611 26.0279 13.3334 25.7113 13.3334 25.3335V17.3335L5.3667 7.16683C5.05559 6.78905 5.01115 6.38905 5.23337 5.96683C5.45559 5.54461 5.80004 5.3335 6.2667 5.3335H25.7334C26.2 5.3335 26.5445 5.54461 26.7667 5.96683C26.9889 6.38905 26.9445 6.78905 26.6334 7.16683L18.6667 17.3335V25.3335C18.6667 25.7113 18.5389 26.0279 18.2834 26.2835C18.0278 26.5391 17.7111 26.6668 17.3334 26.6668H14.6667Z"
                                    fill="#6FBD44"></path>
                            </svg>
                            Bộ lọc tìm kiếm
                        </div>
                        <div class="item-filter">
                            <h3 class="item-title">Thời gian đào tạo</h3>
                            <div class="box-filter">
                                <ul class="mb-0">
                                    <li>
                                        <label>
                                            <input type="checkbox" name="work_type" value="19" class="hidden"
                                                data-label="Full time">
                                            <i class="far fa-square mr-half"></i>
                                            Full time
                                        </label>
                                    </li>
                                    <li>
                                        <label>
                                            <input type="checkbox" name="work_type" value="20" class="hidden"
                                                data-label="Part time">
                                            <i class="far fa-square mr-half"></i>
                                            Part time
                                        </label>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="item-filter">
                            <h3 class="item-title">Đối tượng học</h3>
                            <div class="box-filter">
                                <ul class="mb-0">
                                    <li>
                                        <label>
                                            <input type="checkbox" name="customer" value="33" class="hidden"
                                                data-label="Học chuyên sâu">
                                            <i class="far fa-square mr-half"></i>
                                            Học chuyên sâu
                                        </label>
                                    </li>
                                    <li>
                                        <label>
                                            <input type="checkbox" name="customer" value="36" class="hidden"
                                                data-label="Học cơ bản">
                                            <i class="far fa-square mr-half"></i>
                                            Học cơ bản
                                        </label>
                                    </li>
                                    <li>
                                        <label>
                                            <input type="checkbox" name="customer" value="35" class="hidden"
                                                data-label="Luyện thi chứng chỉ">
                                            <i class="far fa-square mr-half"></i>
                                            Luyện thi chứng chỉ
                                        </label>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col small-12 large-9 pd-0">
                <div class="col-inner">
                    <div class="row align-equal list-ajax-items">
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4" v-for="c in listCourse">
                            <div class="card card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <RouterLink :to="`/${c.id}`">
                                        <img decoding="async" width="360" height="190" :src="c.image"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </RouterLink>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0">
                                            {{ c.courseName }}
                                        </h3>
                                        <div class="text-second main-price">{{ c.coursePrice.toLocaleString('vi-VN') }}
                                            VNĐ</div>
                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Dự kiến học: {{ c.scheduled }}
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M2 8V2.5H7.5V8H2ZM2 14.5V9H7.5V14.5H2ZM8.5 8V2.5H14V8H8.5ZM8.5 14.5V9H14V14.5H8.5ZM3 7H6.5V3.5H3V7ZM9.5 7H13V3.5H9.5V7ZM9.5 13.5H13V10H9.5V13.5ZM3 13.5H6.5V10H3V13.5Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trạng thái:
                                                </div>
                                                <span
                                                    :style="{ 'background-color': c.status == 1 ? '#9bcf53' : 'red', 'border-radius': '5px', color: c.status == 1 ? 'darkgreen' : 'white', }">{{
                                                        c.status == 1 ? "Đang hoạt động" : "Ngừng hoạt động" }}</span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div class="btn-part" data-v-2dc54a20=""><button target="_blank"
                                                        data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                        data-v-2dc54a20=""> Đăng ký ngay </button></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pagination" style="padding-bottom: 15px;">
                <a class="page-btn" @click="previousPage()">«</a>
                <!-- Previous -->

                <a class="page-btn">{{ pageNumber + 1 }}</a>
                <!-- Page numbers -->

                <a class="page-btn" @click="nextPage()">»</a>
                <!-- Next -->
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Đăng ký khóa học</h5>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="" class="form-label">Họ và tên</label>
                        <input type="text" class="form-control" placeholder="Họ và tên ..." v-model="fullName" />
                    </div>
                    <div class="form-group">
                        <label for="" class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control" placeholder="Số điện thoại ..." v-model="phoneNumber" />
                    </div>
                    <div class="form-group">
                        <label for="" class="form-label">Email</label>
                        <input type="text" class="form-control" placeholder="Email ..." v-model="email" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" @click="createStudent()">Send</button>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.container {
    max-width: 1280px
}

.col {
    padding: 15px;
}

.col-inner {
    background-position: 50% 50%;
    background-repeat: no-repeat;
    background-size: cover;
    flex: 1 0 auto;
    margin-left: auto;
    margin-right: auto;
    position: relative;
    width: 100%;
}

.course-filter-title {
    color: #6fbd44;
    text-transform: uppercase;
    font-size: 17px;
    font-weight: 600;
    display: flex;
    align-items: end;
    margin-bottom: 20px;
}

.course-filter-title svg {
    margin-right: 10px;
    margin-left: -5px;
}

svg:not(:root) {
    overflow: hidden;
}

.sidebar-filter .item-filter {
    padding-bottom: 10px;
    margin-bottom: 20px;
}

.sidebar-filter .item-filter ul {
    list-style: none;
}

.mb-0 {
    margin-bottom: 0 !important;
}

.sidebar-filter .item-filter ul li {
    margin-left: 0;
}

.sidebar-filter .item-filter label {
    font-weight: 400;
    cursor: pointer;
}

label {
    color: #222;
}

.sidebar-filter .item-filter input {
    margin-bottom: 0;
}

input[type=checkbox],
input[type=radio] {
    display: inline;
    font-size: 16px;
    margin-right: 10px;
}

.hidden {
    display: none !important;
    visibility: hidden !important;
}

.sidebar-filter .item-filter i {
    font-size: 16px;
}

.far {
    font-weight: 400;
}


h3 .heading-font,
.off-canvas-center .nav-sidebar.nav-vertical>li>a {
    font-family: bromegavn, arial, sans-serif !important;
    font-weight: 700 !important;
}

.h3,
h3 {
    font-size: 1.25em;
}

h3 {
    text-rendering: optimizeSpeed;
    color: #555;
    margin-bottom: .5em;
    margin-top: 0;
    width: 100%;
    font-family: bromegavn, arial, sans-serif !important;
    font-weight: 700 !important;
}

.btn {
    display: inline-block;
    padding: 6px 12px;
    line-height: normal;
    margin: 0;
    vertical-align: middle;
    text-transform: none;
    font-weight: 400;
}

.item-course,
.item-course .item-img img {
    border-radius: 15px !important;
}

.item-course .item-img {
    text-align: center;
}

a {
    text-decoration: none;
    color: #1d1f22;
}

img {
    opacity: 1;
    transition: opacity 1s;
    display: inline-block;
    height: auto;
    max-width: 100%;
    vertical-align: middle;
}

.pa-half {
    padding: 15px !important;
}

.expand,
.full-width {
    display: block;
    max-width: 100% !important;
    padding-left: 0 !important;
    padding-right: 0 !important;
    width: 100% !important;
}

.item-post .item-title {
    font-size: 18px;
    font-weight: 600;
    line-height: 1.33;
    letter-spacing: normal;
    color: #1d1f22;
    text-align: left;
}

.text-second {
    color: #7ebf4f !important;
}

.item-course .main-price {
    font-size: 17px;
    font-weight: 600;
    line-height: 22px;
}

.item-course .item-title {
    font-size: 18px;
    font-weight: 600;
    min-height: 54px;
}

.item-course .item-meta {
    border-top: 1px solid rgba(0, 0, 0, .42);
    padding-top: 12px;
    margin-top: 20px;
}

.item-meta-line {
    font-size: 14px;
}

.item-meta-line {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-bottom: 8px;
}

.item-meta-line>div>svg {
    width: 18px;
    transform: scale(.9);
}

.item-course .price-info {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 75px;
    padding-left: 15px;
    padding-right: 15px;
    padding-bottom: 15px;
}

.btn-course-detail {
    width: 100%;
    padding: 15px 20px;
    text-align: center;
    border-radius: 15px;
}

.box-pagination {
    font-size: 14px;
}

.justify-end {
    justify-content: flex-end;
}

.items-center {
    align-items: center;
}

.pagination {
    width: 100%;
    padding: 0 1rem;
    margin-top: 1.5rem;
    display: flex;
    justify-content: center;
}

.page-btn {
    color: #000000;
    width: 2.5rem;
    height: 2.5rem;
    margin-right: 0.25rem;
    display: inline-flex;
    flex-shrink: 0;
    justify-content: center;
    align-items: center;
}

.page-btn:is(a) {
    text-decoration: none;
    background-color: #ffffff;
    border-radius: 50%;
    cursor: pointer;
    transition: color 128ms ease-out, background-color 128ms ease-out;
}

.page-num {
    display: none;
}

.page-step {
    display: none;
}

@media screen and (min-width: 992px) {
    .col-3 {
        flex-basis: 25%;
        max-width: 25%;
    }
}

@media screen and (min-width: 850px) {
    .col:first-child .col-inner {
        margin-left: auto;
        margin-right: 0;
    }
}

@media screen and (min-width: 992px) {
    .col+.col .col-inner {
        margin-left: 0;
        margin-right: auto;
    }
}

@media screen and (max-width: 849px) {
    .row {
        display: flex;
        flex-direction: column;
    }

    .col-3 {
        width: 100%;
    }
}

@media (max-width: 992px) {
    .col-md-6 {
        flex-basis: 50%;
        max-width: 50%;
    }
}

@media (max-width: 768px) {
    .col-md-6 {
        flex-basis: 100%;
        max-width: 100%;
    }
}

@media (max-width: 768px) {
    img {
        width: 100%;
        height: auto;
    }
}
</style>