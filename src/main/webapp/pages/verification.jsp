<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <title>Verification</title>
</head>
<body>
<main class="flex-shrink-0">
    <div class="container">
        <h1 class="mt-5"><fmt:message key="lang.verification.successful"/></h1>
        <a href="${pageContext.request.contextPath}/controller?command=login"><fmt:message key="lang.go.to.home"/></a>
    </div>
</main>
</body>
<jsp:include page="fragment/footer.jsp"/>
</html>
