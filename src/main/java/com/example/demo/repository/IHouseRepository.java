package com.example.demo.repository;

import com.example.demo.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface IHouseRepository extends JpaRepository<House, Long> {

=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    // Tìm tất cả nhà trên hệ thống
    Iterable<House> findAllByIsDeletedFalse();

    // Tìm toàn bộ nhà bằng ownerId
    @Query(value = "select * from house where owner_Id_user_id = :id  and is_Deleted = 0",nativeQuery = true)
    Iterable<House> findAllByOwnerIdAndDeletedFalse(@Param("id") Long id);
>>>>>>> 909aa3511a1d73656efc9c0ee2571584615b74d9
}
