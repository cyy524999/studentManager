<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
欢迎来到学生管理系统
<form action="login.do" method="post"><!--action指form表单需要调用的后台路径  -->
   用户名:<input name ="username"><br>
    密码:<input name ="password"><br>
   <input type="submit"value="提交">
</form>
还没有账号？<a href="register.jsp">请立即注册</a>   <!--超链接-->
</body>
</html>