package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
    // Lấy tất cả rating
    Iterable<Rating> findAllByHouseId_HouseIdOrderByCreatedAtDesc(Long houseId);

    // Lấy tất cả feedback CHA
    @Query(value = "select * from rating where rating_id = parent_id AND house_id_house_id = :id order by created_at desc;",nativeQuery = true)
    Iterable<Rating> findAllParentRatingByHouse(@Param("id") Long id);

    // Lấy tất cả feedback CON
    @Query(value = "select * from rating where rating_id != parent_id AND house_id_house_id = :id order by created_at asc;",nativeQuery = true)
    Iterable<Rating> findAllChildRatingByHouse(@Param("id") Long id);

    // Lấy tất cả feedback con theo feedback cha
    @Query(value = "select * from rating where parent_id = :id and rating_id != parent_id order by created_at asc;", nativeQuery = true)
    Iterable<Rating> findAllChildRatingByParentRating(@Param("id") Long id);

    // Lấy tất cả rating, sắp xếp theo parentId
    @Query(value = "select * from rating where house_id_house_id = :id order by parent_id desc;", nativeQuery = true)
    Iterable<Rating> findAllByHouseIdParentIdDesc(@Param("id") Long id);


    // Lấy tất cả những người đã book nhà
    @Query(value = "SELECT  user_id_user_id from booking where house_id_house_id = :id AND check_out >= CURDATE();", nativeQuery = true)
    Iterable<BigInteger> findCheckoutUserByHouse(@Param("id") Long id);

    // Lấy tất cả những người đã book + checkout + đã rate
    @Query(value="SELECT rating.user_id_user_id from rating INNER JOIN booking ON rating.user_id_user_id = booking.user_id_user_id where booking.house_id_house_id = :id AND booking.check_out >= CURDATE() AND rating.parent_id = rating.rating_id group by rating.user_id_user_id;", nativeQuery = true)
    Iterable<BigInteger> findCheckedOutAndRatedUserByHouse(@Param("id") Long id);
}
