package com.system.po.parameter;

import com.system.po.parameter.ParameterData01;

import java.util.List;

public class ChartsParameters01 {
    private List<ParameterData01> dParameterdata;
    private List<String> dParameterTime;
    private List<String> dParameterName;

    public List<ParameterData01> getdParameterdata() {
        return dParameterdata;
    }

    public void setdParameterdata(List<ParameterData01> dParameterdata) {
        this.dParameterdata = dParameterdata;
    }

    public List<String> getdParameterTime() {
        return dParameterTime;
    }

    public void setdParameterTime(List<String> dParameterTime) {
        this.dParameterTime = dParameterTime;
    }

    public List<String> getdParameterName() {
        return dParameterName;
    }

    public void setdParameterName(List<String> dParameterName) {
        this.dParameterName = dParameterName;
    }

    public ChartsParameters01(List<String> deviceParameterName,List<ParameterData01> parameterDataList,List<String> deviceParameterTime)
    {
        this.dParameterdata = parameterDataList;
        this.dParameterName = deviceParameterName;
        this.dParameterTime = deviceParameterTime;
    }
}
