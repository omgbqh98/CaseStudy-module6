package com.example.demo.service.house;

import com.example.demo.model.House;
import com.example.demo.service.IGeneralService;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House> findAllByIsDeletedFalse();
    Iterable<House> findAllByOwnerIdAndDeletedFalse(long ownerId);
}
