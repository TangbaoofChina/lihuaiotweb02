package com.system.po.parameter;

public class ParameterBase {
    private String name;
    private String type="line";
    private String connectNulls="true";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConnectNulls() {
        return connectNulls;
    }

    public void setConnectNulls(String connectNulls) {
        this.connectNulls = connectNulls;
    }
}
