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

    @Query(value = "select * from booking where user_id_user_id = :id order by created_at asc;",nativeQuery = true)
    Iterable<Booking> findBookingByUserId(@Param("id") Long id);

    // Lấy tất cả những booking đã checkout của một người
    @Query(value = "select * from booking where check_out <= CURDATE() AND user_id_user_id = :id;", nativeQuery = true)
    Booking findCheckedOutBookingByUser(@Param("id") Long id);

    // Lấy tất cả những booking đã checkout nhưng chưa rate trong 3 tháng gần nhất
    @Query(value = "SELECT * FROM booking where check_out < curdate() AND is_rated = 0 AND datediff (curdate(), check_out) < 90 AND user_id_user_id = :id;", nativeQuery = true)
    Iterable<Booking> findAllBookingNotRatedInThreeMonths (@Param("id") Long id);
}
