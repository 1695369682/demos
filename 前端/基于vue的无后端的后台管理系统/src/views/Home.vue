<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
    <el-row>
        <el-col :span="8">
          <el-card class="box-card">
                <!-- 2 -->
                  <div class="user">
                      <img src="../assets/logo.png" alt="">
                      <!-- 1 -->
                      <div class="userinfo">
                          <p class="name">Admin</p>
                          <p class="access">controler</p>
                      </div>
                      <!-- 1 -->
                    </div>
                  <!-- 2 -->
                  <!-- 3 -->
                  <div class="login">
                      <p>上次登录：<span>2023</span></p>
                      <p>登录地：<span>辽宁</span></p>
                  </div>
                  <!-- 3 -->
            </el-card>
              <!-- <el-card style="margin-top: 20px;height:460px;">
              <el-table :data="tableData" stripe style="width: 100%">
                 key值绑定可能有问题
                <el-table-column v-for="(val,key)  in tableLable" :prop="key" :label="val" :key="key"/>
              </el-table>
            </el-card> -->
        </el-col>

          <el-col :span="16">
                <!-- <div class="num">
                    <el-card class="bodystyle" v-for="item in countData" :key="item.name">
                        <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background:item.color}"></i>
                        <div class="detail">
                          <p class="one">${{item.value}}</p>
                          <p class="two">{{item.name}}</p>
                        </div>
                    </el-card>
                </div> -->

                <!-- <el-card style="height: 260px;">
                    <div ref="echart1" style="height:260px"></div>
                </el-card>
                <div class="graph">
                  <el-card style="height: 240px;">
                    <div ref="echart2" style="height:240px"></div>
                  </el-card>
                  <el-card style="height: 240px;">
                    <div ref="echart3" style="height:220px"></div>
                  </el-card>
                </div> -->
          </el-col>    
    </el-row>
</template>
<script type="moudle">
import { getData } from "../api";
import * as echarts from "echarts";
/* import { it } from "node:test"; */
export default {
  name:"home",
  data() {
    return {
      tableData: [],
      tableLable: {
        name: "名称",
        todayBuy: "今日消费",
        monthBuy: "月消费",
        totalBuy: "总消费",
      },
      countData: [
        {
          name: "今日支付订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "今日收藏订单",
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          name: "今日未支付订单",
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
        {
          name: "本月支付订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "本月收藏订单",
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          name: "本月未支付订单",
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
      ],
    };
  },
  mounted() {
   /*  console.log(typeof(getData)); */
    getData().then(({ data }) => {
      const { tableData } = data.data;
      this.tableData = tableData;
      /* --------------------------------------------------- */
      //基于准备好的dom，初始化echarts实例
      const echarts1 = echarts.init(this.$refs.echart1);
      //指定图标的配置项和数据
      var eoption = {};
      /* 处理数据xaxis */
      const { orderData, userData,videoData } = data.data;
      const xAxis = Object.keys(orderData.data[0]);
      const xAxisdata = {
        data: xAxis,
      };
      eoption.xAxis = xAxisdata;
      eoption.yAxis = {};
      eoption.legend = xAxisdata;
      eoption.series = [];
      xAxis.forEach((element) => {
        /* console.log(eoption.series); */
        eoption.series.push({
          name: element,
          data: orderData.data.map((item) => item[element]),
          type: "line",
        });
      });
      /* console.log(eoption); */
      //使用刚指定的配置项和数据显示图标
      echarts1.setOption(eoption);
    });
  },
};
</script>