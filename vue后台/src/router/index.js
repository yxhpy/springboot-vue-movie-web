import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name: 'login',
    component: () => import("../views/Login")
  },{
    path:"/ssm",
    name: 'ssm',
    component: () => import("../views/SsmMain"),
    children:[
      {path:"/movie", name:"movie", component:()=>import("../views/Movie")},
      {path:"/index", name:"index", component:()=>import("../views/Index")},
      {path:"/sources", name:"sources", component:()=>import("../views/PlaySources")}
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
