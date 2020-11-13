<template>
  <div id="app">
      <nav-bar></nav-bar>
      <div class="main">
        <router-view></router-view>
      </div>
      <nav-foot></nav-foot>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import NavFoot from "@/components/NavFoot";
export default {
  name: 'app',
  components: {
    NavFoot,
    NavBar,
  }
}
</script>

<style>
.main{
  width: 1500px;
  margin-left: auto;
  margin-right: auto;
}
</style>
