<template>
    <div id="login">
        <div class="header">
            电影SSM后台管理系统
        </div>
        <div class="login-form">
            <el-form ref="form" :model="form" :rules="rules">
                <el-form-item prop="username" :error="form_msg.username">
                    <el-input  v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item prop="password" :error="form_msg.password">
                    <el-input  type="password" v-model="form.password"></el-input>
                </el-form-item>
                <div class="sub-btn">
                    <el-button type="primary" @click="onSubmit" class="login-btn">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
import {loginForm} from "../plugins/api";
import router from "@/router";

export default {
    name:"Login",
    data() {
      return {
        form: {
          username: 'yxhpy',
          password: 'admin'
        },
        form_msg:{
          username:null,
          password:null
        },
        rules:{
            username:[{required:true, message:"请输入账号", trigger:"blur"}],
            password:[{required:true, message:"密码", trigger:"blur"}]
        }
      }
    },
    methods: {
      onSubmit() {
        let _this = this
        let _router = router
        loginForm(this.form).then(function (msg) {
            if (msg.code === 200){
              _this.form_msg = msg.data.error;
              _this.$message({
                message:"登录失败",
                type:"error"
              })
            } else {
              _this.$message({
                message:"登录成功",
                type:"success"
              })
              _router.push({"name":"index"})
            }
        })
      }
    }
}
</script>

<style>

#login{
    background: url("../assets/ssm_background.jpg");
    background-attachment: fixed;
    background-position: center;
    background-size: cover;
    width: 100%;
    height: 950px;
    position: relative;
}
.header{
    position: absolute;
    top: 50%;
    width: 100%;
    color: white;
    font-size: 30px;
    text-align: center;
    margin-top: -230px;
}
.login-form{
    position:absolute;
    width: 300px;
    height: 160px;
    background: #C0C4CC;
    left: 50%;
    top: 50%;
    margin-left: -190px;
    margin-top: -150px;
    padding: 40px;
    border-radius: 5px;
}
.login-btn{
    width: 100%;
}
</style>