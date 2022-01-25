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
    <title>Users panel</title>
</head>
<body>
<div class="container pt-2">
    <c:if test="${updateError eq 'true'}">
        <div class="row">
            <div class="alert alert-danger fade show " role="alert">
                <fmt:message key="lang.operation.error"/>
            </div>
        </div>
    </c:if>
</div>
<div class="container pt-2">
    <div class="row">
        <div class="col-md-4 mr-auto">
            <form action="${pageContext.request.contextPath}/controller?command=usersPanel" method="post">
                <select class="form-select" name="sort" aria-label="Default select example">
                    <option value="firstName"><fmt:message key="lang.sort.by.first.name"/></option>
                    <option value="email"><fmt:message key="lang.sort.by.email"/></option>
                    <option value="role"><fmt:message key="lang.sort.by.role"/></option>
                    <option value="status"><fmt:message key="lang.sort.by.status"/></option>
                </select>
                <button type="submit" class="btn btn-outline-success btn-sm">
                    <fmt:message key="lang.sort.button"/></button>
            </form>
        </div>
    </div>
</div>

<div class="container-fluid mt--7 w">
    <div class="album pb-5 pl-2">
        <div class="container">
            <c:forEach var="user" items="${users}">
                <form class="needs-validation" action="${pageContext.request.contextPath}/controller?command=userAction"
                      method="post">
                    <input type="hidden" name="userEditingId" value="${user.id}"/>
                    <div class="row bg-light my-2">
                        <div class="col-1 align-self-center">
                            <p class="card-text bold"><c:out value="${user.name}"/></p>
                        </div>
                        <div class=" col-3 align-self-center">
                            <p class="card-text bold"><c:out value="${user.email}"/></p>
                        </div>
                        <div class="col-1 align-self-center">
                            <p class="card-text bold"><c:out value="${user.role}"/></p>
                        </div>
                        <div class="col-1 align-self-center">
                            <p class="card-text bold"><c:out value="${user.status}"/></p>
                        </div>

                        <div class="col-1 align-self-center d-flex flex-wrap">
                            <c:if test="${user.status eq 'UNVERIFIED'}">
                                <button type="submit" name="action" value="verify"
                                        class="nav-link bg-info text-white">
                                    <fmt:message key="lang.user.verify"/>
                                </button>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${user.status eq 'BANNED'}">
                                <div class="col-1 align-self-center d-flex flex-wrap">
                                    <button type="submit" name="action" value="unban"
                                            class="nav-link bg-success text-white">
                                        <fmt:message key="lang.user.unban"/>
                                    </button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${user.id != userId}">
                                    <div class="col-1 align-self-center d-flex flex-wrap">
                                        <button type="submit" name="action" value="ban"
                                                class="nav-link bg-danger text-white">
                                            <fmt:message key="lang.user.ban"/>
                                        </button>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <div class="col-2 align-self-center d-flex flex-wrap">
                            <c:if test="${user.role != 'ADMIN' && user.status eq 'VERIFIED'}">
                                <button type="submit" name="action" value="makeAdmin"
                                        class="nav-link bg-warning text-white">
                                    <fmt:message key="lang.make.admin"/>
                                </button>
                            </c:if>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>
</div>
</div>

<h1/>

</body>

<jsp:include page="fragment/footer.jsp"/>
</html>