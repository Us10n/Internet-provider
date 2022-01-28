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
                <div class="col pb-5">
                    <div class="card shadow-sm">
                        <img src="static/images/offer/<c:out value="${offer.image}"/>" class="img-fluid" width="100%"
                             height="200">

                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${offer.title}"/></h5>
                            <p class="card-text"><c:out value="${offer.description}"/></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <c:if test="${userRole eq 'ADMIN'}">
                                    <form action=${pageContext.request.contextPath}/controller?command=deletePromotion
                                          method="post">
                                        <div class="btn-group">
                                            <a href="${pageContext.request.contextPath}/controller?command=promotionEditPage&offerId=${offer.id}"
                                               class="btn btn-sm btn-outline-secondary">Edit
                                            </a>
                                            <button type="submit" name="offerId" value="${offer.id}"
                                                    class="btn btn-sm btn-outline-danger">Delete
                                            </button>
                                        </div>
                                    </form>
                                </c:if>
                                <small class="text-muted"><c:out value="${offer.startDate}"/> — <c:out
                                        value="${offer.expirationDate}"/></small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${userRole=='ADMIN'}">
                <div class="col align-self-center text-center">
                    <a href="${pageContext.request.contextPath}/controller?command=promotionAddPage" class="btn bg-info btn-primary btn-lg" role="button" style="width: 200px">
                        <fmt:message key="lang.promotion.add"/></a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<h1/>
</body>

<jsp:include page="fragment/footer.jsp"/>
</html>
