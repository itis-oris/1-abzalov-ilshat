<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/book/confirm" method="get">
    <input type="hidden" name="roomId" value="${room.id}">
    <button class="button" type="submit">Забронировать</button>
</form>