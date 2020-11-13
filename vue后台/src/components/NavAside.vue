<template>
  <div class="nav-aside">
    <el-menu router default-active="/index" :collapse="collapse" class="el-menu-vertical-demo">
      <template v-for="item in manage">
        <el-menu-item :index="item.router">
          <i :class="item.icon"></i>
          <template slot="title">
            <span>{{ item.name }}</span>
          </template>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavAside",
  data() {
    return {
      manage: [
        {name: "管理主页",
          router:"/index",
          icon:"el-icon-s-management"
        },
        {name: "电影管理",
          router:"/movie",
          icon:"el-icon-video-play"
        }
      ]
    }
  },
  computed:{
    collapse() {
      return this.$store.state.navAsideCollapse;
    }
  }
}
</script>

<style scoped>
.nav-aside {
  position: absolute;
  left: 0;
  top: 60px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

</style>