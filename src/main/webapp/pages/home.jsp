<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/styles/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/styles/nicepage.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/styles/Главная.css" media="screen">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
</head>
<body>
<jsp:include page="fragment/header.jsp"/>
<div class="d-flex justify-content-center align-self-center mask-custom"
     style="background-image: url('https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Fmain.jpg?alt=media&token=b0754d6e-d009-4152-9fac-3f8a668ef06e'); height: 100vh;">
    <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <h1 class="u-text u-text-white u-title u-text-1">Internet Provider</h1>
        <p class="u-large-text u-text u-text-variant u-text-2 u-text-white"><fmt:message key="lang.slogan"/></p>
        <a href="${pageContext.request.contextPath}/controller?command=tariffs"
           class="u-btn u-button-style u-palette-2-base u-btn-1">
            <fmt:message key="lang.tariffs"/></a>
    </div>
</div>
</body>
</html>
