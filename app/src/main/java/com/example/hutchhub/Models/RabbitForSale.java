package com.example.hutchhub.Models;

public class RabbitForSale {
    String address,breed,description,phone,price,quantity,sellerId;

    public RabbitForSale() {
    }

    public RabbitForSale(String address, String breed, String description, String phone, String price, String quantity, String sellerId) {
        this.address = address;
        this.breed = breed;
        this.description = description;
        this.phone = phone;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


}
