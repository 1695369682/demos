import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import  store  from './store';
import "@/api/mock";
import cookie from "js-cookie"
/* import VueParticles from 'vue-particles' */

//粒子动效背景插件
/* Vue.use(VueParticles) */

Vue.config.productionTip = false
Vue.use(ElementUI)

//全局前置路由守卫
router.beforeEach((to,from,next)=>{

  //判断token存不存在
  const token= cookie.get("token")
  //token不存在说明没登陆，跳转至login
  if(!token&& to.name!=="login"){
    next({name:"login"})
  }
  //如果token存在，说明用户登录，此时跳转至首页
  else if(token&&to.name==="login"){
    next({name:"home"})
  }
  else{
    next();
  }
})
new Vue({
  store,
  router,
  render: h => h(App),
  created(){
    store.commit("addmenu",router)
  }
}).$mount('#app')
