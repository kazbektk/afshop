package com.epam.afshop.entity;

public class OrderInfo {
    private int id;
    private int userId;
    private String dateOfOrder;
    private String dateOfSending;
    private int deliveryMethodId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(String dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public int getDeliveryMethodId() {
        return deliveryMethodId;
    }

    public void setDeliveryMethodId(int deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
