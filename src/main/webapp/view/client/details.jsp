<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>

<div class="container">
    <div class="row">
        <div class="card col-6" style="margin-top: 10px;">
            <div class="card-body">
                <div class="form-group mt-3">
                    <form method="post">

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
                            <div><fmt:message key="text.name"/></div>
                            <input type="text" class="form-control"
                                   value="${report.name}" name="name" placeholder="name">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.address"/></div>
                            <input type="text" class="form-control"
                                   value="${report.address}" name="address"
                                   placeholder="address">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.bank_name"/></div>
                            <input type="text" class="form-control"
                                   value="${report.bank_name}" name="bank_name"
                                   placeholder="bank_name">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.bank_account"/></div>
                            <input type="text" class="form-control"
                                   value="${report.bank_account}" name="bank_account"
                                   placeholder="bank_account">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.bank_bic"/></div>
                            <input type="text" class="form-control"
                                   value="${report.bank_bic}" name="bank_bic"
                                   placeholder="bank_bic">
                        </div>

                        <div class="form-group">
                            <div><fmt:message key="text.code"/></div>
                            <input type="text" class="form-control"
                                   value="${report.code}" name="code" placeholder="code">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.inn"/></div>
                            <input type="text" class="form-control"
                                   value="${report.inn}" name="inn" placeholder="inn">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.kpp"/></div>
                            <input type="text" class="form-control"
                                   value="${report.kpp}" name="kpp" placeholder="kpp">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.name_short"/></div>
                            <input type="text" class="form-control"
                                   value="${report.name_short}" name="name_short"
                                   placeholder="name_short">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.oktmo"/></div>
                            <input type="text" class="form-control"
                                   value="${report.oktmo}" name="oktmo" placeholder="oktmo">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.parent_address"/></div>
                            <input type="text" class="form-control"
                                   value="${report.parent_address}"
                                   name="parent_address"
                                   placeholder="parent_address">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.parent_code"/></div>
                            <input type="text" class="form-control"
                                   value="${report.parent_code}" name="parent_code"
                                   placeholder="parent_code">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.parent_name"/></div>
                            <input type="text" class="form-control"
                                   value="${report.parent_name}" name="parent_name"
                                   placeholder="parent_name">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.parent_phone"/></div>
                            <input type="text" class="form-control"
                                   value="${report.parent_phone}" name="parent_phone"
                                   placeholder="parent_phone">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.payment_name"/></div>
                            <input type="text" class="form-control"
                                   value="${report.payment_name}" name="payment_name"
                                   placeholder="payment_name">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.phone"/></div>
                            <input type="text" class="form-control"
                                   value="${report.phone}" name="phone" placeholder="phone">
                        </div>
                        <div class="form-group">
                            <div><fmt:message key="text.comment"/></div>
                            <input type="text" class="form-control"
                                   name="comment" placeholder="comment"/>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="inspectorId" value="${report.inspectorId.id}"/>
                            <button name="command" value="updateReport" type="submit" class="btn btn-primary">
                                <fmt:message key="text.save"/></button>
                            <button name="command" value="deleteReport" type="submit" class="btn btn-primary">
                                <fmt:message key="text.delete"/></button>
                            <button name="command" value="changeInspector" class="btn btn-primary">
                                <fmt:message key="text.change"/></button>
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
