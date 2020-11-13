<template>
  <div class="ssm-main">
    <el-container>
      <nav-aside></nav-aside>
      <nav-header></nav-header>
      <div class="ssm-context" :class="{'ssm-context-collapse':collapse}">
        <router-view/>
      </div>
    </el-container>



  </div>
</template>

<script>
import NavAside from "../components/NavAside";
import NavHeader from "../components/NavHeader";

export default {
  name: "SsmMain",
  components:{
    NavHeader,
    NavAside
  },
  computed:{
     collapse(){
       return this.$store.state.navAsideCollapse;
     }
  }
}
</script>

<style scoped>

</style>