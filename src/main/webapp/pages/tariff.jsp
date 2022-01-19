<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="provider/copyrightTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<jsp:include page="fragment/links.jsp"/>
<head>
    <jsp:include page="fragment/header.jsp"/>
    <title>Internet Provider</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script></head>
<body>

<div class="container pt-2">
    <div class="row">
        <div class="col-1">
            <form action="${pageContext.request.contextPath}/controller?command=tariffs" method="post">
                <select class="form-select" name="sort" aria-label="Default select example">
                    <option value="/provider/controller?command=main&sort=name">Sort by name</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                <button type="submit" class="btn btn-outline-success btn-sm" style="margin-top: 20px"><fmt:message
                        key="lang.sort.button"/></button>
            </form>

        </div>
    </div>
</div>

<div class="dropdown">
    <button class="btn bg-info text-white dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        Dropdown button
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="#">Action</a></li>
        <li><a class="dropdown-item" href="#">Another action</a></li>
        <li><a class="dropdown-item" href="#">Something else here</a></li>
    </ul>
</div>

<div class="container-fluid mt--7 w">
    <div class="album pb-5 pl-2">
        <div class="container">
            <c:forEach var="tariff" items="${tariffs}">
                <c:if test="${tariff.status == 'ACTIVE'}">
                    <div class="row bg-light my-2">
                        <div class="col-md-auto align-self-center">
                            <img src="static/images/<c:out value="${tariff.image}"/>" class="img-fluid" width="60"
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
                                <p class="bold text-decoration-line-through" type="number"><c:out
                                        value="${tariff.price}"/>
                                    <fmt:message key="lang.rub.month"/></p>
                                <p class="card-text text-danger bold" type="number"><c:out value="${tariff.newPrice}"/>
                                    <fmt:message key="lang.rub.month"/></p>
                            </c:if>
                        </div>
                        <div class="col-md align-self-center">
                            <p class="card-text bold"><fmt:message key="lang.rating"/> <c:out
                                    value="${tariff.rating}"/></p>
                        </div>
                        <div class="col-md align-self-center d-flex flex-wrap">
                            <a href="?command=tariff?id=${tariff.id}" class="nav-link bg-info text-white"><fmt:message
                                    key="lang.view"/></a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>


        </div>
    </div>
</div>
</div>

<h1/>

</body>

<jsp:include page="fragment/footer.jsp"/>
</html>