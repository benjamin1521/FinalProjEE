<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="${report.category}"/></h5>
                <p class="intend-paragraph card-text">
                <p><fmt:message key="${report.status}"/> <fmt:parseDate value="${report.lastUpdate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" /> <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></p>
                <p><div class="label d-inline"><fmt:message key="text.client"/>: </div> <div class="d-inline"><c:out value="${customerName}"/></div></p>
                <p><div class="label d-inline"><fmt:message key="text.inspector"/>: </div> <div class="d-inline"><c:out value="${inspectorName}" /></div></p>
                </p>
                <form class="form-group" method="post" action="${pageContext.request.contextPath}/download/tax-return">
                    <input type="hidden" name="reportId" value="${param.id}">
                    <input type="hidden" name="customerName" value="${report.customer.fullNameEn}">
                    <button type="submit" class="btn btn-link"><fmt:message key="download.tax.return"/></button>
                </form>

                <form class="form-group" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="input-default-wrapper mt-3">
                            <input type="file" name="file" id="file-with-current" class="input-default-js">
                            <label class="label-for-default-js rounded-right mb-3" for="file-with-current">
                                <span class="span-choose-file"><fmt:message key="choose.a.file"/></span>
                                <div class="float-right span-browse"><fmt:message key="browse"/></div>
                            </label>
                        </div>
                    </div>
                    <input type="hidden" name="lastUpdateId" value="${lastUpdateId}">
                    <button type="submit" class="btn btn-primary" <c:if test="${disableUploadButton}">disabled</c:if>><fmt:message key="file.tax.return"/></button>
                </form>
                <form class="form-group" method="post">
                    <input type="hidden" name="lastUpdateId" value="${lastUpdateId}">
                    <button name="command" value="replaceInspector" type="submit" class="btn btn-warning" <c:if test="${disableReplaceInspectorButton}">disabled</c:if>><fmt:message key="replace.inspector"/></button>
                </form>
                <div class="info-message-label">
                    <c:if test="${not empty message}"><fmt:message key="${message}"/></c:if>
                </div>
            </div>
        </div>

        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <h5 class="card-title"><fmt:message key="tax.return.history"/>:</h5>
                <c:forEach var="update" items="${report.updates}">
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
