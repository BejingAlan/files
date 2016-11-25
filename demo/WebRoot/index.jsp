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
  
  <body> <!--文件上传必须使用Post 提交，设置enctype(编码类型)="multipart/form-data" -->
   	<form action="/demo/fileUploadServlet" method="post" enctype="multipart/form-data" >
   	上传者：<input type="text"  name="username" /><br>
   	上传文件：<input type="file"  name="name" /><br>
   	<input type="submit"  value="上传" />
   	
   	
   	
   	</form>
   	
  </body>
</html>
