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

<c:if test="${userRole!='ADMIN' && tariff.status =='DEACTIVATED'}">
    <jsp:forward page="error/error404.jsp"/>
</c:if>

<form action="${pageContext.request.contextPath}/controller?command=tariffAction"
      method="post">

    <div class="container col-xxl-8 px-4 py-5">
        <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
            <input type="hidden" name="tariffId" value="${tariff.id}"/>

            <div class="col-10 col-sm-8 col-lg-6">
                <img src="static/images/tariff/<c:out value="${tariff.image}"/>" class=" d-block mx-lg-auto img-fluid"
                     alt="Bootstrap Themes" width="300" height="300" loading="lazy">
            </div>
            <div class="col-lg-6">
                <h1 class="display-5 fw-bold lh-1 mb-3"><c:out value="${tariff.name}"/></h1>
                <c:choose>
                    <c:when test="${tariff.status=='ACTIVE'}">
                        <p class="text-success"><fmt:message key="lang.status"/>: <c:out
                                value="${tariff.status}"/></p>
                    </c:when>
                    <c:when test="${tariff.status=='ARCHIVE'}">
                        <p class="text-warning"><fmt:message key="lang.status"/>: <c:out
                                value="${tariff.status}"/></p>
                    </c:when>
                    <c:when test="${tariff.status=='DEACTIVATED'}">
                        <p class="text-danger"><fmt:message key="lang.status"/>: <c:out
                                value="${tariff.status}"/></p>
                    </c:when>
                </c:choose>
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
                                    <p class="card-text text-danger bold" type="number"><c:out
                                            value="${tariff.newPrice}"/>
                                        <fmt:message key="lang.rub.month"/></p>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md align-self-center">
                                <fmt:message key="lang.tariff.special.offer"/>:
                            </div>
                            <div class="col-md align-self-center">
                                <p>
                                    <c:choose>
                                        <c:when test="${tariff.specialOffer.isEmpty() eq 'true'}">
                                            <fmt:message key="lang.tariff.none"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${tariff.specialOffer.get().title}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container pt-5">
                    <c:choose>
                        <c:when test="${userId!=null}">
                            <c:if test="${userTariffId ==null && tariff.status != 'ARCHIVE' && tariff.status!='DEACTIVATED' && sessionScope.userStatus!='BLOCKED'}">
                                <button class="btn bg-info text-white" name="action" value="subscribe" type="submit">
                                    <fmt:message key="lang.subscribe"/>
                                </button>
                            </c:if>
                            <c:if test="${userTariffId !=null && userTariffId==tariff.id}">
                                <button class="btn bg-info text-white" name="action" value="unsubscribe" type="submit">
                                    <fmt:message key="lang.unsubscribe"/>
                                </button>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${tariff.status!= 'ARCHIVE' && tariff.status != 'DEACTIVATED'}">
                                <a href="${pageContext.request.contextPath}/controller?command=login"
                                   class="btn bg-info text-white"><fmt:message key="lang.subscribe"/></a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</form>
<c:if test="${userId !=null}">
    <form action="${pageContext.request.contextPath}/controller?command=addFeedback"
          method="post">
        <div class="container">
            <main>
                <div class="row justify-content-center">
                    <div class="col-md-7 col-lg-8">
                        <input type="hidden" name="tariffId" value="${tariff.id}"/>
                        <div class="row g-3">
                            <div class="col-12 pb-3">
                        <textarea style="resize: none" name="feedbackBody" class="form-control"
                                  required maxlength="2048"></textarea>
                            </div>
                        </div>
                        <div class="row pl-2">
                            <div class="col-sm-auto">
                                <p class="text-black-50"><fmt:message key="lang.rating"/>: </p>
                            </div>
                            <div class="col-sm-4">
                                <select class="form-select" name="rating" aria-label="Default select example">
                                    <option value="1" selected>1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <button class="bg-info text-white" type="submit"><fmt:message
                                        key="lang.comment"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </form>
</c:if>
<div class="container-fluid mt--7 w pt-3 pb-3">
    <div class="album pb-5 pl-2">
        <div class="container">
            <c:forEach var="feedback" items="${feedbacks}">
                <div class="row bg-light my-2">
                    <div class="row">
                        <div class="col ml-4">
                            <p class="text-black-50"><c:out value="${feedback.authorName}"/></p>
                        </div>
                        <div class="col">
                            <p class="text-black-50">
                                <fmt:message key="lang.rating"/>: <c:out value="${feedback.rating}"/>
                            </p>
                        </div>
                    </div>
                    <textarea style="resize: none" name="tariffDescription" class="form-control"
                              required maxlength="2048" readonly><c:out value="${feedback.feedbackBody}"/></textarea>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<h1></h1>

</body>

<jsp:include page="fragment/footer.jsp"/>
</html>
