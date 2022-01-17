<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="en-US"/>
<fmt:setBundle basename="language"/>
<header class="p-3 bg-info text-white">

    <div class="container">
        <div class="d-flex flex-wrap justify-content-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.tariffs"/> </a></li>
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.promotions"/></a></li>
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.about"/></a></li>
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.users"/></a></li>
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.requests"/></a></li>
            </ul>

            <ul class="nav col-12 col-lg-auto ml-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 text-black-50 bg-white"><fmt:message key="lang.balance"/>: 234324</a></li>
                <li><a href="?command=login" class="nav-link px-2 text-white"><fmt:message key="lang.log.in"/></a></li>
                <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="lang.sign.up"/></a></li>
                <li><a href="123" class="nav-link px-2 text-white"><fmt:message key="lang.log.out"/></a></li>
            </ul>
        </div>
    </div>
</header>