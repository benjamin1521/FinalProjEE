<%@include file="imports.jsp"%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/commons.css">

    <title><fmt:message key="text.profile"/></title>
</head>
<body>

<c:choose>
<c:when test="${locale == 'ua'}">
    <c:set var="userName" value="${user.fullNameUa}"/>
</c:when>
<c:otherwise>
    <c:set var="userName" value="${user.fullNameEn}"/>
</c:otherwise>
</c:choose>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/client"><fmt:message key="text.profile"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/client/reports"><fmt:message key="text.myreports"/></a>
                </li>
                <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="${pageContext.request.contextPath}/app/client/new-tax-return"><fmt:message key="new.tax.return"/></a>--%>
                <%--</li>--%>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/client"><c:out value="${userName}" /></a>
                </li>
                <li class="nav-item">
                    <form class="form-inline my-2 my-lg-0">
                        <button class="btn" type="submit" name="command" value="logout"><fmt:message key="text.logout"/></button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>