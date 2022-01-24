<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Profile</title>
</head>
<body>

<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2><fmt:message key="lang.profile"/></h2>
        </div>

        <div class="row g-5 justify-content-center">
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3">
                    <c:choose>
                        <c:when test="${tariffName !=null}">
                            <fmt:message key="lang.current.tariff"/>: <c:out value="${tariffName}"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="lang.current.tariff"/>: <fmt:message key="lang.tariff.none"/>
                        </c:otherwise>
                    </c:choose>
                </h4>
                <h4 class="mb-3">
                    <fmt:message key="lang.status"/>: <c:out value="${user.status}"/>
                </h4>
                <h4 class="mb-3">
                    <div class="col-md-3">
                        <fmt:message key="lang.balance"/>: <c:out value="${bankAccount.balance}"/>
                    </div>
                    <div class="col-md-3">
                        <a href="?command=rechargeBalancePage"
                           class="nav-link px-2 text-black-50 bg-success"><fmt:message
                                key="lang.recharge.balance"/> </a>
                    </div>
                </h4>
                <form class="needs-validation"
                      action="${pageContext.request.contextPath}/controller?command=editProfile"
                      method="post">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label"><fmt:message key="lang.first.name"/></label>
                            <input type="text" name="newFirstName" class="form-control" id="firstName" placeholder=""
                                   value="${user.name}" required maxlength="30" minlength="1">
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label"><fmt:message key="lang.last.name"/></label>
                            <input type="text" name="newLastName" class="form-control" id="lastName" placeholder=""
                                   value="${user.surname}" required maxlength="30" minlength="1">
                        </div>
                        <c:if test="${firstNameError eq 'true'}">
                            <div class="alert alert-danger fade show " role="alert">
                                <fmt:message key="lang.first.name.update.error"/>
                            </div>
                        </c:if>
                        <c:if test="${lastNameError eq 'true'}">
                            <div class="alert alert-danger fade show " role="alert">
                                <fmt:message key="lang.last.name.update.error"/>
                            </div>
                        </c:if>

                        <div class="col-12">
                            <label for="email" class="form-label"><fmt:message key="lang.email"/></label>
                            <input type="email" name="email" class="form-control" id="email"
                                   value="${user.email}"
                                   maxlength="45" readonly>
                        </div>
                        <div class="col-12 pb-2">
                            <label for="floatingPassword"><fmt:message key="lang.new.password"/></label>
                            <input type="text" name="newPassword" class="form-control" id="floatingPassword"
                                   placeholder="<fmt:message key="lang.new.password"/>" minlength="8"
                                   maxlength="20">
                        </div>
                        <c:if test="${passwordError eq 'true'}">
                            <div class="alert alert-danger fade show " role="alert">
                                <fmt:message key="lang.password.update.error"/>
                            </div>
                        </c:if>

                    </div>
                    <button class="w-100 btn btn-info btn-lg pt-2" type="submit"><fmt:message
                            key="lang.edit"/></button>
                    <c:if test="${editError eq 'true'}">
                        <div class="alert alert-danger fade show " role="alert">
                            <fmt:message key="lang.tariff.edit.error"/>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </main>
</div>

</body>
<jsp:include page="fragment/footer.jsp"/>
</html>