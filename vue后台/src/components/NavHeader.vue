<template>
  <div class="nav-header">
    <div class="nav-header-left">
      <i class="el-icon-menu" @click="$store.state.navAsideCollapse = !$store.state.navAsideCollapse"></i>
      <span class="nav-header-logo">ssm后台管理系统</span>
    </div>
    <div class="nav-header-right">
      <div class="nav-head-img">
        <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604647560026&di=781900cb2d863a0bd64e4d69615d4ea5&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F05%2F20170805010159_vRrCL.thumb.700_0.jpeg" alt="">
      </div>
      <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link">
          admin<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="index">主页</el-dropdown-item>
          <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavHeader",
  methods:{
    handleCommand(command){
        if (command === "loginout"){
            this.$router.push({name:'login'})
        }
    },
  }
}
</script>
<style scoped>
  .nav-header{
    height: 60px;
    background: #333333;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    font-size: 22px;
    color: white;
    line-height: 60px;
    padding:0 20px;
    overflow: hidden;
  }
  .nav-header-left{
    float: left;
  }
  .nav-header-logo{
    margin-left: 30px;
  }
  .nav-header-right{
    float: right;
  }
  .nav-head-img{
    float: left;
  }
  .nav-head-img img{
    margin-top: 10px;
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
  .nav-header-right .el-dropdown{
    margin-left: 10px;
    float: left;
    color: white;
    cursor: pointer;
  }
</style>