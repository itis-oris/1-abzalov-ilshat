<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<section class="welcome-section">
    <h1>Добро пожаловать в "Сердце Татарстана"!</h1>
    <p>Мы рады предложить вам лучшие условия для отдыха и работы в самом сердце региона.</p>
</section>

<section class="rooms-section">
    <h2>Доступные номера</h2>
    <div id="rooms-container" class="rooms-list">
        <c:forEach var="room" items="${rooms}">
            <div class="room-card">
                <a href="/rooms/details?roomId=${room.id}">
                    <img src="${room.image_url}" alt="${room.name}">
                    <h3>${room.name}</h3>
                    <p>Вместимость: ${room.capacity} чел.</p>
                    <p>Цена за ночь: ${room.price} руб.</p>
                    <jsp:include page="ui/button.jsp" />
                </a>
            </div>
        </c:forEach>
    </div>
    <button id="load-more-button" class="button">Показать ещё</button>
</section>

<script src="/js/loadRooms.js"></script>

<jsp:include page="footer.jsp" />
