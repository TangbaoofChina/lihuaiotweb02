package com.system.service.impl;

import com.system.mapper.DeviceInfoMapper;
import com.system.mapper01.DeviceInfoMapper01;
import com.system.po.DeviceInfo;
import com.system.po.PagingVO;
import com.system.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private DeviceInfoMapper01 deviceInfoMapper01;

    @Override
    public List<DeviceInfo> selectDeviceInfo() throws Exception {
        return deviceInfoMapper.selectDeviceInfo();
    }

    @Override
    public List<DeviceInfo> selectDeviceInfoByIDs(String[] sDeviceIds) throws Exception {
        return deviceInfoMapper.selectDeviceInfoByIDs(sDeviceIds);
    }

    @Override
    public Integer getCountDevice() throws Exception {
        //尝试访问另外一个数据库
        //Integer a = deviceInfoMapper01.getCountStudent();
        return deviceInfoMapper.getCountDevice();
    }

    @Override
    public List<DeviceInfo> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        pagingVO.setMessageCount();

        List<DeviceInfo> list = deviceInfoMapper.selectDeviceInfoByPaging(pagingVO);
        for (DeviceInfo deviceInfo:list
             ) {

            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String toDate = simpleFormat.format(new Date());
            long from =  simpleFormat.parse(deviceInfo.getdReceiveTime()).getTime();
            long to = simpleFormat.parse(toDate).getTime();
            int minutes = (int) ((to - from) / (1000 * 60));
            if (minutes > 15)
                deviceInfo.setdState("离线");
            else
                deviceInfo.setdState("在线");
        }
        return list;
    }

    @Override
    public DeviceInfo findBySerialNum(String sSerialNum) throws Exception {
        return deviceInfoMapper.selectDeviceInfoById(sSerialNum);
    }

    @Override
    public void updateBySerialNum(String sSerialNum, DeviceInfo deviceInfo) throws Exception {
        deviceInfoMapper.updateDeviceByPrimaryKey(deviceInfo);
    }

    @Override
    public int updateDeviceInfoByNodeId(String sSerialNum, String sNodeId) throws Exception {
        return deviceInfoMapper.updateDeviceInfoByNodeId(sSerialNum,sNodeId);
    }
}
