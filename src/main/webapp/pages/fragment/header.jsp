<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<header class="p-2 bg-info text-white">

    <div class="container">
        <div class="d-flex flex-wrap justify-content-center align-self-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/controller?command=home"
                       class="nav-link px-2 text-white"><fmt:message key="lang.home"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=tariffs"
                       class="nav-link px-2 text-white"><fmt:message key="lang.tariffs"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=promotions"
                       class="nav-link px-2 text-white"><fmt:message key="lang.promotions"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=about"
                       class="nav-link px-2 text-white"><fmt:message key="lang.about"/></a></li>
                <c:if test="${user!=null && user.role=='ADMIN'}">
                    <div class="dropdown">
                        <button class="btn bg-info text-white dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="lang.admin.panel"/>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=usersPanel">
                                <fmt:message key="lang.users.control"/></a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=tariffsPanel">
                                <fmt:message key="lang.tariff.control"/> </a></li>
                            <li><a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=promotionsPanel">
                                <fmt:message key="lang.promotions.control"/></a></li>
                        </ul>
                    </div>
                </c:if>
            </ul>

            <ul class="nav col-12 col-lg-auto ml-lg-auto mb-2 justify-content-center mb-md-0">

                <div class="dropdown">
                    <form action="${pageContext.request.contextPath}/controller?command=changeLocale" method="post">
                        <c:choose>
                            <c:when test="${cookie['lang'].value eq 'ru-RU'}">
                                <button class="btn bg-info text-white dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <fmt:message key="lang.ru"/>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                                    <button class="dropdown-item" name="cookieLocale" value="en-US" type="submit">
                                        <fmt:message key="lang.en"/>
                                    </button>
                                </ul>
                            </c:when>
                            <c:when test="${cookie['lang'].value eq 'en-US'}">
                                <button class="btn bg-info text-white dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <fmt:message key="lang.en"/>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                                    <button class="dropdown-item" name="cookieLocale" value="ru-RU" type="submit">
                                        <fmt:message key="lang.ru"/>
                                    </button>
                                </ul>
                            </c:when>
                        </c:choose>
                    </form>

                </div>
                <c:if test="${user!=null && bankAccount!=null}">
                    <li><a href="#" class="nav-link px-2 text-black-50 bg-white"><fmt:message key="lang.balance"/>:
                        <c:out value="${bankAccount.balance}"/></a></li>
                </c:if>
                <c:if test="${user eq null}">
                    <li><a href="?command=login" class="nav-link px-2 text-white"><fmt:message key="lang.log.in"/></a>
                    </li>
                    <li><a href="?command=signup" class="nav-link px-2 text-white"><fmt:message key="lang.sign.up"/></a>
                    </li>
                </c:if>
                <c:if test="${user!=null}">
                    <form action="${pageContext.request.contextPath}/controller?command=logoutUser" method="post">
                        <button class="btn bg-info text-white" type="submit">
                            <fmt:message key="lang.log.out"/>
                        </button>
                        </li>
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</header>