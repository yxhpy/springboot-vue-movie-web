import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {name:'index', path:'/', component:()=>import("../views/Index"), meta:{title:"yxhpy的电影首页"}},
  {name:'class', path:'/class', component:()=>import("../views/Classifcation"), meta:{title:"电影分类"}},
  {name:'play', path:'/play', component:()=>import("../views/Play"), meta:{title:"正在播放"}},
  {name:'detail', path:'/detail', component:()=>import("../views/Details"), meta:{title:"的详情"}},
  {name:'search', path:'/search', component:()=>import("../views/Search"), meta:{title:"搜索电影"}},
]

const router = new VueRouter({
  routes
})

export default router
