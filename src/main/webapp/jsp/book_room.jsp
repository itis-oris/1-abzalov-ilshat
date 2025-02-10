<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="form-container">
    <h2>Бронирование номера</h2>

    <c:if test="${not empty successMessage}">
        <div class="success-message">${successMessage}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form action="/book/confirm" method="post">
        <label for="roomType">Выберите тип номера:</label>
        <select name="roomType" id="roomType" required>
            <c:forEach var="room" items="${rooms}">
                <option value="${room.id}">${room.name}</option>
            </c:forEach>
        </select>

        <label for="checkInDate">Дата заселения:</label>
        <input type="date" id="checkInDate" name="checkInDate" required>

        <label for="checkOutDate">Дата выселения:</label>
        <input type="date" id="checkOutDate" name="checkOutDate" required>

        <button type="submit">Подтвердить бронирование</button>
    </form>
</div>

<%@ include file="footer.jsp" %>
