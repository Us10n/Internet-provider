<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <jsp:include page="fragment/links.jsp"/>
    <jsp:include page="fragment/header.jsp"/>
    <title>Balance recharge</title>
</head>
<body>

<div class="modal position-static d-block bg-light py-5" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content rounded-5 shadow">
            <div class="modal-header p-5 pb-2 border-bottom-0">
                <h2 class="fw-bold mb-0"><fmt:message key="lang.recharge.balance"/></h2>
            </div>
            <div class="modal-body p-5 pt-0">
                <form action="${pageContext.request.contextPath}/controller?command=rechargeBalance"
                      method="post">
                    <div class="form-floating pb-2">
                        <input type="text" class="form-control rounded-4" name="rechargeAmount"
                               required min="0"
                               pattern="^[0-9]+(\.[0-9]{1,2})?$"
                               maxlength="8"
                               placeholder="<fmt:message key="lang.amount"/>">
                    </div>
                    <c:if test="${rechargeError eq 'true'}">
                        <div class="alert alert-danger fade show pb-2" role="alert">
                            <fmt:message key="lang.recharge.error"/>
                        </div>
                    </c:if>
                    <button class="w-100 mb-2 btn btn-lg rounded-4 btn-info" type="submit">
                        <fmt:message key="lang.submit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
