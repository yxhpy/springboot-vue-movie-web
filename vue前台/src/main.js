import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/css/common.css'
Vue.config.productionTip = false
import './assets/icon/iconfont.css'
import './assets/icon/iconfont'
router.beforeEach((to, from , next)=>{
  if (to.meta.title){
    document.title = to.meta.title
  }
  next()
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
