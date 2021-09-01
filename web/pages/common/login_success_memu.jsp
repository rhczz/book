<%--
  Created by IntelliJ IDEA.
  User: renhongchang
  Date: 2021/8/28
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
    <a href="page/order/order.jsp">我的订单</a>
    <a href="user?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
