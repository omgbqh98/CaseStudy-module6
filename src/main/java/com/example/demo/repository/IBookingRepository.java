package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {
    @Query(value = "select * from booking where house_id_house_id = :id order by check_in desc;",nativeQuery = true)
    Iterable<Booking> findBookingByHouseId(@Param("id") Long id);
}
