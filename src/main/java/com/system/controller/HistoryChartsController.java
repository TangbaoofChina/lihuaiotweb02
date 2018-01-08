package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.po.DeviceInfo;
import com.system.po.DeviceMessage;
import com.system.po.MydataTableColumn;
import com.system.po.ParameterCharts;
import com.system.service.DeviceInfoService;
import com.system.service.DeviceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/historyCharts")
public class HistoryChartsController {

    @Resource(name = "deviceMessageServiceImpl")
    private DeviceMessageService deviceMessageService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<历史数据操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @RequestMapping(value="/hisMessageTables",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  String LoadDevicesHisMessageTable(String[] sDevicesId,String sQueryParam,String sStartTime,String sEndTime) throws Exception{
        //表头
        String sReturnJson = "[]";
        if (sDevicesId==null )
        {
            return sReturnJson;
        }
        if (sDevicesId.length >0 ) {
            List<DeviceInfo> deviceInfoList = deviceInfoService.selectDeviceInfoByIDs(sDevicesId);
            //表的内容 横向标识的，需要变成list<1,2,3>,再有很多个list<list>?
            //List<List<OneDataDetail>> dataDetailList = deviceMessageService.selectHisDevMsgByTimeAndIDs(deviceInfoList,sDevicesId,sQueryParam, sStartTime, sEndTime);

            sReturnJson = deviceMessageService.selectHisDevMsgByTimeAndIDs(deviceInfoList, sDevicesId, sQueryParam, sStartTime, sEndTime);

        }
        return sReturnJson;
    }

    @RequestMapping(value="/hisMessageCharts",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  String LoadDeviceHisMessageChart(String[] sDevicesId,String sQueryParam,String sStartTime,String sEndTime) throws Exception{
        //List<DeviceMessage> deviceMessageList = deviceMessageService.selectHisDeviceMessage();
        ParameterCharts parameterCharts= deviceMessageService.selectHisDevMsgByTimeAndIDs(sDevicesId,sQueryParam, sStartTime, sEndTime);
        if (parameterCharts == null)
            return "[]";
        String jsonString = JSON.toJSONString(parameterCharts);
        return jsonString;
    }

    @RequestMapping(value="/DevicesHead",method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String DeviceHead(String[] sDevicesId) throws Exception{
        List<MydataTableColumn> myDTCList = new ArrayList<MydataTableColumn>();
        if (sDevicesId.length > 0) {
            List<DeviceInfo> deviceInfoList = deviceInfoService.selectDeviceInfoByIDs(sDevicesId);

            for (DeviceInfo deviceInfo : deviceInfoList
                    ) {
                MydataTableColumn mdtc = new MydataTableColumn();
                mdtc.setData(deviceInfo.getdSerialNum());
                mdtc.setDefaultContent("1");
                mdtc.setTitle(deviceInfo.getdName());
                myDTCList.add(mdtc);
            }
        }

        MydataTableColumn mdtcTime = new MydataTableColumn();
        mdtcTime.setData("sSendTime");
        mdtcTime.setDefaultContent("时间");
        mdtcTime.setTitle("发送时间");

        myDTCList.add(mdtcTime);
        /*String a = JSONArray.fromObject(myDTCList).toString();
        JSONArray.parseO*/
        String jsonString = JSON.toJSONString(myDTCList);

        return jsonString;
    }


}
