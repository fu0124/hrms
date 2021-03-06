package com.hr.store.controllers;

import com.common.controllers.BaseCtrl;
import com.hr.store.service.MoveOutService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.utils.UserSessionUtil;
import easy.util.NumberUtils;
import org.apache.commons.lang.StringUtils;
import utils.bean.JsonHashMap;

import java.util.ArrayList;
import java.util.List;

public class MoveOutCtrl extends BaseCtrl {

    /**
     * 8.1.	调出员工列表
     * 名称	查询调出员工列表
     * 描述	查询调出员工的列表信息
     * 验证	无
     * 权限	店长可见
     * URL	http://localhost:8081/mgr/moveOut/list
     * 请求方式	get
     * 请求参数类型	key=value
     * <p>
     * 请求参数列表：
     * 参数名	类型	最大长度	允许空	描述
     * out_store_id	string		是	调出门店
     * In_store_id	string		是	调入门店
     * keyword	string		是	姓名、拼音
     * start_date	string		是	开始日期
     * end_date	string		是	结束日期
     * type	string		是	调出类型。参见数据字典
     * status	string		是	调出状态。参见数据字典
     * <p>
     * 返回数据：
     * 返回格式	JSON
     * 成功	{
     * "code": 1,
     * "data": {
     * "totalRow": 1,
     * "pageNumber": 1,
     * "firstPage": true,
     * "lastPage": true,
     * "totalPage": 1,
     * "pageSize": 10,
     * "list": [{
     * "staff_id": "员工id",
     * "out_store_name": "调出门店名称",
     * "in_store_name": "调入门店名称",
     * "name": "姓名",
     * "date": "2018-06-23",//调出日期
     * "type": "调出",//【调出类型】数据字典的字面值
     * "status": "0",//【调出状态】数据字典的值
     * "status_text": "已调出",//【调出状态】数据字典的字面值
     * "id": ""//调出记录id
     * }]
     * }
     * }
     * 失败	{
     * "code": 0,
     * "message": "失败原因！"
     * }
     * 报错	{
     * "code": -1,
     * "message": "服务器发生异常！"
     * }
     */
    public void list() {
        JsonHashMap jhm = new JsonHashMap();
        List<Object> params = new ArrayList<>();
        UserSessionUtil usu = new UserSessionUtil(getRequest());
        String deptId = usu.getUserBean().getDeptId();
        //页码和每页数据量
        String pageNumStr = getPara("pageNum");
        String pageSizeStr = getPara("pageSize");

        int pageNum = NumberUtils.parseInt(pageNumStr, 1);
        int pageSize = NumberUtils.parseInt(pageSizeStr, 10);

        //sql语句
        String select = "SELECT info.`status` as `status`, (SELECT d.`name` FROM h_dictionary d WHERE d.`value` = info.`status` AND d.parent_id = 6000) as status_text,( SELECT h. NAME FROM h_dictionary h WHERE h.parent_id = 600 AND h.VALUE = info.type ) AS type, info.date AS date, info.id AS id, ( SELECT s. NAME FROM h_store s WHERE s.id = info.to_dept ) AS in_store_name, ( SELECT s. NAME FROM h_store s WHERE s.id = info.from_dept ) AS out_store_name, staff. NAME AS name, staff.staff_id AS staff_id  ";
        StringBuilder sql = new StringBuilder(" FROM h_move_info info, h_move_staff staff, h_staff s WHERE info.from_dept = ? AND staff.move_info_id = info.id AND s.id = staff.staff_id");
        params.add(deptId);

        String outStoreId = getPara("out_store_id");
        String inStoreId = getPara("in_store_id");
        String keyWord = getPara("keyword");
        String startDate = getPara("start_date");
        String endDate = getPara("end_date");
        String type = getPara("type");
        String status = getPara("status");

        if (!(StringUtils.isEmpty(outStoreId) || outStoreId.equals("-1"))) {
            sql.append(" and info.from_dept =  ? ");
            params.add(outStoreId);
        }
        if (!(StringUtils.isEmpty(inStoreId) || inStoreId.equals("-1"))) {
            sql.append(" and info.to_dept = ? ");
            params.add(inStoreId);
        }
        if (!(StringUtils.isEmpty(status) || status.equals("-1"))) {
            sql.append(" and info.status = ? ");
            params.add(status);
        }
        if (!StringUtils.isEmpty(startDate)) {
            sql.append(" and info.date >= ? ");
            params.add(startDate);
        }
        if (!StringUtils.isEmpty(endDate)) {
            sql.append(" and info.date <= ? ");
            params.add(endDate);
        }
        if (!(StringUtils.isEmpty(type) || type.equals("-1"))) {
            sql.append(" and info.type =  ? ");
            params.add(type);
        }
        if (!(StringUtils.isEmpty(keyWord))) {
            keyWord = "%" + keyWord + "%";
            sql.append(" and (staff.name like ? or staff.phone like ? or s.pinyin like ?) ");
            params.add(keyWord);
            params.add(keyWord);
            params.add(keyWord);
        }

        //增加排序
        sql.append(" order by info.date desc");

        try {
            Page<Record> page = Db.paginate(pageNum, pageSize, select, sql.toString(), params.toArray());
            for (int i = 0; i < page.getList().size(); ++i) {
                page.getList().get(i).set("out_store_color", "#b7a6d4");
                page.getList().get(i).set("in_store_color", "#fa7a19");
            }
            jhm.put("data", page);
        } catch (Exception e) {
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }

        renderJson(jhm);
//        renderJson("{\"code\":1,\"data\":{\"totalRow\":1,\"pageNumber\":1,\"firstPage\":true,\"lastPage\":true,\"totalPage\":1,\"pageSize\":10,\"list\":[{\"staff_id\":\"员工id\",\"out_store_name\":\"面对面（长大店）\",\"in_store_name\":\"面对面（红旗街店）\",\"out_store_color\":\"#b7a6d4\",\"in_store_color\":\"#fa7a19\",\"name\":\"马云\",\"date\":\"2018-06-23\",\"type\":\"调出\",\"status\":\"0\",\"status_text\":\"已调出\",\"id\":\"id\"}]}}");
    }

    /**
     * 8.2.	调出店员
     * 名称	店长调出（借调出）店员
     * 描述	店长调出店员，要给调入门店发通知
     * 验证	无
     * 权限	店长可见
     * URL	http://localhost:8081/mgr/moveOut/out
     * 请求方式	Post
     * 请求参数类型	JSON
     * <p>
     * 请求参数列表：
     * 参数名	类型	最大长度	允许空	描述
     * staff_id	array		否	调出员工id
     * date	string		否	调出日期
     * type	string		否	调动类型。参见数据字典
     * to_store	string		否	调入门店id
     * desc	string		是	说明
     * <p>
     * 返回数据：
     * 返回格式	JSON
     * 成功	{
     * "code": 1,
     * "message": "调出成功！"
     * }
     * 失败	{
     * "code": 0,
     * "message": "请选择类别！"
     * }
     * 或者
     * {
     * "code": 0,
     * "message": "保存失败！"
     * }
     * 报错	{
     * "code": -1,
     * "message": "服务器发生异常！"
     * }
     */
    public void out() {
        JsonHashMap jhm = new JsonHashMap();

        String id = getPara("staff_id");
        if (StringUtils.isEmpty(id)) {
            jhm.putCode(0).putMessage("请选择员工");
            renderJson(jhm);
            return;
        }
        String date = getPara("date");
        if (StringUtils.isEmpty(date)) {
            jhm.putCode(0).putMessage("请选择时间");
            renderJson(jhm);
            return;
        }
        String type = getPara("type");
        if (StringUtils.isEmpty(type) || StringUtils.equals(type, "-1")) {
            jhm.putCode(0).putMessage("请选择类型");
            renderJson(jhm);
            return;
        }
        String toStore = getPara("to_store");
        if (StringUtils.isEmpty(toStore) || StringUtils.equals(toStore, "-1")) {
            jhm.putCode(0).putMessage("请选择调入门店");
            renderJson(jhm);
            return;
        }
        String desc = getPara("desc");
        if (StringUtils.isEmpty(desc)) {
            jhm.putCode(0).putMessage("请填写说明");
            renderJson(jhm);
            return;
        }


        UserSessionUtil usu = new UserSessionUtil(getRequest());
        Record record = new Record();
        record.set("date", date);
        record.set("type", type);
        record.set("desc", desc);
        record.set("toStore", toStore);

        MoveOutService mos = enhance(MoveOutService.class);
        jhm = mos.out(record, usu, id.split(","));
        renderJson(jhm);

//        renderJson("{\n" +
//                "     \"code\": 1,\n" +
//                "     \"message\": \"调出成功！\"\n" +
//                "     }");
    }

    /**
     * 8.3.	查看调出详细信息
     * 名称	查看调出信息
     * 描述	根据调出记录id查询调出详细信息
     * 验证	根据id验证调出信息是否存在
     * 权限	店长可见
     * URL	http://localhost:8081/mgr/moveOut/showById
     * 请求方式	get
     * 请求参数类型	key=value
     * <p>
     * 请求参数列表：
     * 参数名	类型	最大长度	允许空	描述
     * id	string		否	调出记录id
     * <p>
     * 返回数据：
     * 返回格式	JSON
     * 成功	{
     * "code": 1,
     * "data": {
     * "id": "调出记录id",
     * "name": "鹿晗、吴亦凡",//多个人们用顿号“、”分隔
     * "date": "2018-06-23",//调出日期
     * "type_text": "调出",//调出类型字典，显示字面值
     * "to_store_name": "长大店",
     * "desc": "说明",
     * "status": "0",
     * "status_text": "已调出",//状态字面值
     * }
     * }
     * 失败	{
     * "code": 0,
     * "message": "该记录不存在！"
     * }
     * 或者
     * {
     * "code": 0,
     * "message": ""//其实失败信息
     * }
     * 报错	{
     * "code": -1,
     * "message": "服务器发生异常！"
     * }
     */
    public void showById() {
        JsonHashMap jhm = new JsonHashMap();

        String id = getPara("id");
        if (StringUtils.isEmpty(id)) {
            jhm.putCode(0).putMessage("请选择员工！");
            renderJson(jhm);
            return;
        }

        String sql = "SELECT (SELECT store.`name` FROM h_store store WHERE store.id = info.to_dept) as to_store_name ,info.id AS id, staff.`name` AS `name`, info.date AS date, info.result AS `desc`, info.`status` AS `status`, ( SELECT d. NAME FROM h_dictionary d WHERE d.`value` = info.`status` AND d.parent_id = 6000 ) AS status_text, ( SELECT d.`name` FROM h_dictionary d WHERE d.`value` = info.type AND d.parent_id = 600 ) AS type_text FROM h_move_info info, h_move_staff staff WHERE staff.move_info_id = info.id AND info.id = ?";

        try {
            List<Record> recordList = Db.find(sql, id);
            if (recordList != null && recordList.size() > 0) {
                Record record = recordList.get(0);
                for (int i = 1; i < recordList.size(); ++i) {
                    record.set("name", record.getStr("name") + "、" + recordList.get(i).getStr("name"));
                }
                jhm.put("data", record);
            } else {
                jhm.putCode(0).putMessage("该记录不存在!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }
        renderJson(jhm);
//        renderJson("{\"code\":1,\"data\":{\"id\":\"调出记录id\",\"name\":\"鹿晗、吴亦凡\",\"date\":\"2018-06-23\",\"type_text\":\"调出\",\"to_store_name\":\"长大店\",\"desc\":\"这两个人可以用\",\"status\":\"0\",\"status_text\":\"已调出\"}}");
    }

    /**
     * 8.4.	撤销调出
     * 名称	撤销调出
     * 描述	店长调出本店员工后，还可以撤销此操作
     * 验证	根据调出id验证此记录是否存在
     * 如果对方门店已经接收或者拒绝（即status值为2或3），那么将不允许撤销
     * 权限	店长可见
     * URL	http://localhost:8081/mgr/moveOut/cancelOut
     * 请求方式	get
     * 请求参数类型	key=value
     * <p>
     * 请求参数列表：
     * 参数名	类型	最大长度	允许空	描述
     * id	string		否	调出记录id
     * <p>
     * 返回数据：
     * 返回格式	JSON
     * 成功	{
     * "code": 1,
     * "message": "撤销成功！"
     * }
     * 失败	{
     * "code": 0,
     * "message": "长大店已经接收，不能撤销！"
     * }
     * 或者
     * {
     * "code": 0,
     * "message": "保存失败！"
     * }
     * 报错	{
     * "code": -1,
     * "message": "服务器发生异常！"
     * }
     */

    public void cancelOut() {
        JsonHashMap jhm = new JsonHashMap();

        String id = getPara("id");
        if (StringUtils.isEmpty(id)) {
            jhm.putCode(0).putMessage("请选择员工!");
            renderJson(jhm);
            return;
        }

        String sql = "SELECT info.id as id , info.`status` as `status` FROM h_move_info info WHERE info.id = ? ";

        try {
            Record record = Db.findFirst(sql, id);
            if (record != null) {
                if (StringUtils.equals(record.getStr("status"), "0") || StringUtils.equals(record.getStr("status"), "1")) {
                    record.set("status", "1");
                    boolean flag = Db.update("h_move_info", record);
                    if (flag) {
                        jhm.putMessage("撤销成功");
                    } else {
                        jhm.putCode(0).putMessage("保存失败");
                    }
                } else {
                    jhm.putCode(0).putMessage("已被接收或拒绝，不能撤销");
                }
            } else {
                jhm.putCode(0).putMessage("该记录不存在!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jhm.putCode(-1).putMessage("服务器发生异常！");
        }

        renderJson(jhm);
//        renderJson("{\"code\":1,\"message\":\"撤销成功！\"}");
    }

}
