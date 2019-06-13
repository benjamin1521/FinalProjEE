<%@include file="imports.jsp" %>


<div class="panel panel-default">
    <div class="panel-body">
        <div class="change-language-container">
            <form method="post">
                <div class="select-container">
                    <select class="form-control" name="locale">
                        <option value="ua" <c:if test="${locale == 'ua'}"> selected </c:if>>
                            <fmt:message key="text.ukrainian"/></option>
                        <option value="en" <c:if test="${locale == 'en'}"> selected </c:if>>
                            <fmt:message key="text.english"/></option>
                    </select>
                </div>
                <button name="command" value="changeLanguage" type="submit" class="btn btn-default">
                    <fmt:message key="text.apply"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

