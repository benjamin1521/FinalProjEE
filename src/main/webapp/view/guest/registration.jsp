<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/footer.jsp"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/commons.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/guest/register.css">

    <title><fmt:message key="text.registration"/></title>
</head>
<body>

<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-body">
                <form method="post">
                    <div class="form-group">
                        <label><fmt:message key="text.username"/></label>
                        <input name="email" type="text" class="form-control" value="${email}">
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="text.fullname.ua"/></label>
                        <input name="fullNameUa" type="text" class="form-control" value="${fullNameUa}">
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="text.fullname.en"/></label>
                        <input name="fullNameEn" type="text" class="form-control" value="${fullNameEn}">
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="text.password"/></label>
                        <input name="password" type="password" class="form-control"/>
                    </div>
                    <input name="personType" type="hidden" value="${personType}">
                    <button name="command" value="register" type="submit" class="btn btn-primary"><fmt:message key="text.create"/></button>
                </form>
                <div class="info-message-label">
                    <c:if test="${not empty message}"><fmt:message key="${message}"/></c:if>
                </div>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/tax-system/guest"> < <fmt:message key="text.back"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
