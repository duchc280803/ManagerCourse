<script setup>
import CsInput from '@/components/CsInput.vue';
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

                        <div class="bottom-filter text-right"><button type="reset" class="btn">Xoá bộ lọc</button></div>
                    </div>
                </div>
            </div>
            <div class="col small-12 large-9 pd-0">
                <div class="col-inner">
                    <div class="row align-equal list-ajax-items">
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/java-web-developer-a-to-z/">
                                        <img decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/03/Javawebaz-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/java-web-developer-a-to-z/">Java
                                                Web Developer A to Z</a></h3>

                                        <div class="text-second main-price">15.900.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span>Offline</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/java-web-developer-a-to-z/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/data-analytics/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2023/09/anh-02-1-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/data-analytics/">Data
                                                Analytics</a></h3>

                                        <div class="text-second main-price">7.900.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span>27/03/2024</span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
                                                </div>
                                                <span>Cơ bản</span>
                                            </div>


                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M2 8V2.5H7.5V8H2ZM2 14.5V9H7.5V14.5H2ZM8.5 8V2.5H14V8H8.5ZM8.5 14.5V9H14V14.5H8.5ZM3 7H6.5V3.5H3V7ZM9.5 7H13V3.5H9.5V7ZM9.5 13.5H13V10H9.5V13.5ZM3 13.5H6.5V10H3V13.5Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Hình thức:
                                                </div>
                                                <span>Online</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/data-analytics/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/agile-in-practice-crash-course/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-01-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/agile-in-practice-crash-course/">Agile
                                                in Practice Crash Course</a></h3>

                                        <div class="text-second main-price">3.900.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span>30/12/2023</span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/agile-in-practice-crash-course/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/programming-with-python/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-18-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/programming-with-python/">Python
                                                Essentials</a></h3>

                                        <div class="text-second main-price">5.900.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/programming-with-python/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/easy-coding/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-19-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/easy-coding/">Easy Coding (for
                                                kids)</a></h3>

                                        <div class="text-second main-price">3.000.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/easy-coding/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/full-stack-net-web-for-beginner/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-23-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/full-stack-net-web-for-beginner/">Full-Stack
                                                .Net Web for Beginner</a></h3>

                                        <div class="text-second main-price">41.800.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/full-stack-net-web-for-beginner/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/luyen-thi-chung-chi-pmi-acp/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-21-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/luyen-thi-chung-chi-pmi-acp/">Luyện
                                                thi chứng chỉ: PMI-ACP</a></h3>

                                        <div class="text-second main-price">8.000.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span></span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
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
                                                    Hình thức:
                                                </div>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/luyen-thi-chung-chi-pmi-acp/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/devops-foundation/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-22-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/devops-foundation/">DevOps
                                                Foundation</a></h3>

                                        <div class="text-second main-price">11.900.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span>01/05/2024</span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
                                                </div>
                                                <span>Cơ bản </span>
                                            </div>


                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M2 8V2.5H7.5V8H2ZM2 14.5V9H7.5V14.5H2ZM8.5 8V2.5H14V8H8.5ZM8.5 14.5V9H14V14.5H8.5ZM3 7H6.5V3.5H3V7ZM9.5 7H13V3.5H9.5V7ZM9.5 13.5H13V10H9.5V13.5ZM3 13.5H6.5V10H3V13.5Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Hình thức:
                                                </div>
                                                <span>Online</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/devops-foundation/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-6 mb-4">
                            <div class="card item-post item-course box-shadow-2 rounded-small overflow-hidden">
                                <div class="item-img relative">
                                    <a href="https://fsoft-academy.edu.vn/course/full-stack-java-web-for-beginner/">
                                        <img loading="lazy" decoding="async" width="360" height="190"
                                            src="https://fsoft-academy.edu.vn/wp-content/uploads/2024/01/anh-23-360x190.jpg"
                                            class="attachment-small-rectangle size-small-rectangle wp-post-image"
                                            alt="">
                                    </a>
                                </div>
                                <div class="item-info pa-half">
                                    <div class="item-detail full-width">
                                        <h3 class="item-title my-0"><a
                                                href="https://fsoft-academy.edu.vn/course/full-stack-java-web-for-beginner/">Full-Stack
                                                Java Web for Beginner</a></h3>

                                        <div class="text-second main-price">41.800.000 VNĐ</div>

                                        <div class="item-meta">
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M4.66629 9.71667C4.82204 9.71667 4.9527 9.66399 5.05825 9.55863C5.16381 9.45327 5.21659 9.32271 5.21659 9.16697C5.21659 9.01121 5.16391 8.88056 5.05855 8.775C4.95319 8.66944 4.82263 8.61667 4.66689 8.61667C4.51113 8.61667 4.38047 8.66934 4.27492 8.7747C4.16936 8.88007 4.11659 9.01062 4.11659 9.16637C4.11659 9.32212 4.16926 9.45278 4.27462 9.55833C4.37999 9.66389 4.51054 9.71667 4.66629 9.71667ZM4.66629 7.05C4.82204 7.05 4.9527 6.99732 5.05825 6.89197C5.16381 6.7866 5.21659 6.65604 5.21659 6.5003C5.21659 6.34454 5.16391 6.21389 5.05855 6.10833C4.95319 6.00278 4.82263 5.95 4.66689 5.95C4.51113 5.95 4.38047 6.00268 4.27492 6.10803C4.16936 6.2134 4.11659 6.34396 4.11659 6.4997C4.11659 6.65546 4.16926 6.78611 4.27462 6.89167C4.37999 6.99722 4.51054 7.05 4.66629 7.05ZM5.99992 9.66667H11.9999V8.66667H5.99992V9.66667ZM5.99992 7H11.9999V6H5.99992V7ZM5.49992 14.5V13.1667H2.33325C2.06659 13.1667 1.83325 13.0667 1.63325 12.8667C1.43325 12.6667 1.33325 12.4333 1.33325 12.1667V3.5C1.33325 3.23333 1.43325 3 1.63325 2.8C1.83325 2.6 2.06659 2.5 2.33325 2.5H13.6666C13.9333 2.5 14.1666 2.6 14.3666 2.8C14.5666 3 14.6666 3.23333 14.6666 3.5V12.1667C14.6666 12.4333 14.5666 12.6667 14.3666 12.8667C14.1666 13.0667 13.9333 13.1667 13.6666 13.1667H10.4999V14.5H5.49992ZM2.33325 12.1667H13.6666V3.5H2.33325V12.1667Z"
                                                            fill="#6FBD44"></path>
                                                    </svg> Khai giảng:
                                                </div>
                                                <span>16/04/2024</span>
                                            </div>
                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M10.4499 11.7168L11.1999 10.9668L8.54992 8.30016V4.95016H7.54992V8.70016L10.4499 11.7168ZM7.99992 15.1668C7.08881 15.1668 6.2277 14.9918 5.41658 14.6418C4.60547 14.2918 3.89714 13.8141 3.29159 13.2085C2.68603 12.6029 2.20825 11.8946 1.85825 11.0835C1.50825 10.2724 1.33325 9.41127 1.33325 8.50016C1.33325 7.58905 1.50825 6.72794 1.85825 5.91683C2.20825 5.10572 2.68603 4.39738 3.29159 3.79183C3.89714 3.18627 4.60547 2.7085 5.41658 2.3585C6.2277 2.0085 7.08881 1.8335 7.99992 1.8335C8.91103 1.8335 9.77214 2.0085 10.5833 2.3585C11.3944 2.7085 12.1027 3.18627 12.7083 3.79183C13.3138 4.39738 13.7916 5.10572 14.1416 5.91683C14.4916 6.72794 14.6666 7.58905 14.6666 8.50016C14.6666 9.41127 14.4916 10.2724 14.1416 11.0835C13.7916 11.8946 13.3138 12.6029 12.7083 13.2085C12.1027 13.8141 11.3944 14.2918 10.5833 14.6418C9.77214 14.9918 8.91103 15.1668 7.99992 15.1668ZM7.99992 14.1668C9.55547 14.1668 10.8888 13.6113 11.9999 12.5002C13.111 11.3891 13.6666 10.0557 13.6666 8.50016C13.6666 6.94461 13.111 5.61127 11.9999 4.50016C10.8888 3.38905 9.55547 2.8335 7.99992 2.8335C6.44436 2.8335 5.11103 3.38905 3.99992 4.50016C2.88881 5.61127 2.33325 6.94461 2.33325 8.50016C2.33325 10.0557 2.88881 11.3891 3.99992 12.5002C5.11103 13.6113 6.44436 14.1668 7.99992 14.1668Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Trình độ:
                                                </div>
                                                <span>Cơ bản</span>
                                            </div>


                                            <div class="item-meta-line">
                                                <div>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17"
                                                        viewBox="0 0 16 17" fill="none">
                                                        <path
                                                            d="M2 8V2.5H7.5V8H2ZM2 14.5V9H7.5V14.5H2ZM8.5 8V2.5H14V8H8.5ZM8.5 14.5V9H14V14.5H8.5ZM3 7H6.5V3.5H3V7ZM9.5 7H13V3.5H9.5V7ZM9.5 13.5H13V10H9.5V13.5ZM3 13.5H6.5V10H3V13.5Z"
                                                            fill="#6FBD44"></path>
                                                    </svg>
                                                    Hình thức:
                                                </div>
                                                <span>Offline</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="price-info flex">
                                    <div class="flex justify-between items-center full-width">

                                        <a href="https://fsoft-academy.edu.vn/course/full-stack-java-web-for-beginner/"
                                            class="btn bg-main text-white btn-course-detail">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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

.col-lg-4.col-md-6 {
    padding-bottom: 15px
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