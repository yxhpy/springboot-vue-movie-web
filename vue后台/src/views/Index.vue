<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="count-title">
            导演总数
          </div>
          <div class="count-num">
            <i class="el-icon-user-solid"></i>
            {{ director_num }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="count-title">
            演员总数
          </div>
          <div class="count-num">
            <i class="el-icon-user-solid"></i>
            {{ actor_num }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="count-title">
            电影总数
          </div>
          <div class="count-num">
            <i class="el-icon-video-camera"> </i>
            {{ movie_num }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="count-title">
            编剧总数
          </div>
          <div class="count-num">
            <i class="el-icon-user-solid"></i>
            {{scriptwriter_num}}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12">
      <el-col :span="12">
        <el-card shadow="hover">
          <div id="card1" style="width: 100%; height: 400%"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <div id="card2" style="width: 100%; height: 400%"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getCountAll, getLocalTop5, getType} from "../plugins/api";

export default {
  name: "Index",
  data(){
    return {
      actor_num:0,
      scriptwriter_num:0,
      movie_num:0,
      director_num:0
    }
  },
  methods: {
    drawCard1() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById('card1'));
      // 指定图表的配置项和数据
      getType().then(function (msg) {
        var option = {
          title: {
            text: '电影类型数量柱状图'
          },
          tooltip: {},
          legend: {
            data:['数量']
          },
          xAxis: {
            data: msg.data.data.name
          },
          yAxis: {},
          series: [{
            name: '数量',
            type: 'bar',
            data: msg.data.data.value
          }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })

    },
    drawCard2() {
      var myChart = this.$echarts.init(document.getElementById('card2'));
      getLocalTop5().then(function (msg) {
        var datas = msg.data.data
        var labels = msg.data.label
        var option = {
          title: {
            text: 'TOP5国家拍摄电影数',
            subtext: 'TOP5',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: labels
          },
          series: [
            {
              name: '电影数',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: datas,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        myChart.setOption(option);
      })

    },
    getCountAll(){
      let _this = this
      getCountAll().then(function (msg) {
        _this.actor_num = msg.data.data.actor
        _this.scriptwriter_num = msg.data.data.scriptwriter
        _this.movie_num = msg.data.data.movie
        _this.director_num = msg.data.data.director
      })
    }
  },
  computed:{

  },
  mounted() {
    this.drawCard1()
    this.drawCard2()
    this.getCountAll()
  }
}
</script>

<style scoped>
.el-card{
  text-align: center;
}
.count-title{
  font-weight: 900;
  font-size: 20px;
}
.count-num{
  margin-top: 30px;
  font-size: 30px;
}
</style>