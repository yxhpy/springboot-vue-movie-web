<template>
  <div>
    <div class="class-title">
      选择你最喜欢的电影类型
    </div>
    <div class="mtype">
      <span>类型：</span>
      <span v-for="item in mtypes" class="type-item" @click="filterType(item.tid)">
        {{ item.tname }}
      </span>
    </div>
    <div class="movie-list">
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
    <div class="pagination">
      <el-pagination
          @current-change="handleCurrentChange"
          :page-size="PageData.size"
          layout="prev, pager, next, jumper"
          :total="PageData.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getMovie, getTypes} from "@/plugins/api";

export default {
  name: "Classifcation",
  data(){
    return {
      mtypes:[],
      movieList: [],
      PageData: {
        page:1,
        size:36,
        tid:'',
        total:1000
      }
    }
  },
  computed:{
  },
  methods:{
    getTypes(){
      var _this = this
      getTypes().then(function (msg) {
        _this.mtypes = msg.data.data
      })
    },
    getPageData(){
      var urlSearchParams = new URLSearchParams();
      urlSearchParams.set("page", this.PageData.page)
      urlSearchParams.set("size", this.PageData.size)
      urlSearchParams.set("tid", this.PageData.tid)
      let _this = this
      getMovie(urlSearchParams).then(function (msg) {
        _this.movieList = msg.data.pages.records
        _this.PageData.total = msg.data.pages.total
      })
    },
    filterType(tid){
      this.PageData.tid = tid
      this.PageData.page = 1
      this.getPageData()
    },
    handleCurrentChange(val){
      this.PageData.page = val
      this.getPageData()
    }
  },
  created() {
    this.getTypes()
    this.getPageData()
  }
}
</script>

<style scoped>
.mtype{

}
.mtype-item{
  margin-top: 10px;
  margin-right: 40px;
}
.class-title{
  margin: 20px 0;
  font-size: 20px;
  font-weight: 1000;
}
.type-item{
  color: #909399;
  cursor: pointer;
  font-weight: 1000;
}
.type-item:hover{
  color: #409EFF;
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
.pagination{
  margin: 30px;
  text-align: center;
}
</style>