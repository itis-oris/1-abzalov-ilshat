<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="header.jsp" %>

<div class="form-container">
    <h2>Вход</h2>
    <c:if test="${not empty error}">
        <div class="error-message">
            <c:out value="${error}" escapeXml="false" />
        </div>
    </c:if>
    <form action="/auth/login" method="post">
        <label for="email">Электронная почта:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Войти</button>
    </form>
</div>

<%@ include file="footer.jsp" %>