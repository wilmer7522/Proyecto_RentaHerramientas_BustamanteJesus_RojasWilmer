import { createApp } from 'vue'
import App from './App.vue' // App.vue será el wrapper con <router-view>
import { router } from './router'

createApp(App).use(router).mount('#app')

