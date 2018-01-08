package com.system.po;

public class DeviceMessage {
    private String deviceSerialNum;
    private String dName;
    private float eC01InTemp1;
    private float eC01InTemp2;
    private float eC01InTemp3;
    private float eC01OutTemp4;
    private String sSendTime;
    private String sRecevieTime;

    public String getDeviceSerialNum() {
        return deviceSerialNum;
    }

    public void setDeviceSerialNum(String deviceSerialNum) {
        this.deviceSerialNum = deviceSerialNum;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public float geteC01InTemp1() {
        return eC01InTemp1;
    }

    public void seteC01InTemp1(float eC01InTemp1) {
        this.eC01InTemp1 = eC01InTemp1;
    }

    public float geteC01InTemp2() {
        return eC01InTemp2;
    }

    public void seteC01InTemp2(float eC01InTemp2) {
        this.eC01InTemp2 = eC01InTemp2;
    }

    public float geteC01InTemp3() {
        return eC01InTemp3;
    }

    public void seteC01InTemp3(float eC01InTemp3) {
        this.eC01InTemp3 = eC01InTemp3;
    }

    public float geteC01OutTemp4() {
        return eC01OutTemp4;
    }

    public void seteC01OutTemp4(float eC01OutTemp4) {
        this.eC01OutTemp4 = eC01OutTemp4;
    }

    public String getsSendTime() {
        return sSendTime;
    }

    public void setsSendTime(String sSendTime) {
        this.sSendTime = sSendTime;
    }

    public String getsRecevieTime() {
        return sRecevieTime;
    }

    public void setsRecevieTime(String sRecevieTime) {
        this.sRecevieTime = sRecevieTime;
    }
}
