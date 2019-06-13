<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="${taxReturn.category}"/></h5>
                <p class="intend-paragraph card-text">
                <p><fmt:message key="${taxReturn.status}"/> <fmt:parseDate value="${taxReturn.lastUpdate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" /> <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></p>
                <p><div class="label d-inline"><fmt:message key="customer"/>: </div> <div class="d-inline"><c:out value="${customerName}"/></div></p>
                <p><div class="label d-inline"><fmt:message key="inspector"/>: </div> <div class="d-inline"><c:out value="${inspectorName}" /></div></p>
                </p>
                <form class="form-group" method="post" action="${pageContext.request.contextPath}/download/tax-return">
                    <input type="hidden" name="taxReturnId" value="${param.id}">
                    <input type="hidden" name="customerName" value="${taxReturn.customer.fullNameEn}">
                    <button type="submit" class="btn btn-link"><fmt:message key="download.tax.return"/></button>
                </form>

                <form class="form-group" method="post">
                    <input type="hidden" name="lastUpdateId" value="${lastUpdateId}">
                    <button name="command" value="APPROVE" type="submit" class="btn btn-success" <c:if test="${disableInspectorButtons}">disabled</c:if>><fmt:message key="approve"/></button>
                </form>

                <form class="form-group" method="post">
                    <input type="hidden" name="lastUpdateId" value="${lastUpdateId}">
                    <textarea required="required" class="form-control" rows="5" name="message" placeholder="<fmt:message key="input.message.for.customer"/>"></textarea>
                    <button style="margin-top: 10px;" name="command" value="REQUEST_CHANGES" type="submit" class="btn btn-primary" <c:if test="${disableInspectorButtons}">disabled</c:if>><fmt:message key="request.changes"/></button>
                    <button style="margin-top: 10px;" name="command" value="REJECT" type="submit" class="btn btn-danger" <c:if test="${disableInspectorButtons}">disabled</c:if>><fmt:message key="reject"/></button>
                </form>
                <div class="info-message-label">
                    <c:if test="${not empty message}"><fmt:message key="${message}"/></c:if>
                </div>
            </div>
        </div>

        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="tax.return.history"/>:</h5>
                <c:forEach var="update" items="${taxReturn.updates}">
                    <c:if test = "${locale == 'ua'}"><c:set var="userName" value="${update.user.fullNameUa}"/></c:if>
                    <c:if test = "${locale == 'en'}"><c:set var="userName" value="${update.user.fullNameEn}"/></c:if>

                    <h6 class="${update.action}">
                        <c:out value="${userName}"/>
                        <fmt:message key="${update.action}"/>
                        <fmt:parseDate value="${update.date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                    </h6>
                    <c:if test="${not empty update.message}"><p class="intend-paragraph ${update.action}"><c:out value="${update.message}"/></p></c:if>

                </c:forEach>
            </div>
        </div>
    </div>
</div>
