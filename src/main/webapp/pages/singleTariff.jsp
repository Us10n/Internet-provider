<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Tariff view</title>
</head>
<body>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div class="col-10 col-sm-8 col-lg-6">
            <img src="static/images/<c:out value="${tariff.image}"/>" class=" d-block mx-lg-auto img-fluid"
                 alt="Bootstrap Themes" width="300" height="300" loading="lazy">
        </div>
        <div class="col-lg-6">
            <h1 class="display-5 fw-bold lh-1 mb-3"><c:out value="${tariff.name}"/></h1>
            <p class="lead"><c:out value="${tariff.description}"/></p>
            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <div class="container">
                    <div class="row">
                        <div class="col-md align-self-center">
                            <fmt:message key="lang.speed.up.to"/>
                        </div>
                        <div class="col-md align-self-center">
                            <p>
                                <c:out value="${tariff.internetSpeed}"/> <fmt:message key="lang.mb.s"/>
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md align-self-center">
                            <fmt:message key="lang.price"/>
                        </div>
                        <div class="col-md align-self-center">
                            <c:if test="${tariff.newPrice==null}">
                                <p class="card-text bold"><c:out value="${tariff.price}"/><fmt:message
                                        key="lang.rub.month"/></p>
                            </c:if>
                            <c:if test="${tariff.newPrice!=null}">
                                <p class="card-text bold" type="number">
                                    <s>
                                        <c:out value="${tariff.price}"/> <fmt:message key="lang.rub.month"/>
                                    </s>
                                </p>
                                <p class="card-text text-danger bold" type="number"><c:out value="${tariff.newPrice}"/>
                                    <fmt:message key="lang.rub.month"/></p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container pt-5">
                <button class="btn bg-info text-white" type="submit"><fmt:message key="lang.subscribe"/></button>
            </div>

        </div>
    </div>
</div>

</body>

<jsp:include page="fragment/footer.jsp"/>
</html>
