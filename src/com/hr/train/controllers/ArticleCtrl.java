package com.hr.train.controllers;

import com.common.controllers.BaseCtrl;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sun.javafx.tk.RenderJob;
import com.utils.UserSessionUtil;
import easy.util.DateTool;
import easy.util.NumberUtils;
import easy.util.UUIDTool;
import org.apache.commons.lang.StringUtils;
import utils.bean.JsonHashMap;

import java.util.ArrayList;
import java.util.List;

public class ArticleCtrl extends BaseCtrl {
    /**
     15.7.	文章列表
     名称	文章列表
     描述	查询文章列表
     根据keyword模糊查询文章标题
     验证	无
     权限	Hr可见
     URL	http://localhost:8081/mgr/train/article/list
     请求方式	get
     请求参数类型	key=value

     请求参数列表：
     参数名	类型	最大长度	允许空	描述
     keyword	string		是	关键字

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "data": {
     "totalRow": 1,
     "pageNumber": 1,
     "firstPage": true,
     "lastPage": true,
     "totalPage": 1,
     "pageSize": 10,
     "list": [{
     "id": "文章id",
     "title": "文章名称",
     "datetime": "2018-6-26 13:52",//最后一次修改日期
     "creater_name": "马云"//发布人
     }]
     }
     }
     失败	{
     "code": 0,
     "message": "失败原因！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }
*/
    public void list(){
//        renderJson("{\"code\":1,\"data\":{\"totalRow\":1,\"pageNumber\":1,\"firstPage\":true,\"lastPage\":true,\"totalPage\":1,\"pageSize\":10,\"list\":[{\"id\":\"文章id\",\"title\":\"文章名称\",\"datetime\":\"2018-6-26 13:52\",\"creater_name\":\"马云\"}]}}");
        JsonHashMap jhm = new JsonHashMap();
        String keyword = getPara("keyword");
        String classId = getPara("class_id");
        String pageNumStr = getPara("pageNum");
        String pageSizeStr = getPara("pageSize");

        //为空时赋予默认值
        int pageNum = NumberUtils.parseInt(pageNumStr, 1);
        int pageSize = NumberUtils.parseInt(pageSizeStr, 10);

        try {
            /*
            select : 查询内容
            sql : 添加查询条件
            parentSearch : sql语句判断是几级分类
             */
            String select = "SELECT h_train_article.id id, h_train_article.title title, h_train_article.create_time datetime, h_staff. NAME creater_name ";
            StringBuilder sql = new StringBuilder();
            sql.append("FROM h_train_article LEFT JOIN h_staff ON h_train_article.creater_id = h_staff.id WHERE 1 = 1 ");
            List<Object> params = new ArrayList<>();

            //根据分类id判断是几级分类
            if(!StringUtils.isEmpty(classId)){
                String parentSerach = "select parent_id p from h_train_type t where t.id = ? ";
                Record record = Db.findFirst(parentSerach,classId);
                if(StringUtils.equals(record.getStr("p"),"-1")){
                    sql.append(" and type_1 = ? ");
                    params.add(classId);
                } else {
                    sql.append(" and type_2 = ? ");
                    params.add(classId);
                }
            }

            //参数集合
            if(!StringUtils.isEmpty(keyword)){
                keyword = "%" + keyword + "%";
                sql.append(" and title like ? ");
                params.add(keyword);
            }
            sql.append(" order by h_train_article.create_time desc,id ");
            Page <Record> page = Db.paginate(pageNum, pageSize, select, sql.toString(), params.toArray());
            jhm.put("data",page);
        }catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
    /**
     15.8.	添加文章
     名称	添加文章
     描述	添加文章。
     验证	文章标题不能重复
     权限	Hr可见
     URL	http://localhost:8081/mgr/train/article/add
     请求方式	post
     请求参数类型	key=value

     请求参数：
     参数名	类型	最大长度	允许空	描述
     title	string		否	文章名称
     content	string		是	内容

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "message": "添加成功！"
     }
     失败	{
     "code": 0,
     "message": "请填写文章标题！"
     }
     或者
     {
     "code": 0,
     "message": "文章标题重复！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }
*/
    public void add(){
//        String content = getPara("content");
//        System.out.println(content);
//        renderJson("{\"code\":1,\"message\":\"添加成功！\"}");

        JsonHashMap jhm = new JsonHashMap();
        String title = getPara("title").trim();
        String content = getPara("content");
        String class_id = getPara("class_id");
        UserSessionUtil usu = new UserSessionUtil(getRequest());

        //进行非空验证
        if(StringUtils.isBlank(title)){
            jhm.putCode(0).putMessage("请填写文章标题！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(class_id)){
            jhm.putCode(0).putMessage("请选择分类！");
            renderJson(jhm);
            return;
        }
        try {
            //sql查询文章标题是否重复
            String sql = "select count(*)c from h_train_article where title = ?";
            Record typeSearch = Db.findFirst(sql,title);
            if(typeSearch.getInt("c" ) != 0 ){
                jhm.putCode(0).putMessage("文章标题重复!");
            } else {
                Record type = new Record();
                type.set("title",title);
                type.set("content",content);

                //查数据库分类
                String classSearch = "select parent_id from h_train_type t where t.id = ?";
                Record parentClass = Db.findFirst(classSearch,class_id);
                String parent_type = parentClass.getStr("parent_id");
                if(StringUtils.equals(parent_type, "-1")){
                    jhm.putCode(0).putMessage("添加文章时不能选择一级分类！");
                } else {
                    type.set("type_1",parent_type);
                    type.set("type_2",class_id);

                    type.set("id", UUIDTool.getUUID());
                    type.set("creater_id",usu.getUserId());
                    type.set("modifier_id",usu.getUserId());
                    String time = DateTool.GetDateTime();
                    type.set("create_time", time);
                    type.set("modify_time", time);
                    boolean flag = Db.save("h_train_article", type);//保存数据到数据库
                    if (flag) {
                        jhm.putCode(1).putMessage("添加成功！");
                    } else {
                        jhm.putCode(0).putMessage("添加失败！");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
    /**
     15.9.	修改文章
     名称	修改文章
     描述	根据id修改文章。
     验证	文章标题不能重复
     权限	Hr可见
     URL	http://localhost:8081/mgr/train/article/updateById
     请求方式	post
     请求参数类型	key=value

     请求参数：
     参数名	类型	最大长度	允许空	描述
     id	string		否	文章id
     class_id  string  否  分类id
     title	string		否	文章名称
     content	string		是	内容

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "message": "修改成功！"
     }
     失败	{
     "code": 0,
     "message": "请填写文章标题！"
     }
     或者
     {
     "code": 0,
     "message": "文章标题重复！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }
*/
    public void updateById(){
//        renderJson("{\"code\":1,\"message\":\"修改成功！\"}");
        JsonHashMap jhm = new JsonHashMap();
        String title = getPara("title").trim();
        String id = getPara("id");
        String type_2 = getPara("class_id");
        String content = getPara("content");
        UserSessionUtil usu = new UserSessionUtil(getRequest());

        //进行非空验证
        if(StringUtils.isEmpty(id)){
            jhm.putCode(0).putMessage("修改失败！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(title)){
            jhm.putCode(0).putMessage("请填写文章标题！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(type_2)){
            jhm.putCode(0).putMessage("请选择分类！");
            renderJson(jhm);
            return;
        }
        try {
            String search = "select count(*) as c from h_train_article where title = ? and id <> ? ";
            Record rSearch = Db.findFirst(search, title, id);
            if(rSearch.getInt("c") != 0){
                jhm.putCode(0).putMessage("文章标题重复！");
                renderJson(jhm);
                return;
            }
            Record record = Db.findById("h_train_article",id);
            record.set("title",title);
            record.set("content",content);
            record.set("type_2", type_2);
            record.set("modifier_id", usu.getUserId());
            String time = DateTool.GetDateTime();//获取时间的通用方法，yyyy-MM-dd HH:mm:ss   这个类中也有其他格式的获取方法
            record.set("modify_time", time);
            boolean flag = Db.update("h_train_article", record);
            if (flag) {
                jhm.putCode(1).putMessage("修改成功！");
            } else {
                jhm.putCode(0).putMessage("修改失败！");
            }
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
    /**
     15.10.	查看文章
     名称	查看文章
     描述	根据id查询文章信息
     验证	根据传入id判断文章是否存在
     权限	Hr可见
     URL	http://localhost:8081/mgr/train/article/showById
     请求方式	get
     请求参数类型	key=value

     请求参数：
     参数名	类型	最大长度	允许空	描述
     id	string		否	文章id

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "data": {
     "id": "134adjfwe",//文章id
     "title": "餐具的摆放",//标题
     "class_id": "234k5jl234j5lkj24l35j423l5j",//分类id
     "content": "<hr><h1>sdfsdfd</h1>",//内容
     "create_time": "2018-06-28",
     "author": "作者"
     }
     }
     失败	{
     "code": 0,
     "message": "文章不存在！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }

*/
    public void showById(){
//        renderJson("{\"code\":1,\"data\":{\"id\":\"134adjfwe\",\"title\":\"餐具的摆放\",\"class_id\":\"234k5jl234j5lkj24l35j423l5j\",\"content\":\"<hr><h1>sdfsdfd</h1>\",\"create_time\":\"2018-06-28\",\"author\":\"作者\"}}");
        JsonHashMap jhm = new JsonHashMap();
        Record record = this.getParaRecord();
        UserSessionUtil usu = new UserSessionUtil(getRequest());

        //进行非空验证
        if(StringUtils.isEmpty(record.getStr("id"))){
            jhm.putCode(0).putMessage("id不能为空！");
            renderJson(jhm);
            return;
        }

        try {
            Record r = Db.findById("h_train_article",record.getStr("id"));
            if(r != null){
                Record nameR=Db.findFirst("SELECT name FROM h_staff WHERE id=?",r.getStr("creater_id"));
                r.set("author",nameR.getStr("name"));
                r.remove("creater_id");
                //r.remove("create_time");
                r.remove("modifier_id");
                r.remove("modify_time");
                r.remove("type_1");
                r.set("class_id",r.getStr("type_2"));
                r.remove("type_2");
                jhm.putCode(1).put("data",r);
            } else {
                jhm.putCode(0).putMessage("文章不存在！");
            }
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
    /**
     15.11.	删除文章
     名称	删除文章
     描述	根据id删除文章
     验证	根据传入id判断文章是否存在
     权限	Hr可见
     URL	http://localhost:8081/mgr/train/article/deleteById
     请求方式	get
     请求参数类型	key=value

     请求参数：
     参数名	类型	最大长度	允许空	描述
     id	string		否	文章id

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "message": "删除成功！"
     }
     失败	{
     "code": 0,
     "message": "文章不存在！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }

     */
    public void deleteById(){
//        renderJson("{\"code\":1,\"message\":\"删除成功！\"}");
        JsonHashMap jhm = new JsonHashMap();
        String id = getPara("id");

        //进行非空验证
        if(StringUtils.isEmpty(id)){
            jhm.putCode(0).putMessage("删除失败！");
            renderJson(jhm);
            return;
        }

        try {
            Record record = Db.findById("h_train_article", id);
            if(record == null){
                jhm.putCode(0).putMessage("文章不存在！");
                renderJson(jhm);
                return;
            } else {
                String sql = "select count(*) as c from h_train_article where id = ?";
                Record r = Db.findFirst(sql, id);
                if(r.getInt("c") == 0){
                    jhm.putCode(0).putMessage("删除失败！");
                } else {
                    boolean flag = Db.deleteById("h_train_article", id);
                    if(flag){
                        jhm.putCode(1).putMessage("删除成功！");
                    } else {
                        jhm.putCode(0).putMessage("删除失败！");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
}
