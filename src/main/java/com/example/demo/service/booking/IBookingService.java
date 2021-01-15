package com.example.demo.service.booking;

import com.example.demo.model.Booking;
import com.example.demo.service.IGeneralService;

public interface IBookingService extends IGeneralService<Booking> {
    Iterable<Booking> findBookingByHouseId(Long id);
}
