import Call from '@/views/Call.vue'
import Home from '@/views/Home.vue'
import Contact from '@/views/Contact.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/contact',
      name: 'Call',
      component: Call
    }
    ,
    {
      path: '/dao-tao',
      name: 'Contact',
      component: Contact
    }
  ]
})

export default router
