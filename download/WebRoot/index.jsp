<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <h1>文件下载</h1>
    <a href="/download/DowloadServlet?filename=1.jpg">1.jpg</a>
    <a href="/download/DowloadServlet?filename=cs.stx">cs.stx</a>
    <a href="/download/DowloadServlet?filename=entities_u.txt">entities_u.txt</a>
    <a href="/download/DowloadServlet?filename=editplus.exe">editplus.exe</a>
    <a href="/download/DowloadServlet?filename=我不知道.txt">我不知道.txt</a>
    
  </body>
</html>
