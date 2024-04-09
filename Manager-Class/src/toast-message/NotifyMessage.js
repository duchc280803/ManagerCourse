// File: notifications.js
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
export function notifySuccess(message) {
  toast.success(message);
}

export function notifyError(message) {
  toast.error(message);
}
