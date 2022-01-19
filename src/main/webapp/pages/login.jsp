<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Login</title>
</head>

<body class="text-center page-content">

<main class="form-signin mx-2">
    <form data-bitwarden-watching="1">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>
        <button class="w-100 btn btn-lg btn-info" type="submit">Sign in</button>
    </form>
</main>


</body>
<%--<div class="h-100 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Высота 100%</div>--%>
<jsp:include page="fragment/footer.jsp"/>


</html>
