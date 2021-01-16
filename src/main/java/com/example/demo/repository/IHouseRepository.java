package com.example.demo.repository;

import com.example.demo.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHouseRepository extends JpaRepository<House, Long> {

}
