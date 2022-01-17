<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="provider/copyrightTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="en-US"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container-fluid mt--7">
    <div class="row">
        <div class="col-xl-3 order-xl-2 mb-5 mb-xl-0">
            <div class="card card-profile shadow">
                <div class="card-body pt-0 pt-md-2">
                    <div class="text-center">
                        <h3>
                            Пользователи
                        </h3>
                        <hr class="my-4"/>
                        <p *ngFor="let tempUsers of currentProject?.users">{{tempUsers.account?.name}}
                            {{tempUsers.account?.surname}}</p>

                    </div>
                </div>
            </div>
        </div>
        <div class="album py-5 mr">
            <div class="container">

                <div class="row bg-light">
                    <div class="col-md-auto">
                        <img src="static/images/<c:out value="tariff/super.png"/>" class="img-fluid" width="100"
                             height="100">
                    </div>
                    <div class="col">
                        <p class="card-text bold">This is a wider card with supporting text below as a natural lead-in to
                            additional content. This content is a little bit longer.</p>
                    </div>

                    <div class="col">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex align-items-end">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="album py-5">
    <div class="container">

        <div class="row bg-light">
            <div class="col-md-auto">
                <img src="static/images/<c:out value="tariff/super.png"/>" class="img-fluid" width="100"
                     height="100">
            </div>
            <div class="col">
                <p class="card-text bold">This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.</p>
            </div>

            <div class="col">
                <div class="card-body">
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                        additional content. This content is a little bit longer.</p>
                    <div class="d-flex align-items-end">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>

<jsp:include page="fragment/footer.jsp"/>
</html>