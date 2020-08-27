<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="regex" class="com.epam.afshop.regex.RegularExpressionConstant"/>
<html>
<head>
    <jsp:include page="style.jsp"/>
    <title>Register</title>
</head>
<body>
    <div class="container">
        <jsp:include page="header.jsp"/>
        <form action="${pageContext.request.contextPath}/controller/register" method="post" class="needs-validation" novalidate>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Mark" required>
                    <div class="invalid-feedback">
                        First name is required
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Otto" required>
                    <div class="invalid-feedback">
                        Last name is required
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="email">Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="email" name="email" aria-describedby="inputGroupPrepend" placeholder="mark@otto.com" required pattern="${regex.getEmailRegex()}">
                        <div class="invalid-feedback">
                            Please enter a correct email.
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="phoneNumber">Phone number</label>
                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" required pattern="${regex.getPhoneRegex()}">
                    <div class="invalid-feedback">
                        Please provide a valid phone number.
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required pattern="${regex.getPasswordRegex()}" >
                    <div class="invalid-feedback">
                        Passwords must contain at least six characters, including uppercase and/or lowercase letters and numbers.
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="confirmPassword">Confirm password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <div class="invalid-feedback" id = "invalid-feedback">
                        Password mismatch!
                    </div>
                </div>
                <script>
                    var password = document.getElementById("validationCustom04")
                        , confirm_password = document.getElementById("validationCustom05");

                    function validatePassword(){
                        if(password.value != confirm_password.value) {
                            confirm_password.setCustomValidity("Passwords Don't Match");
                        } else {
                            confirm_password.setCustomValidity('');
                        }
                    }

                    password.onchange = validatePassword;
                    confirm_password.onkeyup = validatePassword;
                </script>
            </div>
            <button class="btn btn-primary" type="submit">Register</button>
        </form>

    </div>
</body>
</html>
