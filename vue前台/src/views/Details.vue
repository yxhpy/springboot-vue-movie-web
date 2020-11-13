<template>
  <div :class="{'not-find-details':movieDetail===null, 'details':movieDetail!==null}">
    <div v-if="movieDetail===null">
      <div class="not-find">
        NOT FIND 404
      </div>
    </div>
    <div v-else>
      <div class="title">
        {{movieDetail.title}}
      </div>
      <div class="details-group">
        <el-image :src="movieDetail.img" class="image">
        </el-image>
        <div class="det-right">
          <div class="director">
            导演：
            <span v-for="item in movieDetail.directors">{{ item.dname }} </span>
          </div>
          <div class="scriptwriter">
            编剧：
            <span v-for="item in movieDetail.scriptwriters">{{ item.sname }} </span>
          </div>
          <div class="actor">
            演员：
            <span v-for="item in movieDetail.actors">{{ item.aname }} </span>
          </div>
          <div class="m_type">
            类型：
            <span v-for="item in movieDetail.mtypes">{{ item.tname }} </span>
          </div>
          <div class="local">
            制片国家/地区：
            <span v-for="item in movieDetail.locals">{{ item.lname }} </span>
          </div>
          <div class="language">
            语言：
            <span v-for="item in movieDetail.languages">{{ item.lname }} </span>
          </div>
          <div class="releaseMovie" v-if="movieDetail.releaseMovie">
            上映时间：{{ movieDetail.releaseMovie }}
          </div>
          <div class="duration"  v-if="movieDetail.duration">
            片长：{{ movieDetail.duration }}
          </div>
        </div>
      </div>
      <div class="not-sources" v-if="playSources === null">
        管理员还未添加源
      </div>
      <div class="play" v-for="(items, keys) in playSources">
        <i class="el-icon-view play-title">
          {{keys}}
        </i>
        <div class="play-links">
          <template v-for="item in items">
            <span @click="linkTo(item.pid)" class="play-link">
               <i class="el-icon-video-play">{{item.title}}</i>
            </span>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getDetails, getMovieSources} from "@/plugins/api";

export default {
  name: "Details",
  data(){
    return{
      movieDetail:null,
      playSources:null,
    }
  },
  methods:{
    getDetails(mid){
      let _this = this
      getDetails(mid).then(function (msg) {
        _this.movieDetail = msg.data.movie
        document.title =  _this.movieDetail.title + document.title
      })
      getMovieSources(mid).then(function (msg) {
        for (let datum in msg.data.data) {
          _this.playSources = msg.data.data
          return
        }
        _this.playSources = null
      })
    },
    linkTo(pid){
      this.$router.push({name:'play', query:{'mid':this.$route.query.mid, 'pid':pid}})
    }
  },
  created() {

  },
  mounted() {
    this.getDetails(this.$route.query.mid)
  }
}
</script>

<style scoped>
.not-find-details{
  margin-top: 20px;
  background: url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604931676037&di=6b5e544cc76e90987aa7b755d009ae2c&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F3c6371bd86070f674c250fdb147cbaa53e4e1d446a06d-A3wsn3_fw658");
  background-size: cover;
  width: 100%;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.details{
  margin-top: 20px;
}
.details .title{
  color: #333333;
  font-size: 20px;
  font-weight: 1000;
  padding: 10px 0;
}
.details .image{
  width: 200px;
  float: left;
}
.not-find{
  padding: 300px 0;
  font-size: 100px;
  color: #d3dce6;
}
.details-group{
  overflow: hidden;
}
.det-right{
  margin-left: 20px;
  float: left;
  width: 900px;
}
.play{
  margin: 20px 0;
}
.play .play-title{
  font-size: 20px;
  font-weight: 900;
  border-left: 3px solid red;
  padding: 5px;
}
.play-links{
  padding: 20px 0;
}
.play-link{
  display: inline-block;
  margin: 10px 10px;
  background: #475669;
  padding: 10px;
  color: white;
  cursor: pointer;
}
.play-link:hover{
  opacity: 0.5;
}
.not-sources{
  font-weight: 1000;
  font-size: 20px;
  padding: 20px 0;
  color: red;
}
</style>