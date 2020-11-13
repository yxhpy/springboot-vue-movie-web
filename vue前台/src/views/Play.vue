
<template>
  <div class="liveView">
    <div class="play-big-title" :style="{left: bigTitleLeft}">
      正在播放 <svg t="1605144378000" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10866" width="32" height="32"><path d="M63.77 209.12v604.46a45.07 45.07 0 0 0 45.07 45.07h806.32a45.07 45.07 0 0 0 45.07-45.07V210.42a45.07 45.07 0 0 0-45.07-45.07H107.54a43.77 43.77 0 0 0-43.77 43.77z m859.48-5.63a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.09 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.09 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.09 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.08 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.05 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.1 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.07 0z m-105.05 0a11.38 11.38 0 0 1 0 16.1l-71 71a11.38 11.38 0 0 1-16.1 0 11.38 11.38 0 0 1 0-16.1l71-71a11.38 11.38 0 0 1 16.06 0z m241.33 501.14V487.17L617.55 595.9z m508.53-356.76H86.24v-22.76h851.52z" fill="#282727" p-id="10867"></path><path d="M429.23 487.17v217.46L617.55 595.9 429.23 487.17z" fill="#E02727" p-id="10868"></path></svg> {{movieDetail.title}}
    </div>
    <video-player class="vjs-custom-skin"
                  ref="videoPlayer"
                  :options="playerOptions"
                  @ready="onPlayerReadied"
                  @timeupdate="onTimeupdate"
                  :playsinline="playsinline">
    </video-player>
    <div class="play-menu">
      <template v-for="(values, key) in playSources">
        <div class="play-title">
          {{ key }}
        </div>
        <div class="play-group">
          <template v-for="item in values">
            <div :class="{'play-item':true, 'play-item-active': currentPid == item.pid}" @click="getByPid(item.pid)">{{ item.title }}</div>
          </template>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { videoPlayer } from 'vue-video-player'
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import 'videojs-contrib-hls'
import {getDetails, getMovieSources} from "@/plugins/api";

export default {
  name: 'Play',
  components: {
    videoPlayer
  },
  data () {
    return {
      initialized: false,
      currentTech: '',
      streams: {
        rtmp: '',
        hls: ''
      },
      currentPid: 0,
      bigTitleLeft:400+"px",
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0],
        overNative: true,
        autoplay: true,
        controls: true,
        language: 'zh-CN',
        techOrder: ['html5'],
        aspectRatio: '16:9',
        sourceOrder: true,
        fluid: true,
        poster:"https://img3.doubanio.com/view/photo/l/public/p2558292840.webp",
        notSupportedMessage: '此视频暂无法播放，请稍后再试',
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        },
        flash: {
          hls: { withCredentials: false }
        },
        html5: { hls: { withCredentials: false } },
        sources: [
          {
            withCredentials: false,
            type: 'application/x-mpegURL',
            src: ""
          },
          {
            withCredentials: false,
            type: 'video/mp4',
            src: ""
          }
        ]
      },
      movieDetail: null,
      playSources: null,
    }
  },
  computed: {
    player () {
      return this.$refs.videoPlayer.player
    },
    currentStream () {
      return this.currentTech === 'Flash' ? 'RTMP' : 'HLS'
    },
    playsinline () {
      let ua = navigator.userAgent.toLocaleLowerCase()
      // x5内核
      if (ua.match(/tencenttraveler/) != null || ua.match(/qqbrowse/) != null) {
        return false
      } else {
        // ios端
        return true
      }
    },
  },
  methods: {
    onPlayerReadied () {
      if (!this.initialized) {
        this.initialized = true
        this.currentTech = this.player.techName_
      }
    },
    // record current time
    onTimeupdate (e) {
      console.log('currentTime', e.cache_.currentTime)
    },
    getDetails(mid){
      let _this = this
      getDetails(mid).then(function (msg) {
        _this.movieDetail = msg.data.movie
        _this.playerOptions.poster = _this.movieDetail.img
        document.title =  document.title  + _this.movieDetail.title
      })
      getMovieSources(mid).then(function (msg) {
        for (let datum in msg.data.data) {
          _this.playSources = msg.data.data
          _this.getByPid(_this.$route.query.pid)
          return
        }
        _this.playSources = null
      })
    },
    getByPid(pid){
      for (let i in this.playSources) {
        for (let j of this.playSources[String(i)]) {
          if (j.pid == pid) {
            this.currentPid = pid
            this.playerOptions.sources[0].src = j.play_url
          }
        }
      }
    },
    setBigTitleLeft(){
      let _this = this
      let left = 0
      setInterval(function () {
        if (left < -1500)
          left = 2000
        _this.bigTitleLeft = left-- + "px"
      }, 5)
    }
  },
  mounted() {
    this.getDetails(this.$route.query.mid)
    this.setBigTitleLeft()
  }
}
</script>

<style scoped>
.liveView {
  position: relative;
  margin-top: 20px;
  overflow: hidden;
}
.vjs-custom-skin{
  width: 80%;
  float: left;
}
.play-menu{
  margin-left: 20px;
  width: 18%;
  height: 600px;
  background: #333333;
  float: left;
  color: white;
  overflow:auto;
}
.play-title{
  border-left: 5px solid red;
  padding-left: 5px;
  color: #409EFF;
}
.play-group{
  margin: 10px;
}
.play-item{
  background: #409EFF;
  display: inline-block;
  margin: 5px;
  padding: 5px;
  font-size: 10px;
}
.play-item:hover{
  color: black;
  background: #99a9bf;
  cursor: pointer;
}
.play-item-active{
  color: black;
  background: #99a9bf;
}
.play-big-title{
  font-weight: 1000;
  font-size: 30px;
  margin-bottom: 10px;
  position: relative;
  line-height: 40px;
  height: 40px;
}
</style>