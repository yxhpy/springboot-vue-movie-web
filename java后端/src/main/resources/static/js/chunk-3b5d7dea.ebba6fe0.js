(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3b5d7dea"],{"00a6":function(t,a,e){"use strict";e.r(a);var i=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("div",{staticClass:"class-title"},[t._v(" 选择你最喜欢的电影类型 ")]),e("div",{staticClass:"mtype"},[e("span",[t._v("类型：")]),t._l(t.mtypes,(function(a){return e("span",{staticClass:"type-item",on:{click:function(e){return t.filterType(a.tid)}}},[t._v(" "+t._s(a.tname)+" ")])}))],2),e("div",{staticClass:"movie-list"},[e("el-row",{attrs:{gutter:12}},t._l(t.movieList,(function(a){return e("el-col",{attrs:{span:4}},[e("router-link",{attrs:{to:{name:"detail",query:{mid:a.mid}}}},[e("el-card",{staticClass:"card-item",attrs:{shadow:"hover","body-style":{padding:"0px"}}},[e("div",{staticClass:"img-group"},[e("el-image",{attrs:{src:a.img}}),e("i",{staticClass:"el-icon-video-play"},[e("span",[t._v(" 播放次数"+t._s(a.viewNum)+"万")])])],1),e("span",{staticClass:"card-item-title"},[t._v(" "+t._s(a.title.length<=10?a.title:a.title.substring(0,10)+"...")+" ")])])],1)],1)})),1)],1),e("div",{staticClass:"pagination"},[e("el-pagination",{attrs:{"page-size":t.PageData.size,layout:"prev, pager, next, jumper",total:t.PageData.total},on:{"current-change":t.handleCurrentChange}})],1)])},s=[],n=(e("d3b7"),e("3ca3"),e("ddb0"),e("2b3d"),e("1a72")),r={name:"Classifcation",data:function(){return{mtypes:[],movieList:[],PageData:{page:1,size:36,tid:"",total:1e3}}},computed:{},methods:{getTypes:function(){var t=this;Object(n["d"])().then((function(a){t.mtypes=a.data.data}))},getPageData:function(){var t=new URLSearchParams;t.set("page",this.PageData.page),t.set("size",this.PageData.size),t.set("tid",this.PageData.tid);var a=this;Object(n["b"])(t).then((function(t){a.movieList=t.data.pages.records,a.PageData.total=t.data.pages.total}))},filterType:function(t){this.PageData.tid=t,this.PageData.page=1,this.getPageData()},handleCurrentChange:function(t){this.PageData.page=t,this.getPageData()}},created:function(){this.getTypes(),this.getPageData()}},c=r,l=(e("659b"),e("2877")),o=Object(l["a"])(c,i,s,!1,null,"9fa2792e",null);a["default"]=o.exports},"659b":function(t,a,e){"use strict";e("f9f2")},f9f2:function(t,a,e){}}]);
//# sourceMappingURL=chunk-3b5d7dea.ebba6fe0.js.map