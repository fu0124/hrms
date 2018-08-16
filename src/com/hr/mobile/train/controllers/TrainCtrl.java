package com.hr.mobile.train.controllers;

import com.common.controllers.BaseCtrl;
import com.hr.mobile.train.service.TrainService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import easy.util.DateTool;
import easy.util.UUIDTool;
import org.apache.commons.lang.StringUtils;
import utils.bean.JsonHashMap;
import utils.jfinal.RecordUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainCtrl extends BaseCtrl{
    /**
     * 岗位名字中文转英文
     */
    public String translate(String str){
        String sql="SELECT value FROM h_dictionary WHERE name=?";
        Record r=Db.findFirst(sql,str);
        if (r==null){
            return null;
        }
        return r.getStr("value");
    }

    /**
     * 员工查看一级培训列表
     名称	查看一级培训列表
     描述	根据员工id查询一级培训列表
     验证	无
     权限	无
     URL	http://localhost:8081/hrms/mgr/mobile/train/showTrainTypeList
     请求方式	get
     请求参数类型	key=value

     请求参数列表：
     参数名	类型	最大长度	允许空	描述
     staff_id	string		否	员工id
     train_id	string		是	Id为空时，返回一级列表信息(id ,名称,状态);
     Id不为空时，返回二级列表信息(id ,名称,状态)


     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "list": [{
     “train_id”:”培训id”,
     "name": “培训名称",
     "status": "0",
     },{
     “train_id”:”培训id”,
     "name": "培训名称",
     "status": "0",
     }]
     }
     status: 0:未完成 1:已完成
     失败	{
     "code": 0,
     "message": "失败原因！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }
     */
    public void showTrainTypeList(){
        JsonHashMap jhm = new JsonHashMap();
        String staff_id = getPara("staff_id");
        String train_id = getPara("train_id");

        //进行非空验证
        if(StringUtils.isEmpty(staff_id)){
            jhm.putCode(0).putMessage("员工id不能为空！");
            renderJson(jhm);
            return;
        }

        try {
            String staffSearch = "SELECT * FROM h_staff WHERE id=?";
            Record countR = Db.findFirst(staffSearch, staff_id);
            if(countR!=null){
                //返回一级列表 type_1!!!!
                if(StringUtils.isEmpty(train_id)){
                    String trainSearch = "SELECT type.id AS train_id ,type.name AS name,train.status AS status FROM h_train_type type,h_staff_train train\n" +
                            "WHERE type.parent_id='-1' AND (train.type_2='' OR ISNULL(train.type_2)) AND train.type_1=type.id \n" +
                            "AND train.staff_id=?";
                    List<Record> typeList = Db.find(trainSearch, staff_id);
                    jhm.putCode(1);
                    jhm.put("list",typeList);
                } else {
                    //返回单个一级列表下所有的二级列表 type_2
                    String trainSearch = "SELECT type.id AS train_id ,type.name AS name,train.status AS status FROM h_train_type type,h_staff_train train\n" +
                            "WHERE type.parent_id=? AND train.staff_id=? AND train.type_2=type.id ";
                    List<Record> typeList = Db.find(trainSearch, train_id, staff_id);
                    int count=0;
                    if (typeList!=null&&typeList.size()>0){
                        for (Record r:typeList){
                            String status=r.getStr("status");
                            if (!status.equals("1")){
                                break;
                            }
                            count++;
                            //说明二级列表都已通过 则一级列表状态为已通过
                            if (count==typeList.size()){
                                Db.update("UPDATE h_staff_train SET status='1' WHERE staff_id=? AND type_1=? AND (type_2=''OR ISNULL(type_2))",staff_id,train_id);
                            }
                        }
                    }
                    jhm.putCode(1);
                    jhm.put("list",typeList);
                }
            } else {
                jhm.putCode(0).putMessage("员工不存在！");
            }
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }

    /**
     * 员工查看培训详情
     名称	查看培训详情
     描述	根据员工id和二级培训名称查询培训详情
     验证	无
     权限	无
     URL	http://localhost:8081/hrms/mgr/mobile/train/showTrainDetail
     请求方式	get
     请求参数类型	key=value

     请求参数列表：
     参数名	类型	最大长度	允许空	描述
     staff_id	string		否	员工id
     name	string		否	 二级培训名称

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "video": "url",
     "picture": "url",
     "content": "内容"
     }
     video:视频 url链接
     picture:图文
     失败	{
     "code": 0,
     "message": "失败原因！"
     }
     报错	{
     "code": -1,
     "message": "服务器发生异常！"
     }
     */

    public void showTrainDetail(){
        JsonHashMap jhm = new JsonHashMap();
        String type_id = getPara("type_id");
        String staff_id = getPara("staff_id");

        //进行非空验证
        if(StringUtils.isEmpty(staff_id)){
            jhm.putCode(0).putMessage("员工id不能为空！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(type_id)){
            jhm.putCode(0).putMessage("二级分类id为空！");
            renderJson(jhm);
            return;
        }

        try {
            String search = "select count(*) as c from h_staff s where s.id = ? ";
            Record countR = Db.findFirst(search, staff_id);
            if(countR.getInt("c") != 0){
                String sql = "select a.content as content from h_train_article a where a.type_2 = ? ";
                Record record = Db.findFirst(sql, type_id);
                if (record==null){
                    jhm.putCode(0).putMessage("培训详情为空！");
                    renderJson(jhm);
                    return;
                }
                jhm.putCode(1);
                jhm.put("content", record.getStr("content"));
            } else {
                jhm.putCode(0).putMessage("员工不存在！");
            }
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }

    /**
     * 员工点完成
     名称	员工看完培训详情
     描述	根据员工id和二级培训名称看完培训详情
     验证	无
     权限	无
     URL	http://localhost:8081/hrms/mgr/mobile/train/finish
     请求方式	get
     请求参数类型	key=value

     请求参数列表：
     参数名	类型	最大长度	允许空	描述
     staff_id	string		否	员工id
     type_id	string		否	 二级培训id

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "message": "已经完成！"
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

    public void finish(){
        JsonHashMap jhm = new JsonHashMap();
        String staff_id = getPara("staff_id");
        String type_id = getPara("type_id");

        //进行非空验证
        if(StringUtils.isEmpty(staff_id)){
            jhm.putCode(0).putMessage("员工id不能为空！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(type_id)){
            jhm.putCode(0).putMessage("二级分类id为空！");
            renderJson(jhm);
            return;
        }

        try {
            Map paraMap=new HashMap();
            paraMap.put("staff_id", staff_id);
            paraMap.put("type_id", type_id);
            TrainService srv = enhance(TrainService.class);
            jhm=srv.finish(paraMap);

        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }



    /**
     * 员工申请考核
     名称	员工申请考核
     描述	根据员工id和岗位名称申请考核
     验证	无
     权限	无
     URL	http://localhost:8081/hrms/mgr/mobile/train/applyCheck
     请求方式	get
     请求参数类型	key=value

     请求参数列表：
     参数名	类型	最大长度	允许空	描述
     staff_id	string		否	员工id
     type_id	string		否	培训id

     返回数据：
     返回格式	JSON
     成功	{
     "code": 1,
     "message": "提交成功！"
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

    public void applyCheck(){
        JsonHashMap jhm = new JsonHashMap();
        String staff_id = getPara("staff_id");
        String type_id = getPara("type_id");

        //进行非空验证
        if(StringUtils.isEmpty(staff_id)){
            jhm.putCode(0).putMessage("员工id不能为空！");
            renderJson(jhm);
            return;
        }
        if(StringUtils.isEmpty(type_id)){
            jhm.putCode(0).putMessage("培训id不能为空！");
            renderJson(jhm);
            return;
        }

        try {
            String id=UUIDTool.getUUID();
            String createTime=DateTool.GetDateTime();
            //查询staff表得 入职日期hiredate 店铺id dept_id
            String sql1="SELECT hiredate,dept_id FROM h_staff WHERE id=?";
            Record r1=Db.findFirst(sql1,staff_id);
            if (r1==null){
                jhm.putCode(0).putMessage("查询不到入职日期和门店id！");
                renderJson(jhm);
                return;
            }
            String hiredate=r1.getStr("hiredate");
            String deptId=r1.getStr("dept_id");
            String sql2="SELECT id FROM h_staff WHERE dept_id=? AND job='store_manager'";
            Record r2=Db.findFirst(sql2,deptId);
            if (r2==null){
                jhm.putCode(0).putMessage("查询不到店长id！");
                renderJson(jhm);
                return;
            }
            String examinerId=r2.getStr("id");

            //根据二级培训id 查train_type表的id得到name中文
            String sql3="SELECT name FROM h_train_type WHERE id=?";
            Record r3=Db.findFirst(sql3,type_id);
            if (r3==null){
                jhm.putCode(0).putMessage("查询不到二级分类名称！");
                renderJson(jhm);
                return;
            }
            String chineseName=r3.getStr("name");
            String englishName=translate(chineseName);

            Record record=new Record();
            record.set("id",id);
            record.set("staff_id",staff_id);
            record.set("create_time",createTime);
            record.set("hiredate",hiredate);
            record.set("kind_id",englishName);
            record.set("examiner_id",examinerId);
            record.set("result","0");
            record.set("type_id",type_id);

            boolean flag=Db.save("h_exam",record);
            if (flag){
                jhm.putCode(1).putMessage("提交成功！");
            }else {
                jhm.putCode(0).putMessage("提交失败！");
            }
            Db.update("UPDATE h_staff_train SET status=? WHERE staff_id=? AND type_2=?","3",staff_id,type_id);
        } catch (Exception e){
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
    }
}
