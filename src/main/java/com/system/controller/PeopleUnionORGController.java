package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.po.DataTablePageing;
import com.system.po.MydataTableColumn;
import com.system.po.PeopleInfo;
import com.system.po.zTreeNode;
import com.system.service.DeviceInfoService;
import com.system.service.DeviceUnionORGService;
import com.system.service.ORGzTreeService;
import com.system.service.PeopleUnionORGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/peopleUnionORG")
public class PeopleUnionORGController {

    @Autowired
    private PeopleUnionORGService peopleUnionORGService;
    @Autowired
    private ORGzTreeService orGzTreeService;

    @RequestMapping(value = "selectZTreeNode", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectZTreeNode() throws Exception {

        List<zTreeNode> zTreeNodeList = orGzTreeService.selectORGInfo();

        String jsonString = JSON.toJSONString(zTreeNodeList);

        return jsonString;
    }

    @RequestMapping(value = "selectPersonNode", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectPersonNode() throws Exception {

        List<PeopleInfo> peopleInfoList = peopleUnionORGService.selectAllPeopleInfo();

        String jsonString = JSON.toJSONString(peopleInfoList);

        return jsonString;
    }


    @RequestMapping(value = "selectPeopleByORGId", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectDeviceByORGId(Integer pageNumber,Integer pageSize,String sORGId) throws Exception {
        String jsonString = "[]";
        if (sORGId !=null) {
            DataTablePageing dataTablePageing = peopleUnionORGService.selectPeopleByORGIdPaging(pageNumber,pageSize,sORGId);
            jsonString = "{";
            jsonString += "\""+"total"+"\"";
            jsonString += ":";
            jsonString += "\""+dataTablePageing.getTotal()+"\"";
            jsonString += ",";
            jsonString += "\""+"rows"+"\"";
            jsonString += ":";
            jsonString += dataTablePageing.getsReturnJson();
            jsonString += "}";
        }
        return jsonString;
    }


    @RequestMapping(value="/PeopleHead",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String PeopleHead() throws Exception{
        List<MydataTableColumn> myDTCList = new ArrayList<MydataTableColumn>();
        MydataTableColumn mdtc1 = new MydataTableColumn();
        mdtc1.setData("personName");
        mdtc1.setDefaultContent("1");
        mdtc1.setTitle("用户");

        myDTCList.add(mdtc1);

        /*String a = JSONArray.fromObject(myDTCList).toString();
        JSONArray.parseO*/
        String jsonString = JSON.toJSONString(myDTCList);

        return jsonString;
    }

    @RequestMapping(value="/changeUnionORG",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String changeUnionORG(String personId,String personName,String orgId,String orgName) throws Exception{
        PeopleInfo peopleInfo = new PeopleInfo();
        peopleInfo.setPersonId(personId);
        peopleInfo.setPersonName(personName);
        peopleInfo.setOrgId(orgId);
        peopleInfo.setOrgName(orgName);
        peopleUnionORGService.insertUpdatePeople(peopleInfo);
        String jsonString = "修改成功";
        return jsonString;
    }

}
