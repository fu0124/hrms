webpackJsonp([7],{"0Euj":function(e,t,r){(e.exports=r("FZ+f")(!1)).push([e.i,"",""])},"77TK":function(e,t,r){(e.exports=r("FZ+f")(!1)).push([e.i,"",""])},Avze:function(e,t,r){var a=r("tmqq");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r("rjj0")("31aeb2c7",a,!0,{})},GOSz:function(e,t,r){(e.exports=r("FZ+f")(!1)).push([e.i,"",""])},X5ZL:function(e,t,r){var a=r("GOSz");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r("rjj0")("9f2d9bf2",a,!0,{})},hcJo:function(e,t,r){var a=r("77TK");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r("rjj0")("2674a793",a,!0,{})},nEXL:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("Xxa5"),n=r.n(a),s=r("exGp"),o=r.n(s),i=r("Dd8w"),c=r.n(i),l=r("gyMJ"),u=r("NYxO"),d=r("ecGJ"),m={name:"app-notice-out",props:["recordId"],data:function(){return{form:{work_date:"",type:"",from_store:"",remark:"",desc:""},rules:{work_date:[{required:!0,message:"请选择上岗日期",trigger:"change"}],type:[{required:!0,message:"请选择类别",trigger:"change"}],from_store:[{required:!0,message:"请选择来源门店",trigger:"change"}],desc:[{required:!0,message:"请输入说明",trigger:"blur"}]}}},computed:c()({},Object(u.c)("stateChange",["btnLoading"]),Object(u.c)("dict",["deptList","transferInList"])),methods:c()({},Object(u.b)("dict",["createDeptList","createTransferInList"]),{getFormInfo:function(e,t){var r=this;return o()(n.a.mark(function a(){var s;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,e();case 2:1==(s=a.sent).code?r[t]=s.data||[]:r.$message.error(s.message);case 4:case"end":return a.stop()}},a,r)}))()},saveRecord:function(e){var t=this;return o()(n.a.mark(function r(){var a;return n.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,Object(l.o)(c()({status:e},t.form));case 2:1==(a=r.sent).code?t.closePanle():t.$message.error(a.message);case 4:case"end":return r.stop()}},r,t)}))()},submitForm:function(e,t){var r=this;this.$refs[e].validate(function(e){e?"refuse"===t?r.$confirm("确认拒绝此次调出吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(o()(n.a.mark(function e(){return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(""!==r.form.desc){e.next=2;break}return e.abrupt("return",r.$message.warning("拒绝原因不能为空！"));case 2:r.saveRecord("0");case 3:case"end":return e.stop()}},e,r)}))).catch(function(){}):r.saveRecord("1"):console.log("error submit!")})},closePanle:function(){this.$emit("reloadEvent","reload")}}),created:function(){var e=this;this.createDeptList(),this.createTransferInList(),this.getFormInfo(o()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",Object(l.C)({id:e.recordId}));case 1:case"end":return t.stop()}},t,e)})),"form")}},p={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticStyle:{width:"70%"}},[r("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"100px",size:"small"}},[r("el-form-item",{attrs:{label:"上岗日期",prop:"work_date"}},[r("el-date-picker",{attrs:{type:"date",disabled:"",placeholder:"选择上岗日期",format:"yyyy 年 MM 月 dd 日","value-format":"yyyy-MM-dd"},model:{value:e.form.work_date,callback:function(t){e.$set(e.form,"work_date",t)},expression:"form.work_date"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"类别",prop:"type"}},[r("el-select",{attrs:{clearable:"",disabled:"",placeholder:"请选择类别"},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.transferInList,function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.value}})}))],1),e._v(" "),r("el-form-item",{attrs:{label:"调入门店",prop:"from_store"}},[r("el-select",{attrs:{clearable:"",disabled:"",placeholder:"请选择调入门店"},model:{value:e.form.from_store,callback:function(t){e.$set(e.form,"from_store",t)},expression:"form.from_store"}},e._l(e.deptList,function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.value}})}))],1),e._v(" "),r("el-form-item",{attrs:{label:"说明"}},[r("el-input",{attrs:{type:"textarea",disabled:"",rows:3,clearable:"",placeholder:"请输入说明..."},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"拒绝原因"}},[r("el-input",{attrs:{type:"textarea",rows:3,clearable:"",placeholder:"请输入拒绝原因..."},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","agree")}}},[e._v("同意")]),e._v(" "),r("el-button",{attrs:{type:"danger",plain:"",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","refuse")}}},[e._v("拒绝")])],1)],1)],1)},staticRenderFns:[]};var f=r("VU/8")(m,p,!1,function(e){r("hcJo")},null,null).exports,v={name:"app-notice-in",props:["recordId"],data:function(){return{dataInfo:{},form:{desc:""}}},computed:c()({},Object(u.c)("stateChange",["btnLoading"])),methods:{getFormInfo:function(e,t){var r=this;return o()(n.a.mark(function a(){var s;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,e();case 2:1==(s=a.sent).code?r[t]=s.data||[]:r.$message.error(s.message);case 4:case"end":return a.stop()}},a,r)}))()},saveRecord:function(e){var t=this;return o()(n.a.mark(function r(){var a;return n.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,Object(l.o)(c()({status:e},t.form,t.dataInfo));case 2:1==(a=r.sent).code?t.closePanle():t.$message.error(a.message);case 4:case"end":return r.stop()}},r,t)}))()},submitForm:function(e,t){var r=this;this.$refs[e].validate(function(e){e?"refuse"===t?r.$confirm("确认拒绝此次调入吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(o()(n.a.mark(function e(){return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(""!==r.form.desc){e.next=2;break}return e.abrupt("return",r.$message.warning("拒绝原因不能为空！"));case 2:r.saveRecord("0");case 3:case"end":return e.stop()}},e,r)}))).catch(function(){}):r.saveRecord("1"):console.log("error submit!")})},closePanle:function(){this.$emit("reloadEvent","reload")}},created:function(){var e=this;this.getFormInfo(o()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",Object(l.F)({id:e.recordId}));case 1:case"end":return t.stop()}},t,e)})),"dataInfo")}},b={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("div",{staticStyle:{"padding-bottom":"10px"}},[e._v("\r\n        来源门店："),r("span",{staticStyle:{"margin-right":"20px"}},[e._v(e._s(e.dataInfo.out_store_name))]),e._v("\r\n        调入日期："),r("span",{staticStyle:{"margin-right":"20px"}},[e._v(e._s(e.dataInfo.date))]),e._v("\r\n        类别："),r("span",[e._v(e._s(e.dataInfo.type_text))])]),e._v(" "),r("div",{staticClass:"component-main"},[r("el-table",{attrs:{data:e.dataInfo.staffList,stripe:"",border:"",size:"mini"}},[r("el-table-column",{attrs:{prop:"name",label:"姓名"}}),e._v(" "),r("el-table-column",{attrs:{prop:"gender",label:"性别"}}),e._v(" "),r("el-table-column",{attrs:{prop:"phone",label:"电话"}}),e._v(" "),r("el-table-column",{attrs:{prop:"job",label:"职位"}}),e._v(" "),r("el-table-column",{attrs:{prop:"kind",label:"岗位"}}),e._v(" "),r("el-table-column",{attrs:{prop:"money",label:"薪资"}}),e._v(" "),r("el-table-column",{attrs:{prop:"work_type",label:"工作类型"}})],1)],1),e._v(" "),r("div",{staticClass:"transfer-desc-box"},[r("p",[e._v("说明："+e._s(e.dataInfo.remark))])]),e._v(" "),r("el-form",{ref:"form",attrs:{model:e.form,"label-width":"70px",size:"small"}},[r("el-form-item",{attrs:{label:"拒绝原因"}},[r("el-input",{attrs:{type:"textarea",rows:3,clearable:"",placeholder:"请输入拒绝原因..."},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","agree")}}},[e._v("同意")]),e._v(" "),r("el-button",{attrs:{type:"danger",plain:"",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","refuse")}}},[e._v("拒绝")])],1)],1)],1)},staticRenderFns:[]};var h=r("VU/8")(v,b,!1,function(e){r("Avze")},null,null).exports,g={name:"app-notice-quit",props:["recordId"],data:function(){return{form:{name:"",date:"",user:"",remark:"",desc:""}}},computed:c()({},Object(u.c)("stateChange",["btnLoading"])),methods:{getFormInfo:function(e,t){var r=this;return o()(n.a.mark(function a(){var s;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,e();case 2:1==(s=a.sent).code?r[t]=s.data||[]:r.$message.error(s.message);case 4:case"end":return a.stop()}},a,r)}))()},saveRecord:function(e){var t=this;return o()(n.a.mark(function r(){var a;return n.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,Object(l.o)(c()({status:e},t.form));case 2:1==(a=r.sent).code?t.closePanle():t.$message.error(a.message);case 4:case"end":return r.stop()}},r,t)}))()},submitForm:function(e,t){var r=this;this.$refs[e].validate(function(e){e?"refuse"===t?r.$confirm("确认拒绝此次辞退吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(o()(n.a.mark(function e(){return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(""!==r.form.desc){e.next=2;break}return e.abrupt("return",r.$message.warning("拒绝原因不能为空！"));case 2:r.saveRecord("0");case 3:case"end":return e.stop()}},e,r)}))).catch(function(){}):r.saveRecord("1"):console.log("error submit!")})},closePanle:function(){this.$emit("reloadEvent","reload")}},created:function(){var e=this;this.getFormInfo(o()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",Object(l.D)({id:e.recordId}));case 1:case"end":return t.stop()}},t,e)})),"form")}},_={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticStyle:{width:"70%"}},[r("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px",size:"small"}},[r("el-form-item",{attrs:{label:"姓名"}},[r("el-input",{attrs:{disabled:""},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"日期"}},[r("el-date-picker",{attrs:{type:"date",disabled:"",format:"yyyy 年 MM 月 dd 日","value-format":"yyyy-MM-dd"},model:{value:e.form.date,callback:function(t){e.$set(e.form,"date",t)},expression:"form.date"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"操作人"}},[r("el-input",{attrs:{disabled:""},model:{value:e.form.user,callback:function(t){e.$set(e.form,"user",t)},expression:"form.user"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"说明"}},[r("el-input",{attrs:{type:"textarea",rows:3,disabled:"",clearable:""},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"拒绝原因"}},[r("el-input",{attrs:{type:"textarea",rows:3,clearable:"",placeholder:"请输入拒绝原因..."},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","agree")}}},[e._v("同意")]),e._v(" "),r("el-button",{attrs:{type:"danger",plain:"",loading:e.btnLoading},on:{click:function(t){e.submitForm("form","refuse")}}},[e._v("拒绝")])],1)],1)],1)},staticRenderFns:[]};var x=r("VU/8")(g,_,!1,function(e){r("tYxT")},null,null).exports,y={name:"app-notice-list",data:function(){return{search:{dept:"",date:"",status:"",type:""},list:[],loading:!1,curPageIndex:1,dialog:{showVisible:!1},recordId:"",currentComponent:""}},computed:c()({},Object(u.c)("dict",["deptList","noticStateList","noticTypeList"])),methods:c()({},Object(u.b)("dict",["createDeptList","createNoticeStateList","createNoticTypeList"]),{recordHandler:function(e,t){switch(t.toString()){case"1":this.currentComponent="app-notice-out";break;case"2":this.currentComponent="app-notice-in";break;case"3":this.currentComponent="app-notice-quit"}this.dialog.showVisible=!0,this.recordId=e},getNoticeList:function(e,t){var r=this;return o()(n.a.mark(function a(){var s;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return e=e>0?Number(e):r.curPageIndex,a.next=3,Object(l.E)(c()({pageNum:e,pageSize:10},r.search));case 3:1==(s=a.sent).code?(r.list=s.data.list,r.list.total=s.data.totalRow||1,t&&t()):r.$message.error(s.data.message);case 5:case"end":return a.stop()}},a,r)}))()},searchHandle:function(){this.getNoticeList(1)},handleCurrentChange:function(e){this.curPageIndex=e,this.getArticleList(e)},reloadGetData:function(e){if("reload"==e){for(var t in this.dialog)this.dialog[t]=!1;this.getNoticeList(this.curPageIndex)}}}),created:function(){this.getNoticeList(),this.createDeptList(),this.createNoticeStateList(),this.createNoticTypeList()},components:{AppDialog:d.a,AppNoticeOut:f,AppNoticeIn:h,AppNoticeQuit:x}},w={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("nav",{staticClass:"app-location-wrapper"},[r("el-breadcrumb",{staticClass:"fl",attrs:{separator:"/"}},[r("el-breadcrumb-item",{attrs:{to:{path:"/notice"}}},[e._v("消息管理")]),e._v(" "),r("el-breadcrumb-item",[e._v("通知管理")]),e._v(" "),r("el-breadcrumb-item",[e._v("通知列表")])],1)],1),e._v(" "),r("div",{staticClass:"component-top"},[r("div",{staticClass:"search-title fl"},[e._v("门店：")]),e._v(" "),r("el-select",{staticClass:"fl",staticStyle:{width:"160px","margin-right":"10px"},attrs:{size:"small",clearable:"",placeholder:"门店"},on:{change:e.searchHandle},model:{value:e.search.dept,callback:function(t){e.$set(e.search,"dept",t)},expression:"search.dept"}},e._l(e.deptList,function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.value}})})),e._v(" "),r("div",{staticClass:"search-title fl"},[e._v("日期：")]),e._v(" "),r("el-date-picker",{staticClass:"fl",staticStyle:{width:"160px","margin-right":"10px"},attrs:{size:"small",type:"date",placeholder:"选择日期","value-format":"yyyy-MM-dd"},on:{change:e.searchHandle},model:{value:e.search.date,callback:function(t){e.$set(e.search,"date",t)},expression:"search.date"}}),e._v(" "),r("div",{staticClass:"search-title fl"},[e._v("状态：")]),e._v(" "),r("el-select",{staticClass:"fl",staticStyle:{width:"120px","margin-right":"10px"},attrs:{size:"small",clearable:"",placeholder:"状态"},on:{change:e.searchHandle},model:{value:e.search.status,callback:function(t){e.$set(e.search,"status",t)},expression:"search.status"}},e._l(e.noticStateList,function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.value}})})),e._v(" "),r("div",{staticClass:"search-title fl"},[e._v("类型：")]),e._v(" "),r("el-select",{staticClass:"fl",staticStyle:{width:"120px","margin-right":"10px"},attrs:{size:"small",clearable:"",placeholder:"类型"},on:{change:e.searchHandle},model:{value:e.search.type,callback:function(t){e.$set(e.search,"type",t)},expression:"search.type"}},e._l(e.noticTypeList,function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.value}})}))],1),e._v(" "),r("div",{staticClass:"component-main"},[r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{size:"small",data:e.list,stripe:"",border:""}},[r("el-table-column",{attrs:{prop:"content",label:"内容简介","min-width":"200","show-overflow-tooltip":""}}),e._v(" "),r("el-table-column",{attrs:{prop:"type_text",label:"消息类型"}}),e._v(" "),r("el-table-column",{attrs:{prop:"sender_name",label:"发送者"}}),e._v(" "),r("el-table-column",{attrs:{prop:"datetime",label:"日期"}}),e._v(" "),r("el-table-column",{attrs:{label:"状态",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-tag",{attrs:{type:t.row.status_color,size:"medium"}},[e._v(e._s(t.row.status_text))])]}}])}),e._v(" "),r("el-table-column",{attrs:{label:"操作",width:"90"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{size:"mini"},on:{click:function(r){r.stopPropagation(),e.recordHandler(t.row.id,t.row.type)}}},[e._v("\r\n                        查看\r\n                    ")])]}}])})],1),e._v(" "),r("el-pagination",{staticClass:"pagination",attrs:{background:"",layout:"prev, pager, next, jumper",total:e.list.total},on:{"current-change":e.handleCurrentChange}})],1),e._v(" "),r("app-dialog",{attrs:{title:"查看通知",visible:e.dialog.showVisible},on:{"update:visible":function(t){e.$set(e.dialog,"showVisible",t)}}},[r(e.currentComponent,{tag:"component",attrs:{"record-id":e.recordId},on:{reloadEvent:e.reloadGetData}})],1)],1)},staticRenderFns:[]};var k=r("VU/8")(y,w,!1,function(e){r("X5ZL")},null,null);t.default=k.exports},tYxT:function(e,t,r){var a=r("0Euj");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r("rjj0")("23af480f",a,!0,{})},tmqq:function(e,t,r){(e.exports=r("FZ+f")(!1)).push([e.i,".transfer-desc-box{padding:15px 0 20px;line-height:22px;font-size:12px}",""])}});
//# sourceMappingURL=7.00950bd8f1b517020b9b.js.map