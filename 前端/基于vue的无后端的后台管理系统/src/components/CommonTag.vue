<template>
  <div class="tabs">
    <el-tag
      v-for="(item ,index) in tags"
      :key="item.label"
      :closable="item.name !== 'home'"
      :effect="$route.name === item.name ? 'dark' : 'plain'"
      @click="changemenu(item)"
      @close="handclose(item,index)"
      size="small"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "CommonTag",
  data() {
    return {};
  },
  methods:{
    //点击tag跳转功能
    changemenu(item){
        this.$router.push({name:item.name})
    },
    //点击tag删除功能
    handclose(item,index){
      //调用store中的mutation
        this.$store.commit("closetag",item,index)
        const length=this.tags.length
        //删除之后的跳转逻辑
        if(item.name!==this.$route.name)
        {
          return
        }
        //表示删除的是最后一项
        if(index===length)
        {
          this.$router.push({
            name:this.tags[index-1].name
          })
        }
        else
        {
          this.$router.push({
            name:this.tags[index].name
          })
        }
    }
  },
  computed: {
    ...mapState({
      //获取到store中的数据，命名为tags
      tags: (state) => state.tabsList,
    }),
  },
  mounted(){

  }
};
</script>

<style lang="less" scoped>
.tabs{
  padding: 20px;
  .el-tag{
    margin-right: 15px;
    /* 鼠标hover小手样式 */
    cursor: pointer;
  }
}
</style>