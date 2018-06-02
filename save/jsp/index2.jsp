<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <h2>Struts2-Demo</h2>
    <a href="user_login_go">去登录界面</a>
    <h1>展示信息</h1>
    <s:form action="showNews" namespace="/" method="post">
    <s:submit value="Go"></s:submit>
    </s:form>
</body>
</html>

