package com.example.demo.service.house;

import com.example.demo.model.House;
import com.example.demo.model.extend.Search;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House> findAllByIsDeletedFalse();

    Iterable<House> findAllByOwnerIdAndDeletedFalse(long ownerId);

    Iterable<House> findAllByIsDeleteFalseOderByCreatedAt();

    Iterable<House> findHouseByBedroom(int quantity);

    Iterable<House> findHouseByBathroom(int quantity);

    Iterable<House> findHouseByAddressContaining(String address);

    List<House> searchHouse(Search search);

    Iterable<House> findHouseByPriceBetween(long from, long to);

    Iterable<House> findHouseByPriceIsGreaterThanEqual(long priceGreaterThan);

    Iterable<House> findBestHouses ();
}