import Vue from "vue";
import VueRouter from 'vue-router'

Vue.use(VueRouter)
const routes = [
    {
        path: "/",
        name:"main",
        component: ()=>import("@/views/Main.vue"),
        redirect:"/home",//重定向
        children: [
            /* { path: 'home', name:"home",component: Home },//首页
            { path: 'user', name:"user", component: User },//用户管理
            { path: "mall",  name:"mall",component: Mall },//商品管理
            { path: 'page1', name:"page1", component: PageOne },//页面1
            { path: 'page2', name:"page2", component: PageTwo }, *///页面2
        ]
    },
    {
        path:"/login",
        name:"login",
        component:()=>import("@/views/Login.vue")
    }
]
const router = new VueRouter({
    routes
})
export default router