package com.example.demo.repository;

import com.example.demo.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    // Tìm tất cả nhà trên hệ thống
    Iterable<House> findAllByIsDeletedFalse();
    // Tìm bằng ownerId
    @Query(value = "select * from house where owner_Id_user_id = :id  and is_Deleted = 0",nativeQuery = true)
    Iterable<House> findAllByOwnerIdAndDeletedFalse(@Param("id") Long id);
}
