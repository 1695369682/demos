<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
    <div class="manage">
        <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="50%"
            :before-close="handleClose">
            <!-- 用户的表单信息 -->
            <el-form ref="form" :rules="rules" :model="form" label-width="100px"  :inline="true">
                <el-form-item label="姓名" prop="name">
                    <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-input placeholder="请输入年龄" v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-select v-model="form.sex" placeholder="请选择">
                        <el-option label="男" :value="1"></el-option>
                        <el-option label="女" :value="0"></el-option>
                    </el-select>
                </el-form-item prop="burth">
                <el-form-item label="出生日期">
                        <el-date-picker
                        v-model="form.birth"
                        type="date"
                        placeholder="出生日期" prop="birth" value-format="yyyy-MM-DD">
                        </el-date-picker>
                    </el-input>
                </el-form-item>
                <el-form-item label="住址" prop="addr">
                    <el-input placeholder="请输入地址" v-model="form.addr"></el-input>
                </el-form-item>
              
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false" @click1="cancel">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>
        </el-dialog>

        <div class="manage-header">
            <el-button  @click="handadd" type="primary" plain>
                +新增
            </el-button>
            <!-- form搜索 -->
            <el-form :inline="true" :model="searchform">
                <el-form-item>
                  <el-input placeholder="输入搜索内容"  v-model="searchform.name"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>

       <div class="table">
              <el-table
                  :data="tableData"
                style="margin-top:10px">
                  <el-table-column
                      prop="name"
                      label="姓名">
                  </el-table-column>
                  <el-table-column
                      prop="age"
                      label="年龄">
                  </el-table-column>
                  <el-table-column
                      prop="sex"
                      label="性别">
                      <template slot-scope="scope">
                        <span style="margin-left:10px">{{scope.row.sex==1 ?"男":"女"}}</span>
                      </template>
                  </el-table-column>
                  <el-table-column
                      prop="birth"
                      label="出生日期">
                  </el-table-column>
                  <el-table-column
                      prop="addr"
                      label="地址">
                  </el-table-column>
                  <el-table-column
                      prop=""
                      label="">
                      <div slot-scope="scope">
                      <el-button size="mini" @click="handedit(scope.row)">编辑</el-button>
                      <el-button type="danger" size="mini" @click="handdel(scope.row)">删除(用不了)</el-button>
                      </div>                
                  </el-table-column>
          </el-table>

          <div  class="page">
              <el-pagination
              layout="prev, pager, next"
                :total="total"
                @current-change="handlepage">
              </el-pagination>
          </div>
       </div>
    </div>
</template>
<script>
import { getUser, addUser, editUser,deleteUser } from "../api/index";
export default {
  data() {
    return {
      dialogVisible: false, //控制表单显示 false为不显示
      form: {
        name: "",
        age: "",
        sex: "",
        birth: "",
        addr: "",
      },
      rules: {
        name: [{ required: true, message: "请输入姓名" }],
        age: [{ required: true, message: "请输入年龄" }],
        sex: [{ required: true, message: "请选择性别" }],
        birth: [{ required: true, message: "请输入出生日期" }],
        addr: [{ required: true, message: "请输入地址" }],
      },
      tableData: [],
      modelType: 0, //0表示新增，1表示编辑
      total: 0,//当前数据总条数
      pageData: {
        page: 1,
        limit: 10
      },
      searchform: {
        name: ""
      }
    };
  },
  methods: {
    //是否确认关闭（可删
    /*  handleClose(done) {
       this.$confirm("确认关闭？")
         .then((_) => {
           done();
         })
         .catch((_) => {});
     }, */
    //提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          //后续对表单数据处理
          if (this.modelType == 0) {
            addUser(this.form).then(() => {
              //重新获取列表的接口
              this.getlist();
            });
          } else {
            editUser(this.form).then(() => {
              //重新获取列表的接口
              this.getlist();
            });
          }
          //清空表单数据
          this.$refs.form.resetFields();

          this.dialogVisible = false;
        }
      });
    },
    //弹窗关闭的时候
    handleClose() {
      this.$refs.form.resetFields();
      this.dialogVisible = false;
    },
    cancel() {
      this.handleClose();
    },
    handedit(row) {
      this.getlist();
      this.modelType = 1;
      this.dialogVisible = true;
      //对当前行数据进行回显，注意不能直接用this.form=row赋值
      //第一种方式
      /*  this.form=JSON.parse(JSON.stringify(row)) */
      //第二种方式
      this.form = { ...row }; //对数据进行深拷贝
    },
    //获取列表数据
    getlist() {
      /* console.log(this.total) */
      getUser({params:this.pageData}).then(({ data }) => {
        console.log(data);
        this.tableData = data.list;
        this.total = data.count || 0
      });
    },
    //-------------------------------------------
    handdel(row) {
      /* console.log(row) */
      this.$confirm("确认删除？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteUser({id:row.id}).then(() => {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            //重新获取列表接口
            this.getlist();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    handadd() {
      this.modelType = 0;
      this.dialogVisible = true;
    },
    //选择页码的回调函数
    handlepage(val) {
      /* console.log(val) */
      this.pageData.page = val
      this.getlist()
    },
    //列表查询
    search() {
      this.getlist()
    }
  },
  mounted() {
    /* console.log(typeof(getUser)); */
    this.getlist();
  },
};
</script>
<style lang="less">
.manage {
  height: 90%;

  .manage-header {
    display: flex;
    justify-content: space-between;
    align-content: center;
  }

  .table {
    position: relative;
    height: 100%;

    .page {
      position: absolute;
      right: 20px;
    }
  }
}
</style>