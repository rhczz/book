<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含，base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteItem").click(function (){
				/*给删除加确定*/
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() +"】吗");
			});
			$("#clear").click(function (){
				let b = confirm("你确定要清空购物车吗！");
				if (b) {
					return confirm("真的确定好了吗😁😁😁");
				}else {
					return false;
				}
			});
			/*给Count绑定输入框改变事件*/
			$(".updateCount").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				var bookId = $(this).attr("chanCountId");
				if (confirm("你确定要将【"+ name +"】的数量修改为【"+ count +"】吗")) {

					location.href="cart?action=updateCount&count="+count+"&id="+bookId;
				}else {
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--	静态包含，登陆成功之后的菜单		--%>
			<%@ include file="/pages/common/login_success_memu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>		
			<c:forEach items="${sessionScope.cart.cartItems}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input chanCountId="${entry.value.id}" class="updateCount" style="width: 40px" type="text" name="count" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="cart?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>

			
		</table>

		<c:if test="${empty sessionScope.cart.cartItems}">
			<div style="width: 100px;height: 50px;margin: 100px auto;">

				<h2>空空如也</h2>
			</div>
		</c:if>
		<c:if test="${not empty sessionScope.cart.cartItems}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clear" href="cart?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="order?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%--静态包含，页脚部分--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>