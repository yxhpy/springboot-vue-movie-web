<template>
  <div>
    <el-dialog
        title="电影增加"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-form :model="addMovieForm" ref="addMovieForm" :rules="rules" label-position="right" label-width="100px">
        <el-form-item label="电影" prop="title">
          <el-input v-model="addMovieForm.title"></el-input>
        </el-form-item>
        <el-form-item label="观影次数" prop="view_num">
          <el-input type="number" v-model="addMovieForm.viewNum"></el-input>
        </el-form-item>
        <el-form-item label="清晰度" prop="definition">
          <el-select v-model="addMovieForm.definition" placeholder="请选择清晰度">
            <el-option label="标清" :value=1></el-option>
            <el-option label="高清" :value=2></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时长" prop="duration">
          <el-input v-model="addMovieForm.duration"></el-input>
        </el-form-item>
        <el-form-item label="发布时间" prop="release_movie">
          <el-input v-model="addMovieForm.releaseMovie"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addMovie">增 加</el-button>
  </span>
    </el-dialog>
    <el-dialog
        title="电影修改"
        :visible.sync="dialogVisible1"
        width="30%"
        :before-close="handleClose">
      <el-form :model="updateMovieForm" ref="addMovieForm" :rules="rules" label-position="right" label-width="100px">
        <el-form-item label="电影id" prop="mid">
          <el-input v-model="updateMovieForm.mid" disabled></el-input>
        </el-form-item>
        <el-form-item label="电影" prop="title">
          <el-input v-model="updateMovieForm.title"></el-input>
        </el-form-item>
        <el-form-item label="观影次数" prop="view_num">
          <el-input type="number" v-model="updateMovieForm.viewNum"></el-input>
        </el-form-item>
        <el-form-item label="清晰度" prop="definition">
          <el-select v-model="updateMovieForm.definition" placeholder="请选择清晰度">
            <el-option label="标清" :value=1></el-option>
            <el-option label="高清" :value=2></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时长" prop="duration">
          <el-input v-model="updateMovieForm.duration"></el-input>
        </el-form-item>
        <el-form-item label="发布时间" prop="release_movie">
          <el-input v-model="updateMovieForm.releaseMovie"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible1 = false">取 消</el-button>
    <el-button type="primary" @click="updateMovie">更 新</el-button>
  </span>
    </el-dialog>

    <div class="movie-head">
      <div class="head-left">
        <template>模糊筛选</template>
        <el-input size="mini" class="input-search" v-model="selectWord"></el-input>
        <el-button class="add-button el-button--primary" @click="dialogVisible = true" size="mini">添加电影</el-button>
        <el-input size="mini" class="input-search" v-model="searchWord"></el-input>
        <el-button class="add-button el-button--primary" @click="clickSearchWord" size="mini">搜索电影</el-button>
      </div>
    </div>
    <el-table border :data="moviesData" style="width: 100%; text-align: center" height="500px">
      <el-table-column label="序号" prop="mid" width="50"></el-table-column>
      <el-table-column label="封面" prop="img" width="100">
        <template slot-scope="scope">
          <el-upload
              :on-preview="imgPreview"
              :before-upload="beforeUpload"
              :action="BASEURL + `movie/movie/img/post`"
              :show-file-list="false"
              :on-success="imgSuccess"
              :data="{id:scope.row.mid}">
            <img :src=scope.row.img alt="" class="movie-img">
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column label="电影名" prop="title"></el-table-column>
      <el-table-column label="演员" prop="actors">
        <template slot-scope="scope">
            <template v-for="(item, index) in scope.row.actors">
              <template v-if="scope.row.actors.length===index+1">
                {{item.aname}}
              </template>
              <template v-else>
                {{item.aname}},
              </template>
            </template>
        </template>
      </el-table-column>
      <el-table-column label="语言">
        <template slot-scope="scope">
          <template v-for="(item, index) in scope.row.languages">
            <template v-if="scope.row.languages.length===index+1">
              {{item.lname}}
            </template>
            <template v-else>
              {{item.lname}},
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="拍摄地点">
        <template slot-scope="scope">
          <template v-for="(item, index) in scope.row.locals">
            <template v-if="scope.row.locals.length===index+1">
              {{item.lname}}
            </template>
            <template v-else>
              {{item.lname}},
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="导演">
        <template slot-scope="scope">
          <template v-for="(item, index) in scope.row.directors">
            <template v-if="scope.row.directors.length===index+1">
              {{item.dname}}
            </template>
            <template v-else>
              {{item.dname}},
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          <template v-for="(item, index) in scope.row.mtypes">
            <template v-if="scope.row.mtypes.length===index+1">
              {{item.tname}}
            </template>
            <template v-else>
              {{item.tname}},
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="编剧">
        <template slot-scope="scope">
          <template v-for="(item, index) in scope.row.scriptwriters">
            <template v-if="scope.row.scriptwriters.length===index+1">
              {{item.sname}}
            </template>
            <template v-else>
              {{item.sname}},
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="观影次数" prop="viewNum">
        <template slot-scope="scope">
          <i class="el-icon-video-play"></i>
          <span style="margin-left: 10px">{{ scope.row.viewNum }}万</span>
        </template>
      </el-table-column>
      <el-table-column label="清晰度" prop="definition">
        <template slot-scope="scope">
          {{ scope.row.definition===1?`标清`:`高清` }}
        </template>
      </el-table-column>
      <el-table-column label="时长" prop="duration">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.duration }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" prop="releaseMovie">
      </el-table-column>
      <el-table-column label="操作" width="255">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeMovie(scope.row.mid)">删 除</el-button>
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="openUpdateMovie(scope.row)">修 改</el-button>
          <el-button type="primary" size="mini" icon="el-icon-document-add" @click="addPlaySourceMovie(scope.row.mid)">源</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageInfo.page"
          :page-sizes="[20, 40, 80]"
          :page-size="pageInfo.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageInfo.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {addMovie, addPlaySource, BASEURL, deleteMovie, getMovie, getSources, updateMovie} from "../plugins/api";

export default {
  name: "MovieIndex",
  data() {
    return {
      dialogVisible: false,
      dialogVisible1:false,
      dialogVisible2:false,
      BASEURL: BASEURL,
      addMovieForm: {
        title: "爱情十分",
        viewNum: 1.1,
        definition: 1,
        duration: "73分钟",
        releaseMovie: "2015-10-09"
      },
      updateMovieForm:{

      },
      pageInfo: {
        page: 1,
        size: 20,
        total:null,
      },
      rules: {},
      updateIndex:null,
      moviesData:[],
      selectWord:"",
      searchWord:"",
      _moviesData:[],
    }
  },
  computed: {

  },
  watch: {
    selectWord:function f() {
      this.moviesData = this._moviesData
      let temp = []
      for (let i = 0; i < this.moviesData.length; i++) {
        if (this.moviesData[i].title.includes(this.selectWord)) {
          temp.push(this.moviesData[i])
        }
      }
      this.moviesData = temp
    }
  },
  methods: {
    loadData(){
      let _this = this
      let data = new URLSearchParams();
      data.append("page",this.pageInfo.page)
      data.append("size",this.pageInfo.size)
      data.append("search",this.searchWord)
      getMovie(data.toString()).then(function (msg) {
        _this.pageInfo.total = msg.data.pages.total
        _this.moviesData = msg.data.pages.records
        for (let theMovie of _this.moviesData) {
          if (!theMovie.img.includes("http")) {
            theMovie.img = BASEURL + theMovie.img
          }
        }
        _this._moviesData = msg.data.pages.records
      })
      getSources().then(function (msg) {
        _this.addPlaySourceForm.sources = msg.data.data
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
    imgPreview(file){
      console.log(file)
    },
    imgSuccess(msg){
      if (msg.code === 100) {
        this.$notify.success({
          title: 'Message',
          message: msg.msg,
          showClose: false
        })
      }
      this.loadData()
    },
    beforeUpload(file){
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    handleSizeChange(val) {
      this.pageInfo.size = val
      this.loadData();
    },
    handleCurrentChange(val) {
      this.pageInfo.page = val
      this.loadData();
    },
    addMovie(){
      console.log(this.addMovieForm)
      let _this = this
      addMovie(this.addMovieForm).then(function (msg) {
          if (msg.code === 200){
            _this.$notify.error({
                title: 'error',
                message: msg.msg,
                showClose: false
              })
          } else {
            _this.$notify.success({
                title: 'success',
                message: msg.msg,
                showClose: false
              })
            _this.loadData()
            _this.dialogVisible = false
          }
      })
    },
    removeMovie(mid){
      let _this = this
      var params = new URLSearchParams();
      params.append("mid", mid)
      deleteMovie(params).then(function (msg) {
        if (msg.code === 200){
          _this.$notify.error({
            title: 'error',
            message: msg.msg,
            showClose: false
          })
        } else {
          _this.$notify.success({
            title: 'success',
            message: msg.msg,
            showClose: false
          })
          _this.dialogVisible1 = false
          _this.loadData()
        }
      })
    },
    openUpdateMovie(obj){
      this.dialogVisible1 = true;
      this.updateMovieForm = obj
    },
    updateMovie(){
      let _this = this;
      updateMovie(this.updateMovieForm).then(function (msg) {
        if (msg.code === 200){
          _this.$notify.error({
            title: 'error',
            message: msg.msg,
            showClose: false
          })
        } else {
          _this.$notify.success({
            title: 'success',
            message: msg.msg,
            showClose: false
          })
          _this.dialogVisible1 = false
          _this.loadData()
        }
      })
    },
    addPlaySourceMovie(mid){
      this.$router.push({name:'sources', query:{'mid':mid}})
    },
    clickSearchWord(){
      let _this = this
      let data = new URLSearchParams();
      data.append("page",this.pageInfo.page)
      data.append("size",this.pageInfo.size)
      data.append("search",this.searchWord)
      getMovie(data.toString()).then(function (msg) {
        _this.pageInfo.total = msg.data.pages.total
        _this.moviesData = msg.data.pages.records
        for (let theMovie of _this.moviesData) {
          if (!theMovie.img.includes("http")) {
            theMovie.img = BASEURL + theMovie.img
          }
        }
        _this._moviesData = msg.data.pages.records
      })
    }
  },
  created() {
    this.loadData()
  }
}
</script>

<style scoped>
.movie-head {
  height: 70px;
  line-height: 70px;
  background: #C0C4CC;
}

.add-button {
  margin-left: 20px;
}
.movie-img{
  width: 100%;
}
.input-search{
  margin-left: 20px;
  width: 100px;
}
.pagination{
  text-align: center;
}
.el-pagination{
  display: inline-block;
}
</style>