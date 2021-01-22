package com.example.demo.service.booking;

import com.example.demo.model.Booking;
import com.example.demo.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBookingService extends IGeneralService<Booking> {
    Iterable<Booking> findBookingByHouseId(Long id);

    Iterable<Booking> findBookingByUserId(Long id);

    void delete(Long id);

    Iterable<Booking> findAllBookingNotRatedInThreeMonthsByUser (Long id);
}
