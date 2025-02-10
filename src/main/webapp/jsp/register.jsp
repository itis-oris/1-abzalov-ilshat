<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="form-container">
    <h2>Регистрация</h2>
    <form action="/auth/register" method="post">
        <label for="first-name">Имя:</label>
        <input type="text" id="first-name" name="firstName" required>

        <label for="last-name">Фамилия:</label>
        <input type="text" id="last-name" name="lastName" required>

        <label for="email">Электронная почта:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Зарегистрироваться</button>
    </form>
</div>


<%@ include file="footer.jsp" %>