package com.example.demo.repository;

import com.example.demo.model.Homeowner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHomeownerRepository extends CrudRepository<Homeowner,Long> {
}
