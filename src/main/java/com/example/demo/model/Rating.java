package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @ManyToOne
    private User userId;
    @ManyToOne
    private House houseId;
//    @Min(1)
//    @Max(5)
    @NotEmpty
    private int rate;
    private String review;
    private long parentId;
    private Timestamp createdAt;
}
