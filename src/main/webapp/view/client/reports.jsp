<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="btn-group btn-group-justified">
    <a href="${pageContext.request.contextPath}/app/client/reports?page=1" class="btn btn-info disabled">
        <fmt:message key="text.all"/></a>
    <a href="${pageContext.request.contextPath}/app/client/reports?page=1&status=Active" class="btn btn-info">
        <fmt:message key="text.active"/></a>
    <a href="${pageContext.request.contextPath}/app/client/reports?page=1&status=Rejected" class="btn btn-info">
        <fmt:message key="text.rejected"/></a>
    <a href="${pageContext.request.contextPath}/app/client/reports?page=1&status=Approved" class="btn btn-info">
        <fmt:message key="text.approved"/></a>
</div>
<c:choose>
    <c:when test="${noTaxReturns}">
        <p style="text-align: center; margin-top: 300px;"><fmt:message key="text.noreports"/></p>
    </c:when>
    <c:otherwise>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="text.name"/></th>
                <th><fmt:message key="text.status"/></th>
                <th><fmt:message key="text.links"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="report" items="${paginationReports.list}">
                <tr class="${report.status}">
                    <td><fmt:message key="${report.name}"/></td>
                    <td><fmt:message key="${report.status}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/app/client/details?id=${report.id}">
                            <fmt:message key="text.edit"/></a>
                    </td>
                </tr>
            </c:forEach>
            <form method="post">
                <tr>
                    <td>
                        <input type="text" class="form-control" name="name" placeholder=
                            <fmt:message key="text.name"/>>
                    </td>
                    <td>
                        <input type="text" class="form-control" name="comment" placeholder=
                            <fmt:message key="text.comment"/>>
                    </td>
                    <td>
                        <button name="command" value="createReport" type="submit" class="btn btn-primary">
                            <fmt:message key="text.create"/></button>
                    </td>
                </tr>
            </form>
            </tbody>
        </table>
        <div class="navigation-block">
            <c:out value="${response.firstNumber}"/>-<c:out value="${response.lastNumber}"/>
            <fmt:message key="text.from"/>
            <c:out value="${response.total}"/>
            <a href="${pageContext.request.contextPath}/app/client/reports?page=${param.page - 1}&status=${param.status}"><-</a>
            <a href="${pageContext.request.contextPath}/app/client/reports?page=${param.page + 1}&status=${param.status}">-></a>
        </div>
    </c:otherwise>
</c:choose>
</div>
