<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>About us</title>
</head>
<body>
<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div class="row">
            <div class="col-10 col-sm-8 col-lg-6">
                <img src="static/images/tariff/light.png" class="d-block mx-lg-auto img-fluid" alt="Bootstrap Themes"
                     width="400"
                     height="400" loading="lazy">
            </div>
            <div class="col-lg-6">
                <h1 class="display-5 fw-bold lh-1 mb-3"><fmt:message key="lang.about"/></h1>
                <p class="lead"><fmt:message key="lang.about.text"/></p>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="fragment/footer.jsp"/>
</html>
