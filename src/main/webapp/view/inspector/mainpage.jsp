<%@include file="../parts/imports.jsp" %>

<jsp:include page="../parts/header.jsp"/>
<jsp:include page="../parts/footer.jsp"/>


<div class="center-block">
    <div class="card">
        <div class="container">
            <p><div class="label d-inline"><fmt:message key="name.ua"/>:</div> <h6 class="d-inline"><c:out value="${user.fullNameUa}"/><br></h6></p>
            <p><div class="label d-inline"><fmt:message key="name.en"/>:</div> <h6 class="d-inline"><c:out value="${user.fullNameEn}"/></h6></p>
            <p><div class="label d-inline"><fmt:message key="email.address"/>:</div> <h6 class="d-inline"><c:out value="${user.email}"/></h6></p>
        </div>
    </div>
</div>


