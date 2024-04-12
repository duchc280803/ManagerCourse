<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
const route = useRoute();
const idParam = route.params.id;

const showRouteContent = ref(false);
const showRouteContent3 = ref(false);
const showRouteContent4 = ref(false);
const showRouteContent5 = ref(false);
const listSubject = ref([]);
const pageNumber = ref(0);
const pageSize = ref(6);
const courseId = ref("")

const toggleRouteContent = () => {
    showRouteContent.value = !showRouteContent.value;
    if (showRouteContent.value) {
        getListSubjectName(1);
    }
}

const toggleRouteContent2 = () => {
    showRouteContent3.value = !showRouteContent3.value;
    if (showRouteContent3.value) {
        getListSubjectName(2);
    }
}

const toggleRouteContent3 = () => {
    showRouteContent4.value = !showRouteContent4.value;
    if (showRouteContent4.value) {
        getListSubjectName(3);
    }
}

const toggleRouteContent4 = () => {
    showRouteContent5.value = !showRouteContent5.value;
    if (showRouteContent5.value) {
        getListSubjectName(4);
    }
}
const getListSubjectName = async (classify) => {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/subject/subject-course`, {
            params: {
                pageNumber: pageNumber.value,
                pageSize: pageSize.value,
                id: idParam,
                classify: classify,
            },
        });
        listSubject.value = response.data;
        console.log( listSubject.value);
    } catch (error) {
        console.error('Error fetching subjects:', error);
    }
};

</script>
<template>
    <section class="session">
        <div class="session-content relative">
            <div class="container">
                <h1 class="page-title">{{ courseId.courseName }}</h1>
                <div class="row">
                    <div class="col-lg-9">
                        <div class="contact-course mb">
                            <section class="section pl-0 pr-0" id="section_563483951">
                                <hr>
                                <div class="section-content relative">
                                    <em>Nếu bạn muốn biết thêm thông tin về khoá học. Hãy liên hệ với chúng tôi.</em>
                                    <div class="icon-box featured-box icon-box-left text-left">
                                        <div class="icon-box-img" style="width: 92px">
                                            <div class="icon">
                                                <div class="icon-inner">
                                                    <img width="92" height="92"
                                                        src="https://fsoft-academy.edu.vn/wp-content/uploads/2022/08/depositphotos_112592806-stock-illustration-female-call-centre-operator-with-01-300x300.jpg"
                                                        class="attachment-medium size-medium" alt="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="icon-box-text last-reset">
                                            <ul>
                                                <li><strong>Email</strong>: <a
                                                        href="newavesolution@gmail.com">newavesolution@gmail.com</a>
                                                </li>
                                                <li><strong style="color: #555555;">Số điện thoại: </strong><span
                                                        style="color: #555555;">0969125248</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <hr>
                        <div class="card" @click="toggleRouteContent()">
                            <div class=" item-route box-shadow pa-haft">
                                <div class="d-flex justify-between item-route-title">
                                    <b>Session 01: Môn cơ bản</b>
                                    <span class="ml-half">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                            viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                            stroke-linecap="round" stroke-linejoin="round"
                                            class="lucide lucide-chevron-down">
                                            <path d="m6 9 6 6 6-6" />
                                        </svg>
                                    </span>
                                </div>
                                <div class="route-content pl-medium mt-half" v-if="showRouteContent">
                                    <div class="list-lesson">
                                        <div class="item-lesson">
                                            <div class="item-lesson-title d-flex justify-between"
                                                v-for="(s, index) in listSubject" :key="s.id">
                                                <span>{{ s.subjectName }}</span>
                                                <span class="ml-half"></span>
                                            </div>
                                            <ul class="list-contents pl-medium mt-half">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="card" @click="toggleRouteContent2()">
                            <div class=" item-route box-shadow pa-haft">
                                <div class="d-flex justify-between item-route-title">
                                    <b>Session 02: Môn data</b>
                                    <span class="ml-half">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                            viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                            stroke-linecap="round" stroke-linejoin="round"
                                            class="lucide lucide-chevron-down">
                                            <path d="m6 9 6 6 6-6" />
                                        </svg>
                                    </span>
                                </div>
                                <div class="route-content pl-medium mt-half" v-if="showRouteContent3">
                                    <div class="list-lesson">
                                        <div class="item-lesson">
                                            <div class="item-lesson-title d-flex justify-between"
                                                v-for="(s, index) in listSubject" :key="s.id">
                                                <span>{{ s.subjectName }}</span>
                                                <span class="ml-half"></span>
                                            </div>
                                            <ul class="list-contents pl-medium mt-half">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="card" @click="toggleRouteContent3()">
                            <div class=" item-route box-shadow pa-haft">
                                <div class="d-flex justify-between item-route-title">
                                    <b>Session 03: Môn cấu trúc dữ liệu</b>
                                    <span class="ml-half">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                            viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                            stroke-linecap="round" stroke-linejoin="round"
                                            class="lucide lucide-chevron-down">
                                            <path d="m6 9 6 6 6-6" />
                                        </svg>
                                    </span>
                                </div>
                                <div class="route-content pl-medium mt-half" v-if="showRouteContent4">
                                    <div class="list-lesson">
                                        <div class="item-lesson">
                                            <div class="item-lesson-title d-flex justify-between"
                                                v-for="(s, index) in listSubject" :key="s.id">
                                                <span>{{ s.subjectName }}</span>
                                                <span class="ml-half"></span>
                                            </div>
                                            <ul class="list-contents pl-medium mt-half">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="card" @click="toggleRouteContent4()" style="margin-bottom: 26px;">
                            <div class=" item-route box-shadow pa-haft">
                                <div class="d-flex justify-between item-route-title">
                                    <b>Session 04: Môn Chuyên sâu</b>
                                    <span class="ml-half">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                            viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                            stroke-linecap="round" stroke-linejoin="round"
                                            class="lucide lucide-chevron-down">
                                            <path d="m6 9 6 6 6-6" />
                                        </svg>
                                    </span>
                                </div>
                                <div class="route-content pl-medium mt-half" v-if="showRouteContent5">
                                    <div class="list-lesson">
                                        <div class="item-lesson">
                                            <div class="item-lesson-title d-flex justify-between"
                                                v-for="(s, index) in listSubject" :key="s.id">
                                                <span>{{ s.subjectName }}</span>
                                                <span class="ml-half"></span>
                                            </div>
                                            <ul class="list-contents pl-medium mt-half">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="bg-grey">
                            <div class="post-thumbnail">
                                <img width="360" height="190"
                                    src="https://fsoft-academy.edu.vn/wp-content/uploads/2023/09/anh-02-1-360x190.jpg"
                                    class="attachment-small-rectangle size-small-rectangle wp-post-image" alt=""
                                    decoding="async">
                            </div>
                            <div class="post-more-info pa-half">
                                <div class="price-info">
                                    <h3 class="text-second main-price text-bold">7.900.000 <small>VNĐ</small></h3>
                                </div>
                                <table class="mb-0">
                                    <tbody>
                                        <tr>
                                            <td class="no-border pl-0 pr-0" style="width: 25px;"><img
                                                    src="https://fsoft-academy.edu.vn/wp-content/themes/flatsome-child/assets/images/icon-clock.svg"
                                                    alt="Salary"></td>
                                            <td class="no-border pl-small pb-half">
                                                <h4 class="text-bold">Thời lượng học</h4>
                                                32 giờ
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="no-border pl-0 pr-0" style="width: 25px;"><img
                                                    src="https://fsoft-academy.edu.vn/wp-content/themes/flatsome-child/assets/images/icon-location.svg"
                                                    alt="Skill"></td>
                                            <td class="no-border pl-small">
                                                <h4 class="text-bold"></h4>
                                                Tòa nhà Mitec, Đường Dương Đình Nghệ, Phường Yên Hoà, Quận Cầu Giấy, Hà Nội, Việt Nam
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class=" text-center">
                            <div>
                                <a class="btn btn-warning">
                                    Nhận tư vấn
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
<style scoped>
* {
    font-family: BaiJamjuree !important;
}
element.style {
    border-width: 1px 0px 1px 0px;
}

.contact-course .icon-box {
    align-items: center;
}

.text-left {
    text-align: left;
}

img.attachment-medium.size-medium {
    border-radius: 40px;
}

.icon-box {
    display: flex;
    align-items: center;
}

.icon-box-img {
    flex-shrink: 0;
    margin-right: 15px;
}

a.icon-circle:hover,
a.remove:hover,
a:hover {
    color: #000;
}

a:active,
a:hover {
    outline-width: 0;
}

a {
    color: #334862;
    text-decoration: none;
}

.bg-grey {
    background-color: #f5f5f5 !important;
}

img {
    display: inline-block;
    height: auto;
    max-width: 100%;
    vertical-align: middle;
}

.pa-half {
    padding: 15px !important;
}

h3.text-second.main-price.text-bold,
h4.text-bold {
    font-size: 14px;
}

.text-bold {
    font-weight: 700;
}

.text-second {
    color: #7ebf4f !important;
}

small {
    display: inline-block;
    font-size: 80%;
}

h4.text-bold {
    padding-top: 20px;
}

a.btn.btn-warning {
    width: 262px;
    height: 45px;
    margin: 10px;
    background-color: #f17126 !important;
    color: #f5f5f5;
}

.d-flex.justify-between.item-route-title {
    padding: 15px;
}

.justify-between {
    justify-content: space-between;
}
b, strong {
    font-weight: 700;
}
.pl-medium {
    padding-left: 30px !important;
}
</style>