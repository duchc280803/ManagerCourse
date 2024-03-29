import AllCourse from "@/pages/AllCourse.vue";
import Class from "@/pages/Class.vue";
import ClassInformation from "@/pages/ClassInformation.vue";
import ClassOfTeacher from "@/pages/ClassOfTeacher.vue";
import Classroom from "@/pages/ClassRoom.vue";
import Course from "@/pages/Course.vue";
import CourseOfStudent from "@/pages/CourseOfStudent.vue";
import CourseSubject from "@/pages/CourseSubject.vue";
import Login from "@/pages/Login.vue";
import ManagePoint from "@/pages/ManagePoint.vue";
import Order from "@/pages/Order.vue";
import PersonalInformation from "@/pages/PersonalInformation.vue";
import PointBySubject from "@/pages/PointBySubject.vue";
import PointSum from "@/pages/PointSum.vue";
import Schedule from "@/pages/Schedule.vue";
import Student from "@/pages/Student.vue";
import Subject from "@/pages/Subject.vue";
import Teacher from "@/pages/Techer.vue";
import ScheduleOfStudent from "@/pages/ScheduleOfStudent.vue";
import { createRouter, createWebHistory } from "vue-router";
import Register from "@/pages/Register.vue";
import ScheduleOfTeacher from "@/pages/ScheduleOfTeacher.vue";
import MarkPoint from "@/pages/MarkPoint.vue";
import Send_mail from "@/pages/Send_mail.vue";
import Contact from "@/pages/Contact.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/teacher",
      name: "Teacher",
      component: Teacher,
    },
    {
      path: "/student",
      name: "Student",
      component: Student,
    },
    {
      path: "/class",
      name: "Class",
      component: Class,
    },
    {
      path: "/course",
      name: "Course",
      component: Course,
    },
    {
      path: "/order",
      name: "Order",
      component: Order,
    },
    {
      path: "/personal-information",
      name: "PersonalInformation",
      component: PersonalInformation,
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/subject",
      name: "Subject",
      component: Subject,
    },
    {
      path: "/course/subject/:id",
      name: "CourseSubject",
      component: CourseSubject,
    },
    {
      path: "/class/information/:id/:courseName",
      name: "ClassInformation",
      component: ClassInformation,
    },
    {
      path: "/student/course",
      name: "CourseOfStudent",
      component: CourseOfStudent,
    },
    {
      path: "/all-course",
      name: "AllCourse",
      component: AllCourse,
    },
    {
      path: "/point-subjects",
      name: "PointBySubject",
      component: PointBySubject,
    },
    {
      path: "/point-sum",
      name: "PointSum",
      component: PointSum,
    },
    {
      path: "/class/teacher",
      name: "ClassOfTeacher",
      component: ClassOfTeacher,
    },
    {
      path: "/schedule",
      name: "Schedule",
      component: Schedule,
    },
    {
      path: "/class-room",
      name: "ClassRoom",
      component: Classroom,
    },
    {
      path: "/point",
      name: "ManagePoint",
      component: ManagePoint,
    },
    {
      path: "/schedule-of-student",
      name: "ScheduleOfStudent",
      component: ScheduleOfStudent
    },
    {
      path: "/register",
      name: "Register",
      component: Register
    },
    ,
    {
      path: "/schedule-of-teacher",
      name: "ScheduleOfTeacher",
      component: ScheduleOfTeacher
    },
    ,
    {
      path: "/mark-point",
      name: "MarkPoint",
      component: MarkPoint
    },
    {
      path: "/send-mail",
      name: "Send_mail",
      component: Send_mail
    },
    {
      path: "/contact",
      name: "Contact",
      component: Contact
    },
  ],
});

export default router;
