webpackJsonp([3],{"0zsQ":function(t,e,n){var a=n("opmd");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("rjj0")("3505db7a",a,!0,{})},"4eMf":function(t,e,n){(t.exports=n("FZ+f")(!1)).push([t.i,".dropdown-menu{height:50px;position:relative;display:inline-block;z-index:1}.dropdown-link{height:50px;line-height:50px;padding:0 20px;color:#fff;font-size:16px;font-weight:400;cursor:pointer;overflow:hidden}.dropdown-link,.dropdown-link i{-webkit-transition:all .3s ease;transition:all .3s ease}.dropdown-link i{font-size:14px}.dropdown-list{position:absolute;top:50px;min-width:120px;background:#fff;box-shadow:0 5px 5px 0 rgba(0,0,0,.1);z-index:1}.dropdown-menu.open .dropdown-link{background:#fff;color:#333}.dropdown-menu.open .dropdown-link i{-webkit-transform:rotate(90deg);transform:rotate(90deg)}",""])},"6GmT":function(t,e,n){var a=n("a2k5");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("rjj0")("74df590a",a,!0,{})},Cl3J:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("Dd8w"),i=n.n(a),r=n("NYxO");n("mvHQ"),n("pFYg");var s=n("gyMJ"),o=n("Xxa5"),l=n.n(o),p=n("exGp"),c=n.n(p),u=n("OlfU"),d={name:"app-dropdown-menu",props:{placement:{type:String,default:"left"},timeLen:{type:Number,default:300}},data:function(){return{isOpen:!1,timer:0}},methods:{hoverHandle:function(){var t=this;this.timer=setTimeout(function(){return t.isOpen=!0},this.timeLen)},outHandle:function(){clearTimeout(this.timer),this.isOpen=!1}}},f={render:function(){var t,e=this,n=e.$createElement,a=e._self._c||n;return a("div",{staticClass:"dropdown-menu",class:{open:e.isOpen},on:{mouseenter:function(t){return t.stopPropagation(),e.hoverHandle(t)},mouseleave:function(t){return t.stopPropagation(),e.outHandle(t)}}},[a("h4",{staticClass:"dropdown-link"},[e._t("title"),e._v(" "),a("i",{staticClass:"el-icon-arrow-right"})],2),e._v(" "),a("el-collapse-transition",[a("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],staticClass:"dropdown-list",style:(t={},t[e.placement]=0,t)},[e._t("list")],2)])],1)},staticRenderFns:[]};var h=n("VU/8")(d,f,!1,function(t){n("u8F2")},null,null).exports,v={name:"app-header",data:function(){return{username:""}},computed:i()({},Object(r.c)("app",["loginInfo"])),methods:i()({},Object(r.b)("app",["createLogout"]),{logout:function(){var t=this;return c()(l.a.mark(function e(){var n;return l.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(s.r)();case 2:1==(n=e.sent).code?(t.createLogout(),t.$router.push("/login")):t.$message.error(n.message);case 4:case"end":return e.stop()}},e,t)}))()},toPersonal:function(){this.$router.push("/setting/personal")}}),created:function(){this.username=this.loginInfo.name||Object(u.a)()},components:{AppDropdownMenu:h}},g={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-header",{staticClass:"app-header clearFix"},[n("div",{staticClass:"app-head-logo fl"},[n("router-link",{attrs:{to:"/"}},[t._v("HRMS管理系统")])],1),t._v(" "),n("div",{staticClass:"app-top-nav fl"},[t._t("topnav")],2),t._v(" "),n("div",{staticClass:"user-setting fr"},[n("app-dropdown-menu",{attrs:{placement:"right"}},[n("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(t.username))]),t._v(" "),n("div",{staticClass:"user-setting-list",attrs:{slot:"list"},slot:"list"},[n("ul",[n("li",{on:{click:function(e){return e.stopPropagation(),t.toPersonal(e)}}},[t._v("\r\n                        个人设置\r\n                    ")]),t._v(" "),n("li",{on:{click:function(e){return e.stopPropagation(),t.logout(e)}}},[t._v("\r\n                        安全退出\r\n                    ")])])])])],1)])},staticRenderFns:[]};var x=n("VU/8")(v,g,!1,function(t){n("bHyU")},null,null).exports,m={name:"app-head-nav",props:{navList:{type:Array,default:[]},curIndex:{type:Number,default:0}}},b={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("nav",{staticClass:"head-nav"},[n("ul",{staticClass:"clearFix"},t._l(t.navList,function(e,a){return n("li",{key:a,class:{active:a==t.curIndex}},[n("router-link",{attrs:{to:e.link}},[n("i",{staticClass:"icon",class:"graph-"+e.iconName}),t._v(" "),n("span",[t._v(t._s(e.title))])])],1)}))])},staticRenderFns:[]};var k=n("VU/8")(m,b,!1,function(t){n("6GmT")},null,null).exports,w={name:"app-sidebar",props:{list:Array,active:String,openeds:String},data:function(){return{isCollapse:!1}}},y={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-sidebar"},[n("el-menu",{staticClass:"sidebar-menu",attrs:{router:"","default-active":t.active,"default-openeds":[t.openeds],"background-color":"#001529","text-color":"#fff","active-text-color":"#ffd04b",collapse:t.isCollapse}},t._l(t.list,function(e,a){return n("el-submenu",{key:a,attrs:{index:e.depth}},[n("template",{slot:"title"},[n("i",{class:"el-icon-"+e.iconName}),t._v(" "),n("span",[t._v(t._s(e.title))])]),t._v(" "),t._l(e.list,function(e,a){return n("el-menu-item",{key:a,attrs:{index:e.link}},[t._v("\r\n                "+t._s(e.title)+"\r\n            ")])})],2)})),t._v(" "),n("div",{staticClass:"sidebar-ctrl-btn",class:{off:t.isCollapse},on:{click:function(e){e.stopPropagation(),t.isCollapse=!t.isCollapse}}},[n("i",{staticClass:"el-icon-arrow-left"})])],1)},staticRenderFns:[]};var C=n("VU/8")(w,y,!1,function(t){n("0zsQ")},null,null).exports,L={name:"Layout",data:function(){return{curIndex:0,parentDepth:"",sidebarList:[],sidebarActive:"",sidebarOpeneds:""}},computed:i()({},Object(r.c)("app",["navList"]),Object(r.c)("dict",["planTheadList"]),{key:function(){return void 0!==this.$route.name?this.$route.name+ +new Date:"view-"+ +new Date}}),watch:{$route:function(t,e){this.navList.length>0&&t.path.split("/").length<3&&this.setNavInfo()}},created:function(){this.iterFunc(this.navList,""),this.setNavInfo(),this.createPlanTheadList()},methods:i()({},Object(r.b)("dict",["createPlanTheadList"]),{setNavInfo:function(){var t=window.location.hash.slice(1)||"";this.curIndex=this.navList.findIndex(function(e){return e.link==="/"+t.split("/")[1]}),-1===this.curIndex&&(this.curIndex=0),this.sidebarList=_.cloneDeep(this.navList[this.curIndex].list),this.findParDepth(this.navList,t),this.sidebarActive=t,this.sidebarOpeneds=this.parentDepth},iterFunc:function(t,e){for(var n=0;n<t.length;n++)t[n].depth=e+n,_.isArray(t[n].list)&&this.iterFunc(t[n].list,t[n].depth+"-")},findParDepth:function(t,e){for(var n=0;n<t.length;n++)t[n].link===e?this.parentDepth=t[n].depth.slice(0,-2):_.isArray(t[n].list)&&this.findParDepth(t[n].list,e)}}),components:{AppHeader:x,AppHeadNav:k,AppSidebar:C}},O={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-container",{staticClass:"is-vertical my-app"},[n("app-header",{staticStyle:{height:"50px"}},[n("app-head-nav",{attrs:{slot:"topnav",navList:t.navList,curIndex:t.curIndex},slot:"topnav"})],1),t._v(" "),n("el-container",[n("app-sidebar",{attrs:{list:t.sidebarList,active:t.sidebarActive,openeds:t.sidebarOpeneds}}),t._v(" "),n("el-main",[n("transition",{attrs:{name:"router"}},[n("router-view",{key:t.key,staticClass:"view-wrapper"})],1)],1)],1)],1)},staticRenderFns:[]};var j=n("VU/8")(L,O,!1,function(t){n("HwAu")},null,null);e.default=j.exports},EgyO:function(t,e,n){(t.exports=n("FZ+f")(!1)).push([t.i,".app-header{height:50px;line-height:50px;padding:0 10px 0 20px;background-color:#1890ff}.app-head-logo{width:180px;color:#fff;font-size:16px}.app-head-logo a{color:#fff}.user-setting{height:50px}.user-setting-list{padding:6px 0}.user-setting-list ul li{line-height:32px;padding:0 15px;cursor:pointer}.user-setting-list ul li:hover{background:#409eff;color:#fff}",""])},HwAu:function(t,e,n){var a=n("qjS8");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("rjj0")("12f2a485",a,!0,{})},Ny0x:function(t,e,n){t.exports=n.p+"static/img/icons.fe5b175.png"},a2k5:function(t,e,n){var a=n("kxFB");(t.exports=n("FZ+f")(!1)).push([t.i,".head-nav ul li{float:left}.head-nav ul li a{display:block;padding:0 20px;height:50px;line-height:50px;-webkit-transition:all .3s ease;transition:all .3s ease}.head-nav ul li.active a,.head-nav ul li a:hover{background-color:rgba(0,0,0,.2)}.icon{background-image:url("+a(n("Ny0x"))+");background-repeat:no-repeat;display:inline-block;vertical-align:middle}.head-nav ul li a i.icon{height:16px;width:16px;margin-top:-3px}.head-nav ul li a span{margin-left:6px;font-size:16px;color:#fff}.head-nav ul li a i.graph-sys_setting{background-position:0 0}.head-nav ul li a i.graph-performance{background-position:-144px 0}.head-nav ul li a i.graph-train{background-position:-16px 0}.head-nav ul li a i.graph-assessment{background-position:-128px 0}.head-nav ul li a i.graph-notice{background-position:-16px -32px}.head-nav ul li a i.graph-scheduling{background-position:-112px 0}",""])},bHyU:function(t,e,n){var a=n("EgyO");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("rjj0")("e1596f58",a,!0,{})},kxFB:function(t,e){t.exports=function(t){return"string"!=typeof t?t:(/^['"].*['"]$/.test(t)&&(t=t.slice(1,-1)),/["'() \t\n]/.test(t)?'"'+t.replace(/"/g,'\\"').replace(/\n/g,"\\n")+'"':t)}},opmd:function(t,e,n){(t.exports=n("FZ+f")(!1)).push([t.i,".app-sidebar{height:100%;background-color:#001529;position:relative}.app-sidebar .sidebar-menu:not(.el-menu--collapse){width:200px}.app-sidebar .sidebar-menu{border-right:none}.app-sidebar .sidebar-ctrl-btn{background-color:#002140;height:40px;line-height:40px;text-align:center;font-size:18px;color:#fff;position:absolute;left:0;right:0;bottom:0;cursor:pointer;z-index:9}.app-sidebar .sidebar-ctrl-btn.off>i{-webkit-transform:rotate(180deg);transform:rotate(180deg)}",""])},qjS8:function(t,e,n){(t.exports=n("FZ+f")(!1)).push([t.i,".my-app>.el-container{height:calc(100vh - 50px);display:flex}.my-app .el-main{position:relative;background-color:#f2f2f2;flex:1;padding:10px}.my-app .view-wrapper{position:absolute;left:10px;right:10px;background-color:#fff}#app .router-enter{opacity:0;-webkit-transform:translate3d(0,25px,0);transform:translate3d(0,25px,0)}#app .router-enter-to{opacity:1;-webkit-transform:translateZ(0);transform:translateZ(0)}#app .router-leave{opacity:1}#app .router-leave-to{opacity:0}#app .router-enter-to,#app .router-leave{opacity:1;-webkit-transform:translateZ(0);transform:translateZ(0)}#app .router-enter-active,#app .router-leave-active{-webkit-transition:all .5s ease-out;transition:all .5s ease-out}",""])},u8F2:function(t,e,n){var a=n("4eMf");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);n("rjj0")("78a71c03",a,!0,{})}});
//# sourceMappingURL=3.d88e8233249c52d3d58a.js.map