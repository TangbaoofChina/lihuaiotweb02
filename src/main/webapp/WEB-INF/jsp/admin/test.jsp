<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/3
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-7">
    <form name="reset" class="form-horizontal" role="form" action="/lihuaiotweb01/passwordRest"
          id="editfrom" method="post" onsubmit="return check()">
        <div class="form-group">
            <label for="inputFather" class="col-sm-2 control-label">父节点</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="inputFather" id="inputFather"
                       placeholder="请选择父节点" disabled="disabled">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSon" class="col-sm-2 control-label">子节点</label>
            <div class="col-sm-10">
                <input type="text" name="inputSon" class="form-control" id="inputSon"
                       placeholder="请输入子节点">
            </div>
        </div>
        <div class="form-group" style="text-align: center">
            <button class="btn btn-default" type="submit">提交</button>
            <button class="btn btn-default">重置</button>
        </div>
    </form>
</div>
