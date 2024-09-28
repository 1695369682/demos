<!-- eslint-disable vue/no-parsing-error -->
<template lang="">
    </el-radio-group>
        <el-menu default-active="1-4-1" class="el-menu-vertical-demo" 
        @open="handleOpen" 
        @close="handleClose" 
        :collapse="isCollapse"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <h3>{{isCollapse?"后台":"校园驿站"}} </h3>
     <el-menu-item @click="menuclick(item)" v-for="item in nochildren" :key="item.name" :index="item.name">
                    <i :class="`el-icon-${item.icon}`"></i>
                    <span slot="title">{{item.label}}</span>
            </el-menu-item>       
        <el-submenu  v-for="item in havechildren" :key="item.label" :index="item.label">
            <template slot="title">
                <i :class="`el-icon-${item.icon}`"></i>
                <span slot="title">{{item.label}}</span>
            </template>
            <el-menu-item-group  v-for="subitem in item.children" :key="subitem.path">               
                    <el-menu-item @click="menuclick2(subitem)" :index="subitem.path">{{subitem.label}}</el-menu-item>
            </el-menu-item-group>
         </el-submenu>
        </el-menu>
</template>
<script>
import cookie from "js-cookie"

export default {
  data() {
    return {};
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //点击菜单
    menuclick(item) {
      if (
        this.$route.path !== item.path &&
        !(this.$route.path === "/home" && item.path === "/")
      ) {
        this.$router.push(item.path);
      }
      //
      this.$store.commit("selectMenu", item);
    },
    menuclick2(subitem) {
      if (this.$route.path !== subitem.path) {
        this.$router.push(subitem.path);
      }
    },
  },
  computed: {
    menudata(){
      //判断当前数据，如果缓存中没有，从store中获取
      return JSON.parse(cookie.get("menu"))|| this.$store.state.menu
    },
    //没有子菜单
    nochildren() {
      return this.menudata.filter((item) => !item.children);
    },
    //有子菜单
    havechildren() {
      return this.menudata.filter((item) => item.children);
    },
    //
    isCollapse() {
      //返回需要修改的值
      return this.$store.state.isCollapse;
    },
  },
};
</script>
<style lang="less" >
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu {
  height: 100%;
  border-right: none;
  h3 {
    color: #fff;
    text-align: center;
    line-height: 48px;
    font-size: 18.72px;
    font-weight: 700px;
  }
}
</style>