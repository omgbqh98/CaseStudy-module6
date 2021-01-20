package com.example.demo.repository;

import com.example.demo.model.House;
import com.example.demo.model.HouseImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    // Tìm tất cả nhà trên hệ thống
    Iterable<House> findAllByIsDeletedFalse();

    // Tìm toàn bộ nhà bằng ownerId
    @Query(value = "select * from house where owner_Id_user_id = :id  and is_Deleted = 0", nativeQuery = true)
    Iterable<House> findAllByOwnerIdAndDeletedFalse(@Param("id") Long id);

    // Tìm toàn bộ nhà mới đăng
    @Query(value = "select * from house  order by created_at desc;", nativeQuery = true)
    Iterable<House> findAllByIsDeletedFalseOderByCreatedAt();

    // tìm kiếm theo số lượng phòng ngủ
    Iterable<House> findHouseByBedroom(int quantity);

    //Tìm kiếm theo số lượng phòng tắm
    Iterable<House> findHouseByBathroom(int quantity);

    //Tìm kiếm địa chỉ
    Iterable<House> findHouseByAddressContaining(String address);

}