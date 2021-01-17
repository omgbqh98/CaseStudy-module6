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
}
