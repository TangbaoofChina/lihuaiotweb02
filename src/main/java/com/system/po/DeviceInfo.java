package com.system.po;

public class DeviceInfo {
    private String dSerialNum;
    private String dName = "";
    private String dIp="";
    private String dPort="";
    private String dProtocol = "";
    private String dSendTime = "";
    private String dReceiveTime = "";
    private String dState = "";

    public String getdSerialNum() {
        return dSerialNum;
    }

    public void setdSerialNum(String dSerialNum) {
        this.dSerialNum = dSerialNum;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdIp() {
        return dIp;
    }

    public void setdIp(String dIp) {
        this.dIp = dIp;
    }

    public String getdPort() {
        return dPort;
    }

    public void setdPort(String dPort) {
        this.dPort = dPort;
    }

    public String getdProtocol() {
        return dProtocol;
    }

    public void setdProtocol(String dProtocol) {
        this.dProtocol = dProtocol;
    }

    public String getdSendTime() {
        return dSendTime;
    }

    public void setdSendTime(String dSendTime) {
        this.dSendTime = dSendTime;
    }

    public String getdReceiveTime() {
        return dReceiveTime;
    }

    public void setdReceiveTime(String dReceiveTime) {
        this.dReceiveTime = dReceiveTime;
    }

    public String getdState() {
        return dState;
    }

    public void setdState(String dState) {
        this.dState = dState;
    }
}
