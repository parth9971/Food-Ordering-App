package com.example.fastfood.Models;

public class ordersModel {

    int orderImage;
    String soldItemName, price, orderName;

    public ordersModel() {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderName = orderName;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
