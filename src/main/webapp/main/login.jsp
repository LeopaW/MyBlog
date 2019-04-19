<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Leopa
  Date: 2019/4/15
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${ctx}/static/plugins/layui/css/layui.css">
    <style>
        form{
            width: 300px;
            margin: auto;
            margin-top: 10%;
        }
    </style>
</head>
<body>
<div class="layui-container" >


    <form action="${ctx}/user/login"class="layui-form">
        <c:if test="${!empty error}">
            <div style="color: red;">${error}</div>
        </c:if>

        <div class="layui-form-item">
            <%--<label class="layui-form-label">输入框</label>--%>
            <div class="layui-input-inline">
                <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" >
            </div>
        </div>
        <div class="layui-form-item">
            <%--<label class="layui-form-label">密码框</label>--%>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" >
            </div>
            <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                <button type="reset" class="layui-btn layui-btn-primary">重填</button>
            </div>
        </div>
    </form>
</div>

<%--js--%>
<script src="${ctx}/static/plugins/layui/layui.js"></script>
</body>
</html>
