package com.system.po;

import com.system.po.parameter.ChartsParameters01;
import com.system.po.parameter.ParameterData01;

import java.util.ArrayList;
import java.util.List;

public class DeviceCharts01 {
    private DeviceInfo deviceInfo;
    private ChartsParameters01 chartsParameters01;

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public ChartsParameters01 getChartsParameters01() {
        return chartsParameters01;
    }

    public void setChartsParameters01(ChartsParameters01 chartsParameters01) {
        this.chartsParameters01 = chartsParameters01;
    }

    public DeviceCharts01(List<DeviceMessage> deviceMessageList)
    {
        List<String> deviceParameterName = new ArrayList<String>();
        List<String> deviceParameterTime = new ArrayList<String>();
        List<ParameterData01> parameterDataList = new ArrayList<ParameterData01>();

        deviceParameterName.add("室内温度01");
        deviceParameterName.add("室内温度02");
        deviceParameterName.add("室内温度03");
        deviceParameterName.add("室外温度04");

        ParameterData01 parameterInTemp1 = new ParameterData01();
        parameterInTemp1.setName("室内温度01");
        ParameterData01 parameterInTemp2 = new ParameterData01();
        parameterInTemp2.setName("室内温度02");
        ParameterData01 parameterInTemp3 = new ParameterData01();
        parameterInTemp3.setName("室内温度03");
        ParameterData01 parameterOutTemp4 = new ParameterData01();
        parameterOutTemp4.setName("室外温度04");

        List<OneDataDetail> inTemp01List = new ArrayList<OneDataDetail>();
        List<OneDataDetail> inTemp02List = new ArrayList<OneDataDetail>();
        List<OneDataDetail> inTemp03List = new ArrayList<OneDataDetail>();
        List<OneDataDetail> outTemp04List = new ArrayList<OneDataDetail>();

        for (DeviceMessage deviceMessage:deviceMessageList
                ) {
            deviceParameterTime.add(deviceMessage.getsSendTime());

            OneDataDetail oneDataDetailInTemp1 = new OneDataDetail();
            oneDataDetailInTemp1.setName(deviceMessage.getsSendTime());
            oneDataDetailInTemp1.setValue(String.valueOf(deviceMessage.geteC01InTemp1()));

            OneDataDetail oneDataDetailInTemp2 = new OneDataDetail();
            oneDataDetailInTemp2.setName(deviceMessage.getsSendTime());
            oneDataDetailInTemp2.setValue(String.valueOf(deviceMessage.geteC01InTemp2()));

            OneDataDetail oneDataDetailInTemp3 = new OneDataDetail();
            oneDataDetailInTemp3.setName(deviceMessage.getsSendTime());
            oneDataDetailInTemp3.setValue(String.valueOf(deviceMessage.geteC01InTemp3()));

            OneDataDetail oneDataDetailOutTemp4 = new OneDataDetail();
            oneDataDetailOutTemp4.setName(deviceMessage.getsSendTime());
            oneDataDetailOutTemp4.setValue(String.valueOf(deviceMessage.geteC01OutTemp4()));
            inTemp01List.add(oneDataDetailInTemp1);
            inTemp02List.add(oneDataDetailInTemp2);
            inTemp03List.add(oneDataDetailInTemp3);
            outTemp04List.add(oneDataDetailOutTemp4);
        }

        parameterInTemp1.setData(inTemp01List);
        parameterInTemp2.setData(inTemp02List);
        parameterInTemp3.setData(inTemp03List);
        parameterOutTemp4.setData(outTemp04List);

        parameterDataList.add(parameterInTemp1);
        parameterDataList.add(parameterInTemp2);
        parameterDataList.add(parameterInTemp3);
        parameterDataList.add(parameterOutTemp4);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setdName(deviceMessageList.get(0).getdName());
        deviceInfo.setdSerialNum(deviceMessageList.get(0).getDeviceSerialNum());
        this.deviceInfo = deviceInfo;
        this.chartsParameters01 = new ChartsParameters01(deviceParameterName,parameterDataList,deviceParameterTime);
    }
}


