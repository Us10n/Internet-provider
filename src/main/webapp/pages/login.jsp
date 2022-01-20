<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Log in</title>
</head>

<body class="text-center page-content">

<div class="container-fluid py-5" style="width: 500px">
    <main class="form-signin mx-2">
        <div class="py-5 text-center">
            <h2><fmt:message key="lang.log.in.form"/></h2>
        </div>
        <form data-bitwarden-watching=" 1" action="${pageContext.request.contextPath}/controller?command=loginUser"
              method="post">
            <h1 class="h3 mb-3 fw-normal"></h1>

            <div class="form-floating">
                <input type="email" name="email" class="form-control" id="floatingInput"
                       placeholder="name@example.com"
                       required="required">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword"
                       placeholder="Password" required minlength="10" maxlength="20">
                <label for="floatingPassword">Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-info" type="submit">Sign in</button>

            <c:choose>
                <c:when test="${banError eq 'true'}">
                    <div class="alert alert-danger fade show " role="alert">
                        <fmt:message key="lang.ban.error"/>
                    </div>
                </c:when>
                <c:when test="${loginError eq 'true'}">
                    <div class="alert alert-danger fade show " role="alert">
                        <fmt:message key="lang.login.error"/>
                    </div>
                </c:when>
            </c:choose>
        </form>
    </main>
</div>


</body>
<jsp:include page="fragment/footer.jsp"/>


</html>
