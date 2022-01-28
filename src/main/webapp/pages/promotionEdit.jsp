<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Special offer edit</title>
</head>
<body>

<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2><fmt:message key="lang.promotion.edit.form"/></h2>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-8">
                <form class="needs-validation"
                      action="${pageContext.request.contextPath}/controller?command=promotionEdit"
                      method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <label for="offerTitle" class="form-label"><fmt:message
                                    key="lang.special.offer.title"/> </label>
                            <input type="text" name="offerTitle" class="form-control" id="offerTitle"
                                   value="${specialOffer.title}"
                                   readonly>
                        </div>
                        <div class="col-12">
                            <label for="startDate" class="form-label">
                                <fmt:message key="lang.special.offer.start.date"/>
                            </label>
                            <input type="date" name="offerStartDate" class="form-control" id="startDate"
                                   value="${specialOffer.startDate}"
                                   required
                                   pattern="^\d{4}-(02-(0[1-9]|[12][0-9])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$">
                        </div>
                        <div class="col-12">
                            <label for="expirationDate">
                                <fmt:message key="lang.special.offer.expiration.date"/></label>
                            <input type="date" name="offerExpirationDate" class="form-control" id="expirationDate"
                                   value="${specialOffer.expirationDate}"
                                   required
                                   pattern="^\d{4}-(02-(0[1-9]|[12][0-9])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$">
                        </div>
                        <div class="col-12 pb-2">
                            <label for="discount"><fmt:message key="lang.special.offer.discount"/> </label>
                            <input type="number" name="discount" class="form-control" id="discount"
                                   required
                                   value="${specialOffer.discount}"
                                   pattern="^[0-9][0-9]?$|^100$"
                                   min="0"
                                   max="100"
                                   maxlength="3">
                        </div>
                        <div class="col-12 pb-2">
                            <label for="image"><fmt:message key="lang.image.name"/> </label>
                            <input type="text" name="offerImage" class="form-control" id="image" required
                                   placeholder="image.png"
                                   value="${specialOffer.image}"
                                   pattern="^[\w_]+\.[A-Za-z]{3}$"
                                   maxlength="45">
                        </div>
                        <div class="col-12 pb-3">
                            <label for="offerDescription"><fmt:message key="lang.description"/></label>
                            <textarea name="offerDescription" class="form-control" id="offerDescription"
                                      required maxlength="2048"><c:out value="${specialOffer.description}"/></textarea>
                        </div>
                        <c:if test="${editError eq 'true'}">
                            <div class="alert alert-danger fade show pb-3" role="alert">
                                <fmt:message key="lang.special.offer.edit.error"/>
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
