<template>
  <div class="app" @lifecycle="lifecycle">
    <!-- 顶部导航 -->
    <div class="nav">
      <text class="nav-title">{{ title }}</text>
    </div>

    <!-- 页面内容 -->
    <scroller class="app">
      <text class="app-desc">{{ desc }}</text>
    </scroller>
  </div>
</template>

<style scoped>
.app {
  flex: 1;
}
.nav {
  width: 750px;
  height: 96px;
  display: flex;
  background-color: #3eb4ff;
}
.nav-title {
  flex: 1;
  color: #ffffff;
  text-align: center;
  line-height: 96px;
  font-size: 32px;
  font-weight: 300;
}
.app-desc {
  padding: 24px;
  font-size: 36px;
}
</style>
<script>
const eeui = app.requireModule("eeui");
const audio = app.requireModule("eeui/audio");
function debounce(fn, delay) {
  // 记录上一次的延时器
  var timer = null;
  var delay = delay || 200;
  return function () {
    var args = arguments;
    var that = this;
    // 清除上一次延时器
    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(that, args);
    }, delay);
  };
}
const playList = [
  {
    url:
      "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/daiwozouss.m4a",
    songName: "带我走",
    singerName: "杨丞琳",
    image:
      "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/daiwozou.jpg",
  },
  {
    url:
      "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/qingchunzhuleshui.m4a",
    songName: "青春住了谁",
    singerName: "杨丞琳",
    image:
      "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/qingchunzhuleshui.jpeg",
  },
  {
    url: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/lvxing.m4a",
    songName: "旅行",
    singerName: "许巍",
    image:
      "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/lvxing.jpg",
  },
];

export default {
  data() {
    return {
      title: "Hello, World!",
      desc: "Hello, World!  Hello, EEUI! ",
      playList,
      playIndex: 0,
    };
  },

  mounted() {
    audio.setCallback((res) => {
      if (res.status === "next_song") {
        this.nextSong();
      }
      if (res.status === "last_song") {
        this.lastSong();
      }
      if (res.status === "compelete") {
        this.nextSong();
      }
      if (res.status === "error") {
        eeui.toast("播放错误，自动播放下一曲");
      }
    });
    this.playMusic();
    //页面挂载
  },

  methods: {
    /**
     * 生命周期
     * @param res
     */
    lifecycle(res) {
      switch (res.status) {
        case "ready":
          //页面挂载(初始化)
          break;

        case "resume":
          //页面激活(恢复)
          break;

        case "pause":
          //页面失活(暂停)
          break;
      }
    },

    /**
     * 打开新页面
     * @param jsPageName    (String)JS页面名称
     * @param params        (Object)传递参数
     */
    goForward(jsPageName, params) {
      eeui.openPage({
        url: str + ".js",
        pageType: "app",
        statusBarColor: "#3EB4FF",
        params: params ? params : {},
      });
    },
    playMusic: debounce(function () {
      const data = this.playList[this.playIndex];
      const params = {
        url: data.url,
      };
      audio.play(data);
      //
    }, 500),
    toast: debounce(function (message) {
      eeui.toast(message);
    }, 500),
    nextSong: debounce(function () {
      if (this.playIndex >= this.playList.length - 1) {
        this.playIndex = 0;
      } else {
        this.playIndex++;
      }
      this.playMusic();
    }, 500),
    lastSong: debounce(function () {
      if (this.playIndex <= 0) {
        this.playIndex = this.playList.length - 1;
      } else {
        this.playIndex--;
      }
      this.playMusic();
    }),
    /**
     * 返回上一页(关闭当前页)
     */
    goBack() {
      eeui.closePage();
    },
  },
};
</script>
