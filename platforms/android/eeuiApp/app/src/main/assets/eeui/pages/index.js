// { "framework": "Vue"} 
if(typeof app=="undefined"){app=weex}
if(typeof eeuiLog=="undefined"){var eeuiLog={_:function(t,e){var s=e.map(function(e){return e="[object object]"===Object.prototype.toString.call(e).toLowerCase()?JSON.stringify(e):e});if(typeof this.__m==='undefined'){this.__m=app.requireModule('debug')}this.__m.addLog(t,s)},debug:function(){for(var e=[],t=arguments.length;t--;)e[t]=arguments[t];this._("debug",e)},log:function(){for(var e=[],t=arguments.length;t--;)e[t]=arguments[t];this._("log",e)},info:function(){for(var e=[],t=arguments.length;t--;)e[t]=arguments[t];this._("info",e)},warn:function(){for(var e=[],t=arguments.length;t--;)e[t]=arguments[t];this._("warn",e)},error:function(){for(var e=[],t=arguments.length;t--;)e[t]=arguments[t];this._("error",e)}}}
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/pages/index.vue?entry=true");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/pages/index.vue?entry=true":
/*!****************************************!*\
  !*** ./src/pages/index.vue?entry=true ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(/*! !C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/style-loader!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/style-rewriter?id=data-v-6be49aa4!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector?type=styles&index=0!./index.vue */ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\style-loader.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\style-rewriter.js?id=data-v-6be49aa4!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=styles&index=0!./src/pages/index.vue")
)

/* script */
__vue_exports__ = __webpack_require__(/*! !C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/script-loader!babel-loader!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector?type=script&index=0!./index.vue */ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\script-loader.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\node_modules\\_babel-loader@8.1.0@babel-loader\\lib\\index.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=script&index=0!./src/pages/index.vue")

/* template */
var __vue_template__ = __webpack_require__(/*! !C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/template-compiler?id=data-v-6be49aa4!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector?type=template&index=0!./index.vue */ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\template-compiler.js?id=data-v-6be49aa4!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=template&index=0!./src/pages/index.vue")
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {eeuiLog.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "E:\\study\\musicproject\\src\\pages\\index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-6be49aa4"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__
module.exports.el = 'true'
new Vue(module.exports)


/***/ }),

/***/ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\script-loader.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\node_modules\\_babel-loader@8.1.0@babel-loader\\lib\\index.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=script&index=0!./src/pages/index.vue":
/*!*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/script-loader.js!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/node_modules/_babel-loader@8.1.0@babel-loader/lib!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector.js?type=script&index=0!./src/pages/index.vue ***!
  \*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
var eeui = app.requireModule("eeui");
var audio = app.requireModule("eeuiAudio");

function debounce(fn, delay) {
  // 记录上一次的延时器
  var timer = null;
  var delay = delay || 200;
  return function () {
    var args = arguments;
    var that = this; // 清除上一次延时器

    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(that, args);
    }, delay);
  };
}

var playList = [{
  url: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/daiwozouss.m4a",
  songName: "带我走",
  singerName: "杨丞琳",
  image: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/daiwozou.jpg"
}, {
  url: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/qingchunzhuleshui.m4a",
  songName: "青春住了谁",
  singerName: "杨丞琳",
  image: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/qingchunzhuleshui.jpeg"
}, {
  url: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/lvxing.m4a",
  songName: "旅行",
  singerName: "许巍",
  image: "https://eeui.oss-cn-beijing.aliyuncs.com/editor/resources/lvxing.jpg"
}];
/* harmony default export */ __webpack_exports__["default"] = ({
  data: function data() {
    return {
      title: "Hello, World!",
      desc: "Hello, World!  Hello, EEUI! ",
      playList: playList,
      playIndex: 0
    };
  },
  mounted: function mounted() {
    var _this = this;

    audio.setCallback(function (res) {
      if (res.status === "next_song") {
        _this.nextSong();
      }

      if (res.status === "last_song") {
        _this.lastSong();
      }

      if (res.status === "compelete") {
        _this.nextSong();
      }

      if (res.status === "error") {
        eeui.toast("播放错误，自动播放下一曲");
      }
    });
    this.playMusic(); //页面挂载
  },
  methods: {
    /**
     * 生命周期
     * @param res
     */
    lifecycle: function lifecycle(res) {
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
    goForward: function goForward(jsPageName, params) {
      eeui.openPage({
        url: str + ".js",
        pageType: "app",
        statusBarColor: "#3EB4FF",
        params: params ? params : {}
      });
    },
    playMusic: debounce(function () {
      var data = this.playList[this.playIndex];
      var params = {
        url: data.url
      };
      audio.play(data); //
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
    goBack: function goBack() {
      eeui.closePage();
    }
  }
});

/***/ }),

/***/ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\style-loader.js!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\style-rewriter.js?id=data-v-6be49aa4!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=styles&index=0!./src/pages/index.vue":
/*!*********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/style-loader.js!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/style-rewriter.js?id=data-v-6be49aa4!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector.js?type=styles&index=0!./src/pages/index.vue ***!
  \*********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "flex": 1
  },
  "nav": {
    "width": "750",
    "height": "96",
    "display": "flex",
    "backgroundColor": "#3eb4ff"
  },
  "nav-title": {
    "flex": 1,
    "color": "#ffffff",
    "textAlign": "center",
    "lineHeight": "96",
    "fontSize": "32",
    "fontWeight": "300"
  },
  "app-desc": {
    "paddingTop": "24",
    "paddingRight": "24",
    "paddingBottom": "24",
    "paddingLeft": "24",
    "fontSize": "36"
  }
}

/***/ }),

/***/ "C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\template-compiler.js?id=data-v-6be49aa4!C:\\Users\\18589\\AppData\\Roaming\\npm\\node_modules\\eeui-cli\\lib\\loaders\\eeui-loader\\lib\\selector.js?type=template&index=0!./src/pages/index.vue":
/*!*********************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/template-compiler.js?id=data-v-6be49aa4!C:/Users/18589/AppData/Roaming/npm/node_modules/eeui-cli/lib/loaders/eeui-loader/lib/selector.js?type=template&index=0!./src/pages/index.vue ***!
  \*********************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["app"],
    on: {
      "lifecycle": _vm.lifecycle
    }
  }, [_c('div', {
    staticClass: ["nav"]
  }, [_c('text', {
    staticClass: ["nav-title"]
  }, [_vm._v(_vm._s(_vm.title))])]), _c('scroller', {
    staticClass: ["app"]
  }, [_c('text', {
    staticClass: ["app-desc"]
  }, [_vm._v(_vm._s(_vm.desc))])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });