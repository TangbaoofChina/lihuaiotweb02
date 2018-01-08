package com.system.po;

public class DataTablePageing {
    private Integer total = 0;
    private String sReturnJson = "";

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getsReturnJson() {
        return sReturnJson;
    }

    public void setsReturnJson(String sReturnJson) {
        this.sReturnJson = sReturnJson;
    }
}
