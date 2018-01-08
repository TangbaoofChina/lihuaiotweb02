<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/4
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-7">

        <div class="col-md-12">
            <label for="inputFather" class="col-sm-2 control-label">父节点</label>
            <div class="col-sm-10">
                <input type="text" id="inputFatherId" style="display: none">
                <input type="text" class="form-control" name="inputFather" id="inputFather"
                       placeholder="请选择父节点">
            </div>
        </div>
        <div class="col-md-12" style="text-align: center">
            <button class="btn btn-default" onclick="removeSelectNode()">删除节点</button>
            <button class="btn btn-default" onclick="renameSelectNode()">重命名节点</button>
        </div>
        <div class="col-md-12">
            <label for="inputSon" class="col-sm-2 control-label">子节点</label>
            <div class="col-sm-10">
                <input type="text" name="inputSon" class="form-control" id="inputSon"
                       placeholder="请输入子节点">
            </div>
        </div>
        <div class="col-md-12" style="text-align: center">
            <button class="btn btn-default" onclick="addNewChildNode()">新增子节点</button>
        </div>
</div>