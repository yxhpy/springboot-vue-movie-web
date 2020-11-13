<template>
  <div class="index">
    <div class="slider-show">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in viewPager" :key="item">
          <h3 class="medium">
            <el-image :src="item" fit="scale-down"></el-image>
          </h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="card-list">
      <i class="el-icon-reading card-icon"></i>
      <span>热门推荐</span>
      <el-row :gutter="12">
        <el-col v-for="item in movieList" :span="4">
          <router-link :to="{name:'detail', query:{'mid': item.mid}}">
            <el-card shadow="hover" class="card-item" :body-style="{ padding: '0px' }">
              <div class="img-group">
                <el-image :src="item.img">
                </el-image>
                <i class="el-icon-video-play" >
                  <span>&nbsp;播放次数{{ item.viewNum }}万</span>
                </i>
              </div>
              <span class="card-item-title">
                {{ item.title.length<=10?item.title:item.title.substring(0, 10)+"..." }}
              </span>
          </el-card>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {getMovie} from "@/plugins/api";

export default {
  name: "Index",
  data(){
    return{
      movieList:[],
      viewPager:[
          "http://img.lingxinfm.com/images/2020-11/0dbf6b7dbf6f4d6781b5e02097284825.jpg",
        "http://img.lingxinfm.com/images/2020-10/2a75a20cde6548fd990ca874205ada0b.jpg",
      "http://img.lingxinfm.com/images/2020-11/88115efc6f8a48e4bffff8ff365a7e71.jpg"],
    }
  },
  mounted() {
    let data = new URLSearchParams();
    data.append("page", 1)
    data.append("size", 12)
    let _this = this
    getMovie(data).then(function (msg) {
      _this.movieList = msg.data.pages.records
    })
  }
}
</script>

<style scoped>
.index{
  margin-top: 20px;
  width: 1500px;
  margin-left: auto;
  margin-right: auto;
}
.slider-show img{

}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.card-list{
  font-size: 15px;
  font-weight: 1000;
}
.card-list .card-icon{
  padding: 10px;
  border-left: 5px solid red;
}
.card-item{
  position: relative;
  text-align: center;
}
.img-group{
  z-index: 10;

}
.img-group i{
  position: relative;
  bottom: 32px;
  right: -50px;
  color: white;
  z-index: 20;
}
.card-item:hover{
  color: #99a9bf;
  opacity: 0.7;
  cursor: pointer;
}
.img-group i{
  background-color: black;
  opacity: 0.5;
}
.img-group .el-image{
  max-height: 300px;
  height: auto;
}
</style>