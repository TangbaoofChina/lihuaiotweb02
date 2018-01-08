package com.system.service.impl;

import com.system.mapper.DeviceInfoMapper;
import com.system.mapper.DeviceMessageMapper;
import com.system.po.*;
import com.system.po.parameter.ParameterData;
import com.system.service.DeviceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceMessageServiceImpl implements DeviceMessageService {

    @Autowired
    private DeviceMessageMapper deviceMessageMapper;
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    //************************************基本数据********************************************//
    @Override
    public Integer getCountDevice() throws Exception{
        return deviceMessageMapper.getCountDevice();
    }
    //************************************实时数据********************************************//
    @Override
    public List<DeviceMessage> selectRealDeviceMessage() throws Exception {
        return deviceMessageMapper.selectRealDeviceMessage();
    }

    @Override
    public List<DeviceMessage> selectRealDeviceMsgByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setMessageCount();

        List<DeviceMessage> list = deviceMessageMapper.selectRealDeviceMsgByPaging(pagingVO);
        return list;
    }

    @Override
    public List<DeviceMessage> selectRealDataByName(String sDeviceName) throws Exception {
        List<DeviceMessage> list = deviceMessageMapper.selectRealDeviceMsgByName(sDeviceName);
        return list;
    }

    //************************************历史数据********************************************//

    /**
     * 查询单个设备历史数据
     * @param sDeviceId 设备ID
     * @param sStartDate 起始时间
     * @param sEndDate 截止时间
     * @return 单个设备列表
     * @throws Exception
     */
    @Override
    public List<DeviceMessage> selectHisDeviceMessageByTimeAndId(String sDeviceId, String sStartDate, String sEndDate) throws Exception {
        return  deviceMessageMapper.selectHisDevMsgByTimeAndID(sDeviceId,sStartDate,sEndDate);
    }

    /**
     * 查询多个设备历史数据-曲线格式
     * @param sDeviceIds 设备ID 数组
     * @param sQueryParam 设备参数
     * @param sStartDate 起始时间
     * @param sEndDate 截止时间
     * @return 曲线历史数据
     * @throws Exception
     */
    @Override
    public ParameterCharts selectHisDevMsgByTimeAndIDs(String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception {
        List<DeviceMessage> deviceMessageList = deviceMessageMapper.selectHisDevMsgByTimeAndIDs(sDeviceIds,sStartDate,sEndDate);
        ParameterCharts returnParamChats = getParameterChartsByDeviceMessageList(deviceMessageList,sDeviceIds,sQueryParam);
        return returnParamChats;
    }

    /**
     * 查询多个设备历史数据-报表格式
     * @param deviceInfoList 设备信息List
     * @param sDeviceIds 设备ID数组
     * @param sQueryParam 设备参数
     * @param sStartDate 起始时间
     * @param sEndDate  截止时间
     * @return 报表历史数据
     * @throws Exception
     */
    @Override
    public String selectHisDevMsgByTimeAndIDs(List<DeviceInfo> deviceInfoList, String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception {

        ParameterCharts parameterCharts = this.selectHisDevMsgByTimeAndIDs(sDeviceIds, sQueryParam, sStartDate, sEndDate);
        if (parameterCharts==null)
        {
            return "[]";
        }
        List<List<OneDataDetail>> dataDetailList = getDataDetailList(deviceInfoList,parameterCharts);
        String sReturnJson = formatDataDetailsToJson(dataDetailList);
        //return dataDetailList;
        return sReturnJson;
    }

    /**
     * 查询多个设备历史数据-报表格式;分页
     * @param pageNumber 分页码
     * @param pageSize  页数据长度
     * @param deviceInfoList 设备信息List
     * @param sDeviceIds 设备ID数组
     * @param sQueryParam 设备参数
     * @param sStartDate 起始时间
     * @param sEndDate 截止时间
     * @return 报表历史数据;分页
     * @throws Exception
     */
    @Override
    public DataTablePageing selectHisDevMsgByTimeAndIDsAndPage(Integer pageNumber, Integer pageSize, List<DeviceInfo> deviceInfoList, String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception {
        DataTablePageing dataTablePageing = new DataTablePageing();
        ParameterCharts parameterCharts = this.selectHisDevMsgByTimeAndIDs(sDeviceIds, sQueryParam, sStartDate, sEndDate);
        if (parameterCharts==null)
        {
            return dataTablePageing;
        }
        Integer bigIndex = 0;
        Integer smallIndex = 0;
        smallIndex = pageNumber-1;
        bigIndex = smallIndex + pageSize;
        List<List<OneDataDetail>> dataDetailList = getDataDetailList(deviceInfoList,parameterCharts);
        //截取部分字符串
        List<List<OneDataDetail>> dataDetailListSub = new ArrayList<List<OneDataDetail>>();
        if(bigIndex > dataDetailList.size())
            bigIndex = dataDetailList.size();
        dataDetailListSub.addAll(dataDetailList.subList(smallIndex,bigIndex));
        String sReturnJson = formatDataDetailsToJson(dataDetailListSub);
        dataTablePageing.setTotal(dataDetailList.size());
        dataTablePageing.setsReturnJson(sReturnJson);
        //return dataDetailList;
        return dataTablePageing;
    }

    //************************************私有函数********************************************//
    //根据数据生成JSON字符串，返回到datatable显示
    private static String formatDataDetailsToJson(List<List<OneDataDetail>> dataDetailList) throws Exception
    {
        String sHead = "[";
        String sEnd = "]";
        String sOneHead = "{";
        String sOneEnd = "}";
        String sReturnJson = sHead;
        for (List<OneDataDetail> oneDataDetailList:dataDetailList
                ) {
            String sOneLine = sOneHead;
            for (OneDataDetail oneDataDetail:oneDataDetailList
                    ) {
                sOneLine += "\""+oneDataDetail.getName()+"\"";
                sOneLine += ":";
                sOneLine += "\"" + oneDataDetail.getValue()+"\"";
                sOneLine +=",";
            }
            sOneLine = sOneLine.substring(0,sOneLine.length()-1);
            sOneLine += sOneEnd;
            sReturnJson += sOneLine +",";
        }
        sReturnJson = sReturnJson.substring(0,sReturnJson.length()-1);
        sReturnJson+= sEnd;
        return sReturnJson;
    }

    //根据曲线的查询结果，转换成key/value形式，为datatable服务
    private List<List<OneDataDetail>> getDataDetailList( List<DeviceInfo> deviceInfoList,ParameterCharts parameterCharts)
    {
        List<List<OneDataDetail>> dataDetailList = new ArrayList<List<OneDataDetail>>();
        List<String> sTimeList = parameterCharts.getChartsParameters().getdParameterTime();
        //以时间为基准，先填一行，再增加行
        for (int i=0;i<sTimeList.size();i++)
        {
            String sTime = sTimeList.get(i);
            List<OneDataDetail> dataDetails = new ArrayList<OneDataDetail>();
            //循环设备列表
            for (DeviceInfo deviceInfo : deviceInfoList
                    ) {
                OneDataDetail oneDataDetail = new OneDataDetail();
                //找到名称--填入设备序列号
                oneDataDetail.setName(deviceInfo.getdSerialNum());
                //找到值(循环曲线 曲线中的某一条曲线中的值)
                for (ParameterData pD:parameterCharts.getChartsParameters().getdParameterdata()
                        ) {
                    // 判断设备名称是否相同
                    if(pD.getName().equals(deviceInfo.getdName()))
                    {
                        //读取跟上面的时间相同位置的值
                        oneDataDetail.setValue(pD.getData().get(i));
                    }
                }
                dataDetails.add(oneDataDetail);
            }
            OneDataDetail oneDataDetailTime = new OneDataDetail();
            oneDataDetailTime.setName("sSendTime");
            oneDataDetailTime.setValue(sTime);
            dataDetails.add(oneDataDetailTime);
            dataDetailList.add(dataDetails);
        }
        return dataDetailList;
    }

    //根据设备信息，设备ID数组，查询参数，合成曲线格式的对象
    private ParameterCharts getParameterChartsByDeviceMessageList(List<DeviceMessage> deviceMessageList,String[] sDeviceIds,String sQueryParam)
    {
        List<DeviceCharts01> deviceChartsList = new ArrayList<DeviceCharts01>();
        //将查询的数据放置到设备历史数据类
        ParameterCharts returnParamChats = null;
        if (deviceMessageList.size()>0) {
            for (String sOneDeviceId : sDeviceIds
                    ) {
                List<DeviceMessage> deviceMessageList1 = new ArrayList<DeviceMessage>();
                //遍历每一行，放置到对应的设备信息类中
                for (DeviceMessage deviceMessage : deviceMessageList
                        ) {
                    if (deviceMessage.getDeviceSerialNum().equals(sOneDeviceId)) {
                        deviceMessageList1.add(deviceMessage);
                    }
                }
                if (deviceMessageList1.size() ==0)
                {
                    DeviceMessage deviceMessage = new DeviceMessage();
                    DeviceInfo deviceInfo = deviceInfoMapper.selectDeviceInfoById(sOneDeviceId);
                    deviceMessage.setDeviceSerialNum(deviceInfo.getdSerialNum());
                    deviceMessage.setdName(deviceInfo.getdName());
                    deviceMessageList1.add(deviceMessage);
                }
                DeviceCharts01 deviceCharts = new DeviceCharts01(deviceMessageList1);
                deviceChartsList.add(deviceCharts);
            }
            returnParamChats = new ParameterCharts(deviceChartsList, sQueryParam, deviceMessageList);
        }
        return returnParamChats;
    }
}
