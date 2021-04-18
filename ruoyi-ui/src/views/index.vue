<template>
  <div class="app-container home">
    <div class="headContainer" style="height: 100vh;overflow-x: hidden;">
      <el-backtop target=".headContainer">
        <div class="headContainer-icon">
         <i class="el-icon-thumb"></i>
        </div>
      </el-backtop>
      <!--轮播图-->
      <el-row>
        <el-col :span="24"><div class="grid-content bg-purple-dark">
          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="homeUrl in homeUrls" :key="homeUrl">
              <el-image
                style="width: 80%; height: 200PX;border-radius:20px;"
                :src="homeUrl">
              </el-image>
            </el-carousel-item>
          </el-carousel>
        </div></el-col>
      </el-row>
      <!--通知公告-->
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>通知公告</span>
              </div>
              <el-collapse v-model="activeName" accordion>
                <el-collapse-item v-for="notice in noticeList" :title="notice.noticeTitle" :name="notice.noticeId">
                  <div v-html="notice.noticeContent"></div>
                </el-collapse-item>
              </el-collapse>
            </el-card>
          </div>
        </el-col>
      </el-row>
      <!--论文下载量排行统计-->
      <el-row>
        <el-card class="box-card1" style="width: 46%">
          <div slot="header" class="clearfix">
            <span>论文下载量排行统计</span>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </el-row>
    </div>
  </div>
</template>

<script>
import { listNotice} from "@/api/system/notice";
import echarts from "echarts";
export default {
  name: "index",
  data() {
    return {
      homeUrls:[
        process.env.VUE_APP_BASE_API+'/profile/home1.png',
        process.env.VUE_APP_BASE_API+'/profile/home2.jpg',
        process.env.VUE_APP_BASE_API+'/profile/home3.jpg',
        process.env.VUE_APP_BASE_API+'/profile/home4.jpg'
      ],
      noticeList:[],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      activeName:'1'
    };
  },
  created(){
    this.getList();
  },
  methods: {
    /** 查询公告列表 */
    getList() {
      listNotice(this.queryParams).then(response => {
        this.noticeList = response.rows;
        console.log(this.noticeList)
      });
    },
  },
};
</script>

<style scoped lang="scss">
  /*卡片样式*/
  .text {
    font-size: 14px;
  }
  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 92%;
  }
  /*跑马灯样式*/
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  /*布局样式*/
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
 /* .bg-purple-dark {
    background: #99a9bf;
  }*/
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .headContainer-icon{
    height: 100%;
    width: 100%;
    background-color: #f2f5f6;
    box-shadow: 0 0 6px rgba(0,0,0, .12);
    text-align: center;
    line-height: 40px;
    color: #1989fa;
  }
</style>

