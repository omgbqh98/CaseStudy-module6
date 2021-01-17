package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;
    @NotEmpty
    private String houseName;
    @NotNull
    private int type; // 0: phòng đơn (Single), 1: phòng đôi (double), 2: phòng tổng thống (president), 3: phòng VIP, 4: phòng Luxury
    @NotEmpty
    private String address;
    @NotEmpty
    private String description;
    @NotNull
    private long price;
    @NotNull
    private int bedroom;
    @NotNull
    private int bathroom;
    @NotNull
    private int status; // 0: còn trống, 1: đã thuê, 2: đã checkin, 3: đang nâng cấp
    @ManyToOne
    private User ownerId;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isDeleted;
    private Timestamp createdAt;


    public House(@NotEmpty String houseName, @NotEmpty int type, @NotEmpty String address, @NotEmpty String description, long price, @NotEmpty int bedroom, @NotEmpty int bathroom, @NotEmpty int status, User ownerId, boolean isDeleted) {
        this.houseName = houseName;
        this.type = type;
        this.address = address;
        this.description = description;
        this.price = price;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.status = status;
        this.ownerId = ownerId;
        this.isDeleted = isDeleted;
    }

    public House() {
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
