package com.system.po.parameter;

import com.system.po.OneDataDetail;

import java.util.List;

public class ParameterData01 extends ParameterBase {

    private List<OneDataDetail> data;

    public List<OneDataDetail> getData() {
        return data;
    }

    public void setData(List<OneDataDetail> data) {
        this.data = data;
    }
}
