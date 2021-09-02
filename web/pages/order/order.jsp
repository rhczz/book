<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含，base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%--	静态包含，登陆成功之后的菜单		--%>
			<%@ include file="/pages/common/login_success_memu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>时间</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">

				<tr>
					<td style="width: 120px">${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:if test="${order.status == 0}">
							未发货
						</c:if>
						<c:if test="${order.status == 1}">
							已发货
						</c:if>
						<c:if test="${order.status == 2}">
							已签收
						</c:if>
					</td>
					<td><a href="order?action=orderDetails&id=${order.orderId}">查看详情</a></td>
					<c:if test="${order.status == 1}">
						<td><a href="order?action=receiveOrder&id=${order.orderId}">确认收货</a></td>
					</c:if>
				</tr>
			</c:forEach>


		</table>
		
	
	</div>

	<%--静态包含，页脚部分--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>