package com.system.po.parameter;

import com.system.po.parameter.ParameterData;

import java.util.List;

public class ChartsParameters {
    private List<ParameterData> dParameterdata;
    private List<String> dParameterTime;
    private List<String> dParameterName;

    public List<ParameterData> getdParameterdata() {
        return dParameterdata;
    }

    public void setdParameterdata(List<ParameterData> dParameterdata) {
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

    public ChartsParameters(List<String> deviceParameterName,List<ParameterData> parameterDataList,List<String> deviceParameterTime)
    {
        this.dParameterdata = parameterDataList;
        this.dParameterName = deviceParameterName;
        this.dParameterTime = deviceParameterTime;
    }
}
