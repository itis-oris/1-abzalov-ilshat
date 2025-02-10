<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="room-details">
  <h2>${room.name}</h2>
  <img src="${room.image_url}" alt="${room.name}">
  <p><strong>Описание:</strong> ${room.description}</p>
  <p><strong>Вместимость:</strong> ${room.capacity} чел.</p>
  <p><strong>Цена за ночь:</strong> ${room.price} руб.</p>
  <jsp:include page="ui/button.jsp" />
</div>

<%@ include file="footer.jsp" %>
