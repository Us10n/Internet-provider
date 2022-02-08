<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Sign up</title>

    <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body class="bg-light">

<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2><fmt:message key="lang.sign.up.form"/></h2>
        </div>

        <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-info"><fmt:message key="lang.note"/> </span>
                </h4>
                <h5 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-black-50"><fmt:message key="lang.verification.info"/></span>
                </h5>
            </div>
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3"><fmt:message key="lang.personal.data"/></h4>
                <form class="needs-validation" action="${pageContext.request.contextPath}/controller?command=signupUser"
                      method="post">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label"><fmt:message key="lang.first.name"/></label>
                            <input type="text" name="firstName" class="form-control" id="firstName" placeholder=""
                                   required maxlength="30" minlength="1"
                            pattern="^[A-Za-zА-Яа-я][A-Za-zА-Яа-я]{0,29}$">
                        </div>


                        <div class="col-sm-6">
                            <label for="lastName" class="form-label"><fmt:message key="lang.last.name"/></label>
                            <input type="text" name="lastName" class="form-control" id="lastName" placeholder=""
                                   value="" required maxlength="30" minlength="1"
                            pattern="^[A-Za-zА-Яа-я][A-Za-zА-Яа-я]{0,29}$">
                        </div>

                        <div class="col-12">
                            <label for="email" class="form-label"><fmt:message key="lang.email"/></label>
                            <input type="email" name="email" class="form-control" id="email"
                                   placeholder="you@example.com"
                                   required maxlength="45">
                        </div>
                        <div class="col-12 pb-2">
                            <label for="floatingPassword"><fmt:message key="lang.password"/></label>
                            <input type="password" name="password" class="form-control" id="floatingPassword"
                                   placeholder="<fmt:message key="lang.password"/>" required minlength="8"
                                   maxlength="20">
                        </div>

                    </div>
                    <button class="w-100 btn btn-info btn-lg pt-2" type="submit"><fmt:message
                            key="lang.sign.up"/></button>
                    <c:choose>
                        <c:when test="${signupError eq 'true'}">
                            <div class="alert alert-danger fade show " role="alert">
                                <fmt:message key="lang.signup.error"/>
                            </div>
                        </c:when>
                        <c:when test="${existsError eq 'true'}">
                            <div class="alert alert-danger fade show " role="alert">
                                <fmt:message key="lang.user.exists.error"/>
                            </div>
                        </c:when>
                    </c:choose>
                </form>
            </div>
        </div>
    </main>
</div>

<jsp:include page="fragment/footer.jsp"/>
</div>
</body>
</html>
