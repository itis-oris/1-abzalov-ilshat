package com.heartoftatarstan.models;

public class Room {
    private int id;
    private String name;
    private String description;
    private int capacity;
    private double price;
    private String image_url;

    public Room(int id, String name, String description, int capacity, double price, String image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.price = price;
        this.image_url = image_url;
    }

    public String getDescription() { return description; }
    public String getImage_url() { return image_url; }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", description=" + description +
                '}';
    }
}
