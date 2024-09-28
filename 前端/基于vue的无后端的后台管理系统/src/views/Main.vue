<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
    <div>
        <el-container style="height:100vh">
        <el-aside width="auto" >
            <common-aside></common-aside>
        </el-aside>
        <el-container>
            <el-header>
            <common-header/>
            </el-header>
            <common-tag/>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
        </el-container>
       
    </div>
</template>
<script type="moudle">
import CommonAside from "@/components/CommonAside.vue";
import CommonHeader from "@/components/CommonHeader.vue";
import CommonTag from "@/components/CommonTag.vue"

export default {
  data() {
    return {};
  },
  components: { CommonAside, CommonHeader,CommonTag },
};
</script>
<style lang="less">
.el-header {
  padding: 0;
}
/* .el-aside{
  height: 100%;
} */
</style>