<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3"><fmt:message key="lang.personal.data"/></h4>
                <form class="needs-validation" action="${pageContext.request.contextPath}/controller?command=signupUser"
                      method="post">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label"><fmt:message key="lang.first.name"/></label>
                            <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                        </div>


                        <div class="col-sm-6">
                            <label for="lastName" class="form-label"><fmt:message key="lang.last.name"/></label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
                        </div>

                        <div class="col-12">
                            <label for="email" class="form-label"><fmt:message key="lang.email"/></label>
                            <input type="email" class="form-control" id="email" placeholder="you@example.com"
                                   required="">
                        </div>
                        <div class="col-12 pb-2">
                            <label for="floatingPassword"><fmt:message key="lang.password"/></label>
                            <input type="password" name="password" class="form-control" id="floatingPassword"
                                   placeholder="Password" required minlength="10" maxlength="20">
                        </div>

                    </div>
                    <button class="w-100 btn btn-info btn-lg pt-2" type="submit"><fmt:message key="lang.sign.up"/></button>
                </form>
            </div>
        </div>
    </main>

    <jsp:include page="fragment/footer.jsp"/>
</div>
</body>
</html>
