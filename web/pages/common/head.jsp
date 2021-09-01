<%--
  Created by IntelliJ IDEA.
  User: renhongchang
  Date: 2021/8/28
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*动态获取完整的工程路径*/
    String BasePath = request.getScheme()   //获取协议名称
            + "://"
            + request.getServerName()   //获取ip
            + ":"
            + request.getServerPort()   //获取端口号
            + request.getContextPath()  //获取工程路径
            + "/";
    pageContext.setAttribute("basePath",BasePath);
%>
<!--	base标签，永远固定相对路径跳转的结果-->
<base href="<%=BasePath%>">

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>