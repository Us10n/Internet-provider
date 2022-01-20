<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/styles/common.css" type="text/css">
</head>
<body>
<jsp:include page="fragment/header.jsp"/>
<div class="d-flex justify-content-lg-start align-self-start mask-custom"
     style="background-image: url('https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Fmain.jpg?alt=media&token=b0754d6e-d009-4152-9fac-3f8a668ef06e'); height: 100vh;">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <header class="mb-auto">
        </header>

        <main class="px-3">
            <h1 class="text-white">Internet Provider</h1>
            <p class="lead text-white"><fmt:message key="lang.slogan"/></p>
            <p class="lead">
                <a href="${pageContext.request.contextPath}/controller?command=tariffs"
                   class="btn btn-lg btn-secondary fw-bold border-white bg-white"><fmt:message key="lang.tariffs"/> </a>
            </p>
        </main>

        <footer class="mt-auto text-white-50">
        </footer>
    </div>
</div>
</div>
</body>
</html>
