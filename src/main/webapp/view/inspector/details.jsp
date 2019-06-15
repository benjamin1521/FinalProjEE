<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <div class="form-group mt-3">
                    <%--<form method="post">--%>

                        <c:choose>
                            <c:when test="${locale == 'ua'}">
                                <c:set var="userName" value="${report.inspectorId.fullNameUa}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="userName" value="${report.inspectorId.fullNameEn}"/>
                            </c:otherwise>
                        </c:choose>

                    <div class="form-group">
                        <fmt:message key="text.inspector"/>:
                        <c:out value="${userName}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.name"/>:
                        <c:out value="${report.name}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.address"/>:
                        <c:out value="${report.address}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.bank_name"/>:
                        <c:out value="${report.bank_name}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.bank_account"/>:
                        <c:out value="${report.bank_account}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.bank_bic"/>:
                        <c:out value="${report.bank_bic}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.code"/>:
                        <c:out value="${report.code}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.inn"/>:
                        <c:out value="${report.inn}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.kpp"/>:
                        <c:out value="${report.kpp}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.name_short"/>:
                        <c:out value="${report.name_short}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.oktmo"/>:
                        <c:out value="${report.oktmo}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.parent_address"/>:
                        <c:out value="${report.parent_address}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.parent_code"/>:
                        <c:out value="${report.parent_code}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.parent_name"/>:
                        <c:out value="${report.parent_name}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.parent_phone"/>:
                        <c:out value="${report.parent_phone}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.payment_name"/>:
                        <c:out value="${report.payment_name}"/>
                    </div>
                    <div class="form-group">
                        <fmt:message key="text.phone"/>:
                        <c:out value="${report.phone}"/>
                    </div>
                    <%--<div><fmt:message key="text.comment"/></div>--%>
                    <form method="post">
                        <div class="form-group">
                            <fmt:message key="text.comment"/>
                            <input type="text" class="form-control"
                                   name="comment" placeholder="comment"/>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="inspectorId" value="${report.inspectorId.id}"/>
                            <button name="command" value="Shift" type="submit" class="btn btn-primary">
                                <fmt:message key="text.shift"/></button>
                            <button name="command" value="Approve" type="submit" class="btn btn-primary">
                                <fmt:message key="text.approve"/></button>
                            <button name="command" value="Reject" class="btn btn-primary">
                                <fmt:message key="text.reject"/></button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <c:forEach var="a_mod" items="${mods}">

                    <c:choose>
                        <c:when test="${locale == 'ua'}">
                            <c:set var="userName" value="${a_mod.userId.fullNameUa}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="userName" value="${a_mod.userId.fullNameEn}"/>
                        </c:otherwise>
                    </c:choose>

                    <li class="list-group-item">
                        <span>${a_mod.comment}</span>
                        <div class="card-footer text-muted">

                            <span><c:out value="${userName}"/></span>
                            <span>${a_mod.date}</span>
                            <i>${a_mod.action}</i>
                        </div>
                    </li>

                </c:forEach>

                <div class="m-2">
                    <form method="post">
                        <input type="text" class="form-control" name="comment" placeholder="comment">
                        <div class="card-footer text-muted">
                            <button name="command" value="Comment" type="submit" class="btn btn-primary">
                                <fmt:message key="text.create"/></button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
