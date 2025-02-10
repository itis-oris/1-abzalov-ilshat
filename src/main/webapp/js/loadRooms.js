document.addEventListener("DOMContentLoaded", () => {
    const roomsContainer = document.getElementById("rooms-container");
    const loadMoreButton = document.getElementById("load-more-button");

    let offset = 4; // Начальный отступ (первые 4 номера уже загружены)
    const limit = 4; // Количество записей для подгрузки

    loadMoreButton.addEventListener("click", () => {
        fetch(`/rooms/load?limit=${limit}&offset=${offset}`)
            .then(response => response.json())
            .then(data => {
                if (data.length < limit) {
                    loadMoreButton.style.display = "none"; // Скрываем кнопку, если записей меньше, чем limit
                }

                data.forEach(room => {
                    const roomCard = document.createElement("div");
                    roomCard.className = "room-card";
                    roomCard.innerHTML = `
                        <a href="/rooms/details?roomId=${room.id}">
                            <img src="${room.image_url}" alt="${room.name}">
                            <h3>${room.name}</h3>
                            <p>Вместимость: ${room.capacity} чел.</p>
                            <p>Цена за ночь: ${room.price} руб.</p>
                            <form action="/book/confirm" method="get">
                                <input type="hidden" name="roomId" value="${room.id}">
                                <button class="button" type="submit">Забронировать</button>
                            </form>
                        </a>
                    `;
                    roomsContainer.appendChild(roomCard);
                });

                offset += limit; // Увеличиваем отступ
            })
            .catch(error => console.error("Ошибка загрузки номеров:", error));
    });
});
