<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XX论坛登录</title>
</head>
<body>
    <c:if test="${!empty error}">
        <font color="red"><c:out value="${error}" /></font>
    </c:if>

    <form action="<c:url value="/loginCheck.html"/> " method="post">
        用户名：<input type="text" name="userName"> <br>
        密码：<input type="password" name="password"> <br>
        <input type="submit" name="login">
        <input type="reset" name="reset">


    </form>

</body>
</html>
