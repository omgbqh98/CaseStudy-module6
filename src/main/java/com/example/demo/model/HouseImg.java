package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class HouseImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    @ManyToOne
    private House houseId;
    private String link;
    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean isAvatar;
}
