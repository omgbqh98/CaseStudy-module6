package com.example.demo.service.booking;

import com.example.demo.model.Booking;
import com.example.demo.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService{
    @Autowired
    private IBookingRepository bookingRepository;
    @Override
    public Iterable<Booking> findBookingByHouseId(Long id) {
        return bookingRepository.findBookingByHouseId(id);
    }

    @Override
    public Iterable<Booking> findAll() {
        return null;
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Booking> findBookingByUserId(Long id) {
        return bookingRepository.findBookingByUserId(id);
    }
}
