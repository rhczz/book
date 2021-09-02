<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含，base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%--静态包含，manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="order?action=orderDetails&id=${order.orderId}">查看详情</a></td>
					<c:if test="${order.status == 0}">
						<td><a href="order?action=sendOrder&id=${order.orderId}">点击发货</a></td>
					</c:if>
					<c:if test="${order.status == 1}">
						<td>已发货</td>
					</c:if>
					<c:if test="${order.status == 2}">
						<td>已收货</td>
					</c:if>
				</tr>
			</c:forEach>


		</table>
	</div>

	<%--静态包含，页脚部分--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>