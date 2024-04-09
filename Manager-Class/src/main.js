import { createApp } from 'vue'
import App from './App.vue'
import router from './routes'
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap"
import 'sweetalert2/dist/sweetalert2.min.css';
import ToastPlugin from 'vue-toast-notification';

const app = createApp(App)

app.use(router)
app.use(ToastPlugin);

app.mount('#app')
