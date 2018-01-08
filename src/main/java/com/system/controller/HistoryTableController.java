package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.po.DeviceMessage;
import com.system.po.MydataTableColumn;
import com.system.service.DeviceMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/historyTable")
public class HistoryTableController {

    @Resource(name = "deviceMessageServiceImpl")
    private DeviceMessageService deviceMessageService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<历史数据操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  历史数据显示
    @RequestMapping(value="/showHisMessageTable",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  String LoadDeviceHisMessageTable(String sDeviceId,String sStartTime,String sEndTime) throws Exception{
        //List<DeviceMessage> deviceMessageList = deviceMessageService.selectHisDeviceMessage();
        List<DeviceMessage> deviceMessageList = deviceMessageService.selectHisDeviceMessageByTimeAndId(sDeviceId, sStartTime, sEndTime);
        String jsonString = JSON.toJSONString(deviceMessageList);
        return jsonString;
    }

    @RequestMapping(value="/DeviceHead",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String DeviceHead() throws Exception{
        List<MydataTableColumn> myDTCList = new ArrayList<MydataTableColumn>();
        MydataTableColumn mdtc1 = new MydataTableColumn();
        mdtc1.setData("deviceSerialNum");
        mdtc1.setDefaultContent("1");
        mdtc1.setTitle("序号");

        MydataTableColumn mdtc2 = new MydataTableColumn();
        mdtc2.setData("dName");
        mdtc2.setDefaultContent("2");
        mdtc2.setTitle("名称");

        MydataTableColumn mdtc3 = new MydataTableColumn();
        mdtc3.setData("eC01InTemp1");
        mdtc3.setDefaultContent("3");
        mdtc3.setTitle("室内温度1");

        MydataTableColumn mdtc4 = new MydataTableColumn();
        mdtc4.setData("eC01InTemp2");
        mdtc4.setDefaultContent("4");
        mdtc4.setTitle("室内温度2");

        MydataTableColumn mdtc5 = new MydataTableColumn();
        mdtc5.setData("eC01InTemp3");
        mdtc5.setDefaultContent("5");
        mdtc5.setTitle("室内温度3");

        MydataTableColumn mdtc6 = new MydataTableColumn();
        mdtc6.setData("eC01OutTemp4");
        mdtc6.setDefaultContent("6");
        mdtc6.setTitle("室外温度");

        MydataTableColumn mdtc7 = new MydataTableColumn();
        mdtc7.setData("sSendTime");
        mdtc7.setDefaultContent("7");
        mdtc7.setTitle("发送时间");

        MydataTableColumn mdtc8 = new MydataTableColumn();
        mdtc8.setData("sRecevieTime");
        mdtc8.setDefaultContent("8");
        mdtc8.setTitle("接收时间");

        myDTCList.add(mdtc1);
        myDTCList.add(mdtc2);
        myDTCList.add(mdtc3);
        myDTCList.add(mdtc4);
        myDTCList.add(mdtc5);
        myDTCList.add(mdtc6);
        myDTCList.add(mdtc7);
        myDTCList.add(mdtc8);
        /*String a = JSONArray.fromObject(myDTCList).toString();
        JSONArray.parseO*/
        String jsonString = JSON.toJSONString(myDTCList);

        return jsonString;
    }


}
