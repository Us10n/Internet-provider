<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Promotions</title>
</head>
<body>
<div class="album py-5 bg-light">
    <div class="container">

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="offer" items="${offers}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="static/images/offer/<c:out value="${offer.image}"/>" class="img-fluid" width="100%"
                             height="200">

                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${offer.title}"/></h5>
                            <p class="card-text"><c:out value="${offer.description}"/></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-muted"><c:out value="${offer.startDate}"/> — <c:out
                                        value="${offer.expirationDate}"/></small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>
</div>
</body>

<jsp:include page="fragment/footer.jsp"/>
</html>
