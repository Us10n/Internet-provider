<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="provider/copyrightTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Tariffs</title>
</head>
<body>

<div class="container pt-2">
    <div class="row">
        <div class="col-md-4 mr-auto">
            <form action="${pageContext.request.contextPath}/controller?command=tariffs" method="post">
                <select class="form-select" name="sort" aria-label="Default select example">
                    <option value="name"><fmt:message key="lang.sort.by.name"/></option>
                    <option value="speed"><fmt:message key="lang.sort.by.speed"/></option>
                    <option value="price"><fmt:message key="lang.sort.by.price"/></option>
                    <option value="rating"><fmt:message key="lang.sort.by.rating"/></option>
                </select>
                <button type="submit" class="btn btn-outline-success btn-sm">
                    <fmt:message key="lang.sort.button"/></button>
            </form>
        </div>
        <c:if test="${userRole eq 'ADMIN'}">
            <div class="col-2 align-self-start">
                <a href="?command=tariffAddPage" class="nav-link bg-info text-white text-center">
                    <fmt:message key="lang.add.tariff"/>
                </a>
            </div>
        </c:if>
    </div>
</div>

<div class="container-fluid mt--7 w">
    <div class="album pb-5 pl-2">
        <div class="container">
            <c:forEach var="tariff" items="${tariffs}">
                <c:choose>
                    <c:when test="${userRole eq 'ADMIN'}">
                        <div class="row bg-light my-2">
                            <div class="col-md-auto align-self-center">
                                <img src="static/images/tariff/<c:out value="${tariff.image}"/>" class="img-fluid"
                                     width="60"
                                     height="60">
                            </div>
                            <div class="col-md align-self-center">
                                <p class="card-text bold"><c:out value="${tariff.name}"/></p>
                            </div>
                            <div class="col-md align-self-center">
                                <p class="card-text bold"><fmt:message key="lang.speed.up.to"/> <c:out
                                        value="${tariff.internetSpeed}"/> <fmt:message key="lang.mb.s"/></p>
                            </div>
                            <div class="col-md align-self-center">
                                <c:if test="${tariff.newPrice==null}">
                                    <p class="card-text bold">
                                        <c:out value="${tariff.price}"/> <fmt:message key="lang.rub.month"/>
                                    </p>
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
                            <div class="col-md align-self-center">
                                <p class="card-text bold"><fmt:message key="lang.rating"/> <c:out
                                        value="${tariff.rating}"/></p>
                            </div>
                            <div class="col-md align-self-center">
                                <c:choose>
                                    <c:when test="${tariff.status=='ACTIVE'}">
                                        <p class="card-text text-success bold"><fmt:message key="lang.status"/>
                                            <c:out
                                                    value="${tariff.status}"/></p>
                                    </c:when>
                                    <c:when test="${tariff.status=='ARCHIVE'}">
                                        <p class="card-text text-warning bold"><fmt:message key="lang.status"/>
                                            <c:out
                                                    value="${tariff.status}"/></p>
                                    </c:when>
                                    <c:when test="${tariff.status=='DEACTIVATED'}">
                                        <p class="card-text text-danger bold"><fmt:message key="lang.status"/>
                                            <c:out
                                                    value="${tariff.status}"/></p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="col-md align-self-center d-flex flex-wrap">
                                <a href="?command=tariff&name=${tariff.name}"
                                   class="nav-link bg-info text-white"><fmt:message
                                        key="lang.view"/></a>
                            </div>
                            <c:if test="${userRole eq 'ADMIN'}">
                                <div class="col-md align-self-center d-flex flex-wrap">
                                    <a href="?command=tariffEditPage&name=${tariff.name}"
                                       class="nav-link bg-info text-white"><fmt:message
                                            key="lang.edit"/></a>
                                </div>
                            </c:if>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${tariff.status != 'DEACTIVATED'}">
                            <div class="row bg-light my-2">
                                <div class="col-md-auto align-self-center">
                                    <img src="static/images/tariff/<c:out value="${tariff.image}"/>" class="img-fluid"
                                         width="60"
                                         height="60">
                                </div>
                                <div class="col-md align-self-center">
                                    <p class="card-text bold"><c:out value="${tariff.name}"/></p>
                                </div>
                                <div class="col-md align-self-center">
                                    <p class="card-text bold"><fmt:message key="lang.speed.up.to"/> <c:out
                                            value="${tariff.internetSpeed}"/> <fmt:message key="lang.mb.s"/></p>
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
                                <div class="col-md align-self-center">
                                    <p class="card-text bold"><fmt:message key="lang.rating"/> <c:out
                                            value="${tariff.rating}"/></p>
                                </div>
                                <div class="col-md align-self-center d-flex flex-wrap">
                                    <a href="?command=tariff&name=${tariff.name}"
                                       class="nav-link bg-info text-white"><fmt:message
                                            key="lang.view"/></a>
                                </div>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>

            </c:forEach>


        </div>
    </div>
</div>
</div>

<h1/>

</body>

<jsp:include page="fragment/footer.jsp"/>
</html>