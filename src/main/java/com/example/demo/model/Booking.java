package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    private House houseId;
    @ManyToOne
    private User userId;
    @NotEmpty
    private Date checkIn;
    @NotEmpty
    private Date checkOut;
    @NotEmpty
    private long total;
    private Timestamp createdAt;
}
