package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.exception.CustomException;
import com.system.po.DeviceInfo;
import com.system.po.DeviceMessage;
import com.system.po.PagingVO;
import com.system.po.Userlogin;
import com.system.service.DeviceInfoService;
import com.system.service.DeviceMessageService;
import com.system.service.UserloginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Jacey on 2017/6/30.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "deviceMessageServiceImpl")
    private DeviceMessageService deviceMessageService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "deviceInfoServiceImpl")
    private DeviceInfoService deviceInfoService;
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<设备信息操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  设备连接信息显示
    @RequestMapping(value="/showDeviceInfo")
    public String showDeviceInfo(Model model, Integer page) throws Exception{
        List<DeviceInfo> deviceList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(deviceInfoService.getCountDevice());
        //pagingVO.setPageSize(10);
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            deviceList = deviceInfoService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            deviceList = deviceInfoService.findByPaging(page);
        }

        model.addAttribute("deviceList", deviceList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showDeviceInfo";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<实时数据操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  分页实时数据显示
    @RequestMapping("/showRealData01")
    public String showRealData(Model model, Integer page) throws Exception {

        List<DeviceMessage> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(deviceMessageService.getCountDevice());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = deviceMessageService.selectRealDeviceMsgByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = deviceMessageService.selectRealDeviceMsgByPaging(page);
        }

        model.addAttribute("deviceList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showRealData01";
    }

    //  全部实时数据显示
    @RequestMapping("/showRealData")
    public String showRealData() throws Exception {

        return "admin/showRealData";
    }

    //  实时数据显示 通过名称检索
    @RequestMapping(value = "selectRealDataByName", method = {RequestMethod.POST})
    private String selectRealDataByName(String findByName, Model model) throws Exception {

        List<DeviceMessage> list = deviceMessageService.selectRealDataByName(findByName);

        model.addAttribute("deviceList", list);

        return "admin/showRealData";
    }

    //  实时数据显示 通过名称检索
    @RequestMapping(value = "selectRealDataTable", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String selectRealDataTable() throws Exception {

        List<DeviceMessage> list = deviceMessageService.selectRealDeviceMessage();

        String jsonString = JSON.toJSONString(list);

        return jsonString;
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<历史数据操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  历史数据显示
    @RequestMapping("/showHistoryData")
    public String showHistoryData() throws Exception {
        return "admin/showHistoryData";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<历史曲线操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  历史数据显示
    @RequestMapping("/showHistoryCharts")
    public String showHistoryCharts() throws Exception {
        return "admin/showHistoryCharts";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<曲线图表操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  历史数据显示
    @RequestMapping("/showHistoryChartsTable")
    public String showHistoryChartsTable() throws Exception {
        return "admin/showHistoryChartsTable";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<曲线图表操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  报表分页显示
    @RequestMapping("/showHistoryTablePage")
    public String showHistoryTablePage() throws Exception {
        return "admin/showHistoryTablePage";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<树形列表操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  树形列表显示
    @RequestMapping("/showZTree")
    public String showZTree() throws Exception {
        return "admin/showZTree";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<设备组织操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  树形列表显示
    @RequestMapping("/showDeviceUnionORG")
    public String showDeviceUnionORG() throws Exception {
        return "admin/showDeviceUnionORG";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<人员组织操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //  树形列表显示
    @RequestMapping("/showPeopleUnionORG")
    public String showPeopleUnionORG() throws Exception {
        return "admin/showPeopleUnionORG";

    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 普通用户账号密码重置
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    // 普通用户账号密码重置处理
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getUsername());

        if (u != null) {
            if (u.getRole() == 0) {
                throw new CustomException("该账户为管理员账户，没法修改");
            }
            u.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), u);
        } else {
            throw new CustomException("没找到该用户");
        }

        return "admin/userPasswordRest";
    }

    // 本账户密码重置
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }


}
