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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public House getHouseId() {
        return houseId;
    }

    public void setHouseId(House houseId) {
        this.houseId = houseId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isAvatar() {
        return isAvatar;
    }

    public void setAvatar(boolean avatar) {
        isAvatar = avatar;
    }
}
