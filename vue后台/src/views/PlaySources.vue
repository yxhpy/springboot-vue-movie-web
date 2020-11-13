<template>
  <div>
    <el-dialog
        :title="`增加电影源`"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-form :model="addPlaySourceForm" ref="addPlaySourceForm" label-position="right" label-width="100px">
        <el-form-item label="电影id" prop="mid">
          <el-input v-model="addPlaySourceForm.movie" disabled></el-input>
        </el-form-item>
        <el-form-item label="电影源商" prop="sname">
          <el-select v-model="addPlaySourceForm.sname" disabled>
            <template v-for="item in addPlaySourceForm.sources">
              <el-option :label="item.name" :value=item.snid>
              </el-option>
            </template>
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="definition">
          <el-input v-model="addPlaySourceForm.title"></el-input>
        </el-form-item>
        <el-form-item label="URL" prop="definition">
          <el-input v-model="addPlaySourceForm.playUrl"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addPlaySource">增 加</el-button>
  </span>
    </el-dialog>
<!--    <el-input type="textarea" :value="JSON.stringify(movie)" readonly autosize></el-input>-->
    <ul class="play-source-nav">
      <li><i class="el-icon-video-play" style="color: red;font-weight: 1000;font-size: 20px"> {{movie.movie.title}}</i></li>
      <li><i class="el-icon-s-data">源</i></li>
      <li><el-input size="mini" style="width: 90px;" v-model="source"></el-input></li>
      <li> <el-button size="mini" type="primary" icon="el-icon-document-add" @click="addSource">添加源</el-button></li>
    </ul>
    <div>
      <div v-for="item0 in sources" class="sources-item">
        <span class="sources-name">
          <i style="width: 60px; display: inline-block">{{item0.name}}</i>
          <el-button size="mini" type="primary" @click="dialogVisible=true; addPlaySourceForm.sname=item0.snid">增加</el-button>
          <el-button size="mini" type="danger" @click="removeSourceGroup(item0.snid)">删除本源</el-button>
        </span>
        <el-table :data="(item0.name in playSource)?playSource[item0.name]:[]">
          <el-table-column label="ID" prop="pid"></el-table-column>
          <el-table-column label="标题" prop="title"></el-table-column>
          <el-table-column label="播放地址" prop="play_url"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" icon="el-icon-edit">修 改</el-button>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="removeSource(scope.row.pid)">删 除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import {
  addPlaySource,
  addSource,
  getMovieById,
  getPlaySource,
  getSources,
  removePlaySource,
  removeSource
} from "../plugins/api";

export default {
  name: "PlaySources",
  data(){
    return {
      movie:null,
      sources:null,
      playSource:null,
      source:"",
      addPlaySourceForm: {
        sname:1,
        movie:0,
        title:'',
        playUrl:'',
        sources:[],
      },
      dialogVisible:false,
    }
  },
  methods:{
    loadData() {
      this.addPlaySourceForm  =  {
        sname:1,
        movie:0,
        title:'',
        playUrl:'',
        sources:[],
      }
      let _this = this
      let mid = this.$route.query.mid
      _this.addPlaySourceForm.movie = mid
      getMovieById(mid).then(function (msg) {
        _this.movie = msg.data
      })
      getSources().then(function (msg) {
        _this.sources = msg.data.data
        _this.addPlaySourceForm.sources = msg.data.data
      })
      getPlaySource("mid="+mid).then(function (msg) {
        _this.playSource = msg.data.data
      })
    },
    addPlaySource(){
      var _this = this
      addPlaySource(this.addPlaySourceForm).then(function (msg) {
        if (msg.code === 100){
          _this.dialogVisible = false
          _this.$notify({
            title: '成功',
            message: msg.msg,
            type: 'success'
          });
          _this.loadData()
        } else {
          _this.$notify({
            title: '失败',
            message: msg.msg,
            type: 'error'
          });
        }
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    removeSource(pid){
      if (!confirm("确认删除？")) {
        return
      }
      let _this = this
      removePlaySource("pid="+pid).then(function (msg) {
        if (msg.code === 100){
          _this.dialogVisible = false
          _this.$notify({
            title: '成功',
            message: msg.msg,
            type: 'success'
          });
          _this.loadData()
        } else {
          _this.$notify({
            title: '失败',
            message: msg.msg,
            type: 'error'
          });
        }
      })
    },
    addSource(){
      let _this = this
      addSource("name="+this.source).then(function (msg) {
        if (msg.code === 100){
          _this.$notify({
            title: '成功',
            message: msg.msg,
            type: 'success'
          });
          _this.loadData()
        } else {
          _this.$notify({
            title: '失败',
            message: msg.msg,
            type: 'error'
          });
        }
      })
    },
    removeSourceGroup(snid){
      let _this = this
      removeSource("snid="+snid).then(function (msg) {
        if (msg.code === 100){
          _this.$notify({
            title: '成功',
            message: msg.msg,
            type: 'success'
          });
          _this.loadData()
        } else {
          _this.$notify({
            title: '失败',
            message: msg.msg,
            type: 'error'
          });
        }
      })
    }
  },
  mounted() {
    this.loadData()
  }
}
</script>

<style scoped>
.sources-title{
  padding: 20px;
  font-size: 20px;
  height: 50px;
  background: #DCDFE6;
  color: #606266;
}
.sources-name{
  color: #42b983;
  font-size: 5px;
  padding: 10px;
}
.sources-item{
  margin: 20px 0;
}
.play-source-nav{
  list-style: none;
  overflow: hidden;
  margin: 10px;
}
.play-source-nav li{
  float: left;
  margin: 0 10px;
}
</style>