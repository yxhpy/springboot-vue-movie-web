<template>
  <div class="nav-bar">
    <div class="nav-bar-mid">
      <span class="logo">
      <i class="el-icon-video-play"></i>
      YXHPY 影院
    </span>
      <div class="nav-left">
        <template>
          <router-link to="/">主页</router-link>
          <router-link to="/class">分类</router-link>
          <router-link to="/search">搜索</router-link>
        </template>
      </div>
      <div class="nav-right"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBar"
}
</script>

<style scoped>
.nav-bar{
  background-color: #333333;
  height: 60px;
  width: 100%;
  line-height: 60px;
}
.logo{
  color: white;
  font-size: 30px;
  float: left;
}
.nav-bar-mid{
  width: 1500px;
  margin-left: auto;
  margin-right: auto;
}
.nav-left{
  float: left;
  margin-left: 10px;
}
.nav-left a{
  color: white;
  padding: 20px;
  font-weight: 800;
}
.nav-left a:hover{
  color: #99a9bf;
}
</style>