package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;
    @NotEmpty
    private String houseName;
    @NotEmpty
    private int type; // 0: phòng đơn (Single), 1: phòng đôi (double), 2: phòng tổng thống (president), 3: phòng VIP, 4: phòng Luxury
    @NotEmpty
    private String address;
    @NotEmpty
    private String description;
    @NotEmpty
    private long price;
    @NotEmpty
    private int bedroom;
    @NotEmpty
    private int bathroom;
    @NotEmpty
    private int status; // 0: còn trống, 1: đã thuê, 2: đang nâng cấp
    @ManyToOne
    private User ownerId;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isDeleted;
}
