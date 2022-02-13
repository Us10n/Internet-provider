<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Tariff edit</title>
</head>
<body>

<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2><fmt:message key="lang.tariff.edit.form"/></h2>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-8">
                <form class="needs-validation" action="${pageContext.request.contextPath}/controller?command=tariffEdit"
                      method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <label for="tariffName" class="form-label"><fmt:message key="lang.tariff.name"/> </label>
                            <input type="text" name="tariffName" class="form-control" id="tariffName"
                                   value="${tariff.name}"
                                   readonly>
                        </div>

                        <div class="col-12">
                            <label for="internetSpeed" class="form-label">
                                <fmt:message key="lang.speed.up.to"/>, <fmt:message key="lang.mb.s"/>
                            </label>
                            <input type="number" name="tariffInternetSpeed" class="form-control" id="internetSpeed"
                                   value="${tariff.internetSpeed}"
                                   required min="0"
                                   pattern="^\d+$"
                                   max="9999">
                        </div>
                        <div class="col-12">
                            <label for="tariffPrice">
                                <fmt:message key="lang.price"/>,<fmt:message key="lang.rub.month"/></label>
                            <input type="text" name="tariffPrice" class="form-control" id="tariffPrice"
                                   value="${tariff.price}"
                                   required min="0"
                                   pattern="^[0-9]+(\.[0-9]{1,2})?$"
                                   maxlength="11">
                        </div>
                        <div class="col-12 pb-2">
                            <label for="tariffImage"><fmt:message key="lang.image.name"/> </label>
                            <input type="text" name="tariffImage" class="form-control" id="tariffImage" required
                                   placeholder="image.png"
                                   value="${tariff.image}"
                                   pattern="^[\w_]+\.[A-Za-z]{3}$"
                                   maxlength="45">
                        </div>
                        <div class="col-sm-6">
                            <label for="tariffStatus"><fmt:message key="lang.status"/> </label>
                            <select id="tariffStatus" class="form-select" name="tariffStatus">
                                <c:choose>
                                    <c:when test="${tariff.status=='ACTIVE'}">
                                        <option value="ACTIVE"><fmt:message key="lang.tariff.status.active"/></option>
                                        <option value="ARCHIVE"><fmt:message key="lang.tariff.status.archive"/></option>
                                        <option value="DEACTIVATED"><fmt:message
                                                key="lang.tariff.status.deactivated"/></option>
                                    </c:when>
                                    <c:when test="${tariff.status=='ARCHIVE'}">
                                        <option value="ARCHIVE"><fmt:message key="lang.tariff.status.archive"/></option>
                                        <option value="ACTIVE"><fmt:message key="lang.tariff.status.active"/></option>
                                        <option value="DEACTIVATED"><fmt:message
                                                key="lang.tariff.status.deactivated"/></option>
                                    </c:when>
                                    <c:when test="${tariff.status=='DEACTIVATED'}">
                                        <option value="DEACTIVATED"><fmt:message
                                                key="lang.tariff.status.deactivated"/></option>
                                        <option value="ARCHIVE"><fmt:message key="lang.tariff.status.archive"/></option>
                                        <option value="ACTIVE"><fmt:message key="lang.tariff.status.active"/></option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <label for="specialOfferTitle"><fmt:message key="lang.tariff.special.offer"/> </label>
                            <select id="specialOfferTitle" class="form-select" name="specialOfferTitle">
                                <c:if test="${tariff.specialOffer.isPresent() eq 'true'}">
                                    <option value="${tariff.specialOffer.get().title}">
                                        <c:out value="${tariff.specialOffer.get().title}"/>
                                    </option>
                                </c:if>
                                <option value="NONE"><fmt:message key="lang.tariff.none"/></option>
                                <c:forEach var="specialOffer" items="${specialOffers}">
                                    <c:if test="${tariff.specialOffer.isEmpty() || tariff.specialOffer.get().title!=specialOffer.title}">
                                        <option value="${specialOffer.title}">
                                            <c:out value="${specialOffer.title}"/>
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-12 pb-3">
                            <label for="tariffDescription"><fmt:message key="lang.description"/></label>
                            <textarea name="tariffDescription" class="form-control" id="tariffDescription"
                                      required maxlength="2048"><c:out value="${tariff.description}"/></textarea>
                        </div>
                        <c:if test="${editError eq 'true'}">
                            <div class="alert alert-danger fade show pb-3" role="alert">
                                <fmt:message key="lang.tariff.edit.error"/>
                            </div>
                        </c:if>

                    </div>
                    <div class="text-right">
                        <button class="btn btn-success btn-lg" type="submit">
                            <fmt:message key="lang.submit"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>

</body>
</html>
