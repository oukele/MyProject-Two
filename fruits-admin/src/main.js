import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import './plugins/iview.js'
import router from './router'


import index from './views/index.vue'
import login from './views/login.vue'

Vue.config.productionTip = false

axios.defaults.withCredentials = true;//请求携带cookie信息

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
