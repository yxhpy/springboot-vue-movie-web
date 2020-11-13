<template>
  <div>
    <div class="small-nav">
      <el-form :inline="true" :model="formInline" size="mini">
        <el-form-item>
          <el-input v-model="formInline.search" placeholder="搜 索 ..." @change="searchMovie"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="searchMovie">
            <i class="el-icon-search">搜索</i>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="search-ret">
      <template v-if="searchData.length !== 0">
        <template v-for="item in searchData">
          <el-card shadow="hover" class="card-group">
            <div class="card-left">
              <h1 v-html="item.title">{{item.title}}</h1>
              <el-image :src="item.img">
              </el-image>
            </div>
            <div class="card-right">
              <div class="release">
                <template v-if="item.releaseMovie">
                  发布时间：{{item.releaseMovie}}
                </template>
                <template v-else>
                  发布时间：未知
                </template>
              </div>
              <div class="actors">
                <template v-if="item.actors.length!==0">
                  演员：<span v-for="_item in item.actors">{{_item.aname}} / </span>
                </template>
                <template v-else>
                  演员：未知
                </template>
              </div>
              <router-link :to="{name:'detail', query:{'mid': item.mid}}">
                <div class="more">点 击 详 情. . . .</div>
              </router-link>
            </div>
          </el-card>
        </template>
      </template>
      <template v-else>
        <span class="not-data">暂无数据....</span>
      </template>
    </div>
  </div>
</template>

<script>
import {getMovie} from "@/plugins/api";

export default {
  name: "Search",
  data(){
    return {
      formInline:{
        search:"",
        page:1,
        size:10,
        total:0
      },
      searchData:[]
    }
  },
  methods:{
    searchMovie(){
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      let _this = this
      if (!this.formInline.search){
        this.searchData = []
        loading.close();
      } else {
        this.searchData = []
        var params = new URLSearchParams();
        params.set("page", this.formInline.page)
        params.set("size", this.formInline.size)
        params.set("search", this.formInline.search)
        getMovie(params).then(function (msg) {
          _this.formInline.total = msg.data.pages.total
          _this.searchData = msg.data.pages.records
          for (let searchDatum of _this.searchData) {
            searchDatum.title = searchDatum.title.replace(_this.formInline.search, "<i style='color: red; font-size: 30px'>"+_this.formInline.search+"</i>")
          }
          loading.close();
        })
      }
    }
  },
  mounted() {
      this.searchMovie();
  }
}
</script>

<style scoped>
.small-nav{
  margin: 10px 0;
  height: 50px;
  background: #333333;
  line-height: 50px;
  border-radius: 3px;
  box-shadow: 5px 5px 10px;
}
.small-nav .el-form{
  padding: 10px;
}

.search-ret{
  width: 100%;
  text-align: center;
  overflow: hidden;
}
.not-data{
    display: inline-block;
    padding: 100px;
}
.card-left{
  float: left;
  width: 300px;
}
.card-left h1{
  margin-bottom: 20px;
  font-weight: 1000;
  font-size: 20px;
}
.card-right{
  width: 1000px;
  float: left;
}
.card-right div{
  font-weight: 1000;
  font-size: 20px;
  margin: 50px;
}
.card-right .more{
  color: white;
  cursor: pointer;
}
.card-group:hover{
  opacity: 0.8;
  background: white;
  color: white;
}
.card-group:hover .more{
  color: #409EFF;
}
.hot-key{
  color: red;
}
</style>