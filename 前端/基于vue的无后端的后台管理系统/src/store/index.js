import Vue from "vue";
import Vuex from "vuex"
import cookie from "js-cookie"


Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        isCollapse: false,
        //控制菜单展开还是收起
        tabsList: [
            {
                path: "/",
                name: "home",
                label: "首页",
                icon: "home",
                url: "home"
            }],
        menu: []
    },
    mutations: {
        //修改菜单展开收起的方法
        collapseMenu(state) {
            state.isCollapse = !state.isCollapse
        },
        selectMenu(state, val) {
            /* console.log(val); */
            //判断添加数据是否为首页
            if (val !== "home") {
                const index = state.tabsList.findIndex(item => item.name === val.name)
                if (index === -1) {
                    state.tabsList.push(val)
                }
            }
        },
        //删除指定的tag数据
        closetag(state, item) {
            console.log(state, item);
            const index = state.tabsList.findIndex(val => val.name === item.name)
            state.tabsList.splice(index, 1)
        },
        //设置menu数据
        setMenu(state, val) {
            state.menu = val
            //缓存菜单信息
            cookie.set("menu", JSON.stringify(val))
        },
        //动态注册路由
        addmenu(state, router) {
            /* console.log(router); */
            //判断缓存是否有数据
            if (!cookie.get("menu"))  { return}
            const menu=JSON.parse(cookie.get("menu"))
            /* console.log(menu); */
            state.menu=menu
            //处理动态路由数据
            const menuarr=[]
            menu.forEach(item => {
                if(item.children){
                    item.children=item.children.map(item=>{
                        /* console.log(item); */
                        item.component=()=>import(`../views/${item.url}`)
                        return item
                    })
                    menuarr.push(...item.children)
                    /* console.log(menuarr); */
                }
                else{
                    item.component=()=>import(`../views/${item.url}`)
                    menuarr.push(item)
                }
            });
            console.log(menuarr);
            //路由的动态添加
            menuarr.forEach(item=>{
                router.addRoute("main",item)
                console.log(router);
            })
            /* console.log(menuarr); */
        }
    }
})