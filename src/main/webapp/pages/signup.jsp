<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Sign Up</title>

    <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <script src="form-validation.js"></script>
</head>
<body class="bg-light">

<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2>Registration form</h2>
        </div>

        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3">Personal data</h4>
                <form class="needs-validation" action="${pageContext.request.contextPath}/controller?command=signupUser" method="post">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">First name</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" value="" required="">
                            <div class="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" placeholder="you@example.com" required="">
                            <div class="invalid-feedback">
                                Please enter a valid email address
                            </div>
                        </div>
                        <div class="col-12">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password" required="">
                            <div class="invalid-feedback">
                                Please enter a valid password
                            </div>
                        </div>
                    </div>
                    <button class="w-100 btn btn-info btn-lg pt-2" type="submit">Sign Up</button>
                </form>
            </div>
        </div>
    </main>

    <jsp:include page="fragment/footer.jsp"/>
</div>
</body>
</html>
