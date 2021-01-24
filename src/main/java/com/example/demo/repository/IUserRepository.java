package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query(value = "select * from user where user_id in (select owner_id_user_id from house group by owner_id_user_id);", nativeQuery = true)
    Iterable<User> findAllOwner();
}
