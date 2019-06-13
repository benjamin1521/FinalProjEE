<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>


<div class="table-container">
    <div class="btn-group btn-group-justified">
        <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns?page=1" class="btn btn-info disabled"><fmt:message key="all"/></a>
        <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns/at-the-inspection?page=1" class="btn btn-info"><fmt:message key="AT_THE_INSPECTION"/></a>
        <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns/changes-requested?page=1" class="btn btn-info"><fmt:message key="CHANGES_REQUESTED"/></a>
        <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns/rejected?page=1" class="btn btn-info"><fmt:message key="REJECTED"/></a>
        <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns/approved?page=1" class="btn btn-info"><fmt:message key="APPROVED"/></a>
    </div>
    <c:choose>
        <c:when test="${noTaxReturns}">
            <p style="text-align: center; margin-top: 300px;"><fmt:message key="no.tax.returns"/></p>
        </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="customer"/></th>
                    <th><fmt:message key="category"/></th>
                    <th><fmt:message key="last.update"/></th>
                    <th><fmt:message key="status"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="taxReturn" items="${response.list}">
                    <tr class="${taxReturn.status}">
                        <td>
                            <c:if test="${locale == 'ua'}"><c:out value="${taxReturn.customer.fullNameUa}"/></c:if>
                            <c:if test="${locale == 'en'}"><c:out value="${taxReturn.customer.fullNameEn}"/></c:if>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns/get?id=${taxReturn.id}"><fmt:message key="${taxReturn.category}"/></a></td>
                        <td>
                            <fmt:parseDate value="${taxReturn.lastUpdate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                        </td>
                        <td><fmt:message key="${taxReturn.status}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="navigation-block">
                <c:out value="${response.firstNumber}"/>-<c:out value="${response.lastNumber}"/> <fmt:message key="from"/> <c:out value="${response.total}"/>
                <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns?page=${param.page - 1}">❮</a>
                <a href="${pageContext.request.contextPath}/tax-system/inspector/tax-returns?page=${param.page + 1}">❯</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
