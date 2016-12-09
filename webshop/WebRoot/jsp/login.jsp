<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>登陆界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/login.css">


</head>

<body>
	<div id="top" class="top">
		<ul>
			<li><a href="login.jsp" style="border-right: 1px solid #333;">登陆</a>
				<a href="jsp/register.jsp">注册</a>
			</li>
		</ul>
	</div>


	<center>

		<div class="login_right">
			<div class="login_cont">
				<div class="title">
					<h3>欢迎登录键鼠特卖商城</h3>
				</div>
				<form name="formLogin" action="<%=path%>/UserServlet" method="post">
					<input type="hidden" name="method" value="2" />
					<div class="input clearfix">
						<div id="logintip"></div>
						<div class="yhm" style="margin-top:0px;">
							<p>用户名</p>
							<input name="username" type="text" size="25" class="inputBg" value="${user.username }">
						</div>
						<div class="mm">
							<p>密码</p>
							<input name="password" type="password" size="15" class="inputBg" >
						</div>
						<div class="cap">
							<p>验证码</p>
							<input type="text" size="8" name="yzm" class="inputBg"
								style="width:240px;"> <img src="/webshop/CheckServlet"
								onclick="this.src=this.src+'?'">
									<span id="conform_password_notice">${msg}</span>
						</div>
						<div class="jizhu">
							<p>
								<input type="checkbox" value="1" name="remember" id="remember">
								记住密码
							</p>
							<span><a href="#">忘记密码？</a> </span>
						</div>
						<div class="denglu">
							<input type="hidden" name="act" value="act_login"> <input
								type="hidden" name="back_act"
								value="https://cn.store.razerzone.com/">
							<button type="submit" name="submit">
								登录&nbsp;<a href="#" style="color:#0f0;">&gt;</a>
							</button>
							<p>
								没账号？<a href="jsp/register.jsp">免费注册</a>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</center>
</body>
</html>
