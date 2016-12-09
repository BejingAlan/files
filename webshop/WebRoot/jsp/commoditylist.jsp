<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>键鼠特卖</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/commoditylist.css">
<link rel="stylesheet" type="text/css"
	href="css/commoditylist_daohang.css">

</head>
<body>

	<!-- 头部包含登陆注册 -->
	<div id="top" class="top">
		<ul>
			<li><a href="jsp/login.jsp"
				style="border-right: 1px solid #333;"><c:if
						test=" ${empty user}">登录</c:if> </a> <a href="jsp/register.jsp"><c:if
						test=" ${empty user}">注册</c:if> </a> <c:if test="${not empty user }">
					欢迎您,${user.username }用户
				</c:if></li>
		</ul>
	</div>


	<div id="topimg">
		<img alt="top" src="images/logo.png">
	</div>
	<center>
		<div id="title">
			<h1>键鼠特卖官网</h1>
		</div>
	</center>


	<div>
		<div id="daohang" class="daohang">
			<ul id="dao_list">
				<li id="li_sy"><a href="#">鼠标</a></li>
				<li><a href="#">游戏鼠标垫</a>
				<li><a href="#">音频</a>
				<li><a href="#">周边配件</a>
				<li><a href="#">笔记本电脑</a></li>
			</ul>
		</div>

		<center>
			<div class="product-div">
				<ul class="product-list">
					<c:forEach items="${listProduct}" var="c">

						<li><a
							href="<c:url value='/ProductServlet?method=4&productid=${c.id}' />"
							target="_blank"> <img src="${c.imgurl}">
								<div class="price-box">
									<h4 color="#00A000">${c.name }</h4>

									<span style="display:none;">${c.price }</span> <span
										class="discount">￥${c.price }</span>

								</div> </a>
						</li>

					</c:forEach>
				</ul>
			</div>
		</center>
</body>
</html>
