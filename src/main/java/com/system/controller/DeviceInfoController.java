package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.exception.CustomException;
import com.system.po.DeviceInfo;
import com.system.po.PagingVO;
import com.system.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/deviceInfo")
public class DeviceInfoController {
    @Autowired
    private DeviceInfoService deviceInfoService;
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<设备信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @RequestMapping(value="/DeviceInfo",method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String loadDevice() throws Exception{
        List<DeviceInfo> deviceInfoList = deviceInfoService.selectDeviceInfo();
        String jsonString = JSON.toJSONString(deviceInfoList);
        return jsonString;
    }

    // 修改设备信息页面显示
    @RequestMapping(value = "/editDeviceInfo", method = {RequestMethod.GET})
    public String editDeviceInfoUI(String dSerialNum, Model model) throws Exception {
        if (dSerialNum == null) {
            //加入没有带设备id就进来的话就返回设备显示页面
            return "redirect:/admin/showDeviceInfo";
        }
        DeviceInfo deviceInfo =  deviceInfoService.findBySerialNum(dSerialNum);
        if (deviceInfo == null) {
            throw new CustomException("未找到该设备");
        }

        model.addAttribute("deviceInfo", deviceInfo);

        return "admin/editDeviceInfo";
    }

    // 修改设备信息处理
    @RequestMapping(value = "/editDeviceInfo", method = {RequestMethod.POST})
    public String editStudent(DeviceInfo deviceInfo) throws Exception {

        deviceInfoService.updateBySerialNum(deviceInfo.getdSerialNum(), deviceInfo);

        //重定向
        return "redirect:/admin/showDeviceInfo";
    }


}
