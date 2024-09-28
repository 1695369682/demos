<template>
  <div class="bg">
    <el-form ref="form" class="login_total" :model="form" :rules="rules">
      <h3 class="login_title">Welcome</h3>
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username" placeholder="输入账号" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="PassWord" placeholder="输入密码" />
      </el-form-item>
      <el-form-item>
        <el-button style="margin-left: 130px" type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* import Mock from 'mockjs';*/
import cookie from "js-cookie"
import { getMenu } from "@/api/index"
export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            trigger: "blur",
            message: "用户名不为空",
          },
        ],
        password: [{ required: true, trigger: "blur", message: "输入密码" }],
      },
    };
  },
  methods: {
    login() {
      //token信息
      // const token = Mock.Random.guid()
      //token信息存入cookie用于不同页面间的通信
      // cookie.set("token", token)

      //校验通过
      this.$refs.form.validate((valid) => {
        if (valid) {
          getMenu(this.form).then(({ data }) => {
            /* console.log(data); */
            if (data.code === 20000) {
              cookie.set("token", data.data.token)
              //获取菜单数据，存入store
              this.$store.commit("setMenu",data.data.menu)
              this.$store.commit("addmenu",this.$router)
              //跳转到首页
              this.$router.push("/home")
            }
            else{
              this.$message.error('密码错误');
            }
          })
        }
      })


    }
  }
};
</script>

<style lang="less" scoped>
.bg {
  display: flex;
  height: 961px !important;
  background-image: url(@/image/R-C.png);

  /deep/.login_total {
    width: 350px;
    border: 1px solid #eaeaea;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    background-color: #fff;
    border-radius: 15px;
    box-shadow: 0 0 25px #cac6c6;

    .login_title {
      text-align: center;
      margin-top: 40px;
      color: #505458;
    }
  }
}
</style>