import { createApp } from 'vue'
import App from './App.vue'
import router from './routes'
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap"
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import 'jquery';
const app = createApp(App)

app.use(router)
app.use(VueSweetalert2)

app.mount('#app')
