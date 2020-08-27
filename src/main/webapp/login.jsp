<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="regex" class="com.epam.afshop.regex.RegularExpressionConstant"/>
<html>
<head>
    <title>Login</title>
    <jsp:include page="style.jsp"/>
</head>
<body>
    <div class="container">
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <form action="${pageContext.request.contextPath}/controller/login" method="post" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="inputEmail">Email address</label>
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="example@mail.com" required pattern="${regex.getEmailRegex()}" aria-describedby="emailHelp">
                <div class="invalid-feedback">
                    Please input correct email.
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" name="password" id="inputPassword" required>
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
