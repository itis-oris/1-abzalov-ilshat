CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    room_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    CONSTRAINT fk_user_email FOREIGN KEY (email) REFERENCES users(email) ON DELETE CASCADE,
    CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);