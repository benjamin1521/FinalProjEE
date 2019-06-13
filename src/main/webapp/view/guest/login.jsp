<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/footer.jsp"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/commons.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/guest/login.css">

    <title><fmt:message key="text.login"/></title>
</head>
<body>

<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-body">
                <form method="post">
                    <div class="form-group">
                        <label><fmt:message key="text.username"/></label>
                        <input name="username" autocomplete="off" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="text.password"/></label>
                        <input name="password" autocomplete="off" type="password" class="form-control">
                    </div>
                    <button name="command" value="login" type="submit" class="btn btn-primary"><fmt:message
                            key="text.login"/></button>
                </form>
                <div class="info-message-label">
                    <c:if test="${not empty message}"><fmt:message key="${message}"/></c:if>
                </div>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/app/guest/registration"><fmt:message
                            key="text.registration"/></a>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<div class="panel panel-default">--%>
<%--<div class="panel-body">--%>
<%--<div class="change-language-container">--%>
<%--<form method="post">--%>
<%--<div class="select-container">--%>
<%--<select class="form-control" name="locale">--%>
<%--<option value="ua" <c:if test="${locale == 'ua'}"> selected </c:if>><fmt:message--%>
<%--key="text.ukrainian"/></option>--%>
<%--<option value="en" <c:if test="${locale == 'en'}"> selected </c:if>><fmt:message--%>
<%--key="text.english"/></option>--%>
<%--</select>--%>
<%--</div>--%>
<%--<button name="command" value="changeLanguage" type="submit" class="btn btn-default"><fmt:message--%>
<%--key="text.apply"/></button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>