import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8081',
    timeout: 10000, // 请求超时时间
});

const app = createApp(App);
app.provide('axios', axiosInstance);
app.use(ElementPlus);
app.mount('#app');
