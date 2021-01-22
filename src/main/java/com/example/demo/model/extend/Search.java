package com.example.demo.model.extend;

import java.sql.Date;

public class Search {
    private int bedroomQuantity;
    private int bathroomQuantity;
    private String address;
    private int idPrice;
    private Date checkIn;
    private Date checkOut;

    public Search() {
    }

    public int getBedroomQuantity() {
        return bedroomQuantity;
    }

    public void setBedroomQuantity(int bedroomQuantity) {
        this.bedroomQuantity = bedroomQuantity;
    }

    public int getBathroomQuantity() {
        return bathroomQuantity;
    }

    public void setBathroomQuantity(int bathroomQuantity) {
        this.bathroomQuantity = bathroomQuantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
