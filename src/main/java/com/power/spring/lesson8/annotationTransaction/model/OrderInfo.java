package com.power.spring.lesson8.annotationTransaction.model;

import java.io.Serializable;

public class OrderInfo implements Serializable {
    private Integer id;

    private String productName;

    private Double price;

    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}