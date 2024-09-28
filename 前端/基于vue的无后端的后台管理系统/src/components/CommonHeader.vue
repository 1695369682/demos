<template lang="">
    <div class="header-container">
        <div class="l-contect">
            <el-button style="margin-right:20px"  @click="handlemenu" icon="el-icon-menu" size="mini"></el-button>
            <span class="text">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item v-for="item in tags" :key="item.path" :to="{ path: '/' }">{{item.label}}</el-breadcrumb-item>
                </el-breadcrumb>
            </span>
        </div>
        <div class="r-contect">
            <el-dropdown @command="handclick">
  <span class="el-dropdown-link">
            <img class="user" src="../image/{N0TI0KYD3F78GU6[26]88D.png" alt="">
        </span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item command="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
        </el-dropdown>
        </div>
    </div>
</template>
<script lang="ts">
import { mapState } from "vuex";
import cookie from "js-cookie"

export default {
  data() {
    return {};
  },
  methods: {
    handlemenu() {
      this.$store.commit("collapseMenu");
    },
    handclick(command){
      if(command==="logout")
      {
      //清除token    
      cookie.remove("token")
      //清楚menu
      cookie.remove("menu")
      this.$router.push("/login")
      }
    }
  },
  computed: {
    ...mapState({
      tags: (state) => state.tabsList,
    }),
  },
  mounted() {
    /* console.log(this.tags); */
  },
};
</script>
<style lang="less">
.header-container {
  background-color: #333;
  height: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  .text {
    color: #fff;
    font-size: 14px;
    margin-left: 10px;
  }
  .r-contect {
    .user {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
  }
}
.l-contect {
    display: flex;
    align-items: center;
    .breadcrumb__item {
        .breadcrumb__inner{
            font-weight: normal;
        .is-link{
            color: #fff;
        }
      } 
      &:last-child{
        .breadcrumb__inner{
            color:#fff;
        }
      } 
    }
  }
</style>