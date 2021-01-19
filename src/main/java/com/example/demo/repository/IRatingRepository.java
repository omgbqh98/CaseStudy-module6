package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
    // Lấy tất cả rating
    Iterable<Rating> findAllByHouseId_HouseIdOrderByCreatedAtDesc(Long houseId);

    // Lấy tất cả feedback cha
    @Query(value = "select * from rating where rating_id = parent_id AND house_id_house_id = :id order by created_at desc;",nativeQuery = true)
    Iterable<Rating> findAllParentRatingByHouse(@Param("id") Long id);


    // Lấy tất cả feedback con theo feedback cha
    @Query(value = "select * from rating where parent_id = :? and rating_id != parent_id order by created_at asc;", nativeQuery = true)
    Iterable<Rating> findAllChildRatingByParentRating(@Param("id") Long id);

}
