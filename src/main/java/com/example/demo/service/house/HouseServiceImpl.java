package com.example.demo.service.house;

import com.example.demo.model.Booking;
import com.example.demo.model.House;
import com.example.demo.model.extend.Search;
import com.example.demo.repository.IHouseRepository;
import com.example.demo.service.booking.BookingServiceImpl;
import com.example.demo.service.booking.IBookingService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    private IHouseRepository houseRepository;
    @Autowired
    private IBookingService iBookingService;

    @Override
    public Iterable<House> findAllByIsDeletedFalse() {
        return houseRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public Iterable<House> findAllByOwnerIdAndDeletedFalse(long ownerId) {
        return houseRepository.findAllByOwnerIdAndDeletedFalse(ownerId);
    }

    @Override
    public Iterable<House> findAllByIsDeleteFalseOderByCreatedAt() {
        return houseRepository.findAllByIsDeletedFalseOderByCreatedAt();
    }

    @Override
    public Iterable<House> findHouseByBedroom(int quantity) {
        return houseRepository.findHouseByBedroom(quantity);
    }

    @Override
    public Iterable<House> findHouseByBathroom(int quantity) {
        return houseRepository.findHouseByBathroom(quantity);
    }

    @Override
    public Iterable<House> findHouseByAddressContaining(String address) {
        return houseRepository.findHouseByAddressContaining(address);
    }

    @Override
    public Iterable<House> findHouseByPriceBetween(long from, long to) {
        return houseRepository.findHouseByPriceBetween(from, to);
    }

    @Override
    public Iterable<House> findHouseByPriceIsGreaterThanEqual(long priceGreaterThan) {
        return houseRepository.findHouseByPriceIsGreaterThanEqual(priceGreaterThan);
    }

    @Override
    public List<House> searchHouse(Search search) {
        List<House> houseList = new ArrayList<>();
        boolean mergeFirst = false;
        if (search.getBedroomQuantity() != -1) {
            List<House> houseListByBedroom = (List<House>) findHouseByBedroom(search.getBedroomQuantity());
            if (!mergeFirst) {
                mergeFirst = true;
                houseList = houseListByBedroom;
            }
        }
        if (search.getBathroomQuantity() != -1) {
            List<House> houseListByBathroom = (List<House>) findHouseByBathroom(search.getBathroomQuantity());
            if (!mergeFirst) {
                mergeFirst = true;
                houseList = houseListByBathroom;
            } else {
                houseList = mergeList(houseList, houseListByBathroom);

            }
        }
        if (!search.getAddress().equals("")) {
            List<House> houseListAddress = (List<House>) findHouseByAddressContaining(search.getAddress());
            if (!mergeFirst) {
                mergeFirst = true;
                houseList = houseListAddress;
            } else {

                houseList = mergeList(houseList, houseListAddress);

            }
        }
        if (search.getIdPrice() != -1) {
            if (search.getIdPrice() != 5) {
                List<House> listByPriceFromTo;
                switch (search.getIdPrice()) {
                    case 1:
                        listByPriceFromTo = (List<House>) findHouseByPriceBetween(0, 50);
                        break;
                    case 2:
                        listByPriceFromTo = (List<House>) findHouseByPriceBetween(50, 100);
                        break;
                    case 3:
                        listByPriceFromTo = (List<House>) findHouseByPriceBetween(100, 500);
                        break;
                    case 4:
                        listByPriceFromTo = (List<House>) findHouseByPriceBetween(500, 1000);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + search.getIdPrice());
                }
                if (!mergeFirst) {
                    mergeFirst = true;
                    houseList = listByPriceFromTo;
                } else {
                    houseList = mergeList(houseList, listByPriceFromTo);
                }
            } else {
                List<House> houseByPriceGreaterThan = (List<House>) houseRepository.findHouseByPriceIsGreaterThanEqual(1000);
                if (!mergeFirst) {
                    mergeFirst = true;
                    houseList = houseByPriceGreaterThan;
                } else {
                    houseList = mergeList(houseList, houseByPriceGreaterThan);
                }
            }

        }
        if (search.getCheckIn() != null && search.getCheckOut() != null) {
            List<House> searchByHouse = new ArrayList<>();
            if (!mergeFirst) {
                mergeFirst = true;
                List<House> listAll = (List<House>) findAll();
                houseList = checkSearchByDate(listAll, search.getCheckIn(), search.getCheckOut());
            } else {
                searchByHouse = checkSearchByDate(houseList, search.getCheckIn(), search.getCheckOut());
                houseList = mergeList(houseList, searchByHouse);
            }

        }
        if (!mergeFirst) {
            houseList = (List<House>) findAll();
        }
        return houseList;
    }


    public List<House> mergeList(List<House> listFirst, List<House> listSecond) {
        List<House> list = new ArrayList<>();
        for (int i = 0; i < listFirst.size(); i++) {
            for (int j = 0; j < listSecond.size(); j++) {
                if (listFirst.get(i).getHouseId() == listSecond.get(j).getHouseId()) {
                    list.add(listFirst.get(i));
                }
            }
        }
        return list;
    }

    public int count(List<Booking> list) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            index += 1;
        }
        return index;
    }

    public List<House> checkSearchByDate(List<House> listInput, Date checkIn, Date checkOut) {
        List<House> listOutput = new ArrayList<>();
        int count = 0;
        boolean isHas = false;
        for (int i = 0; i < listInput.size(); i++) {
            for (int j = 0; j < count((List<Booking>) iBookingService.findBookingByHouseId(listInput.get(i).getHouseId())); j++) {
                Date indexStart = ((List<Booking>) iBookingService.findBookingByHouseId(listInput.get(i).getHouseId())).get(j).getCheckIn();
                Date indexEnd = ((List<Booking>) iBookingService.findBookingByHouseId(listInput.get(i).getHouseId())).get(j).getCheckOut();
                if (checkIn.after(indexEnd) || checkIn.toString().equals(indexEnd.toString())) {
                    isHas = true;
                    count += 1;
                }
                if (checkOut.before(indexStart) || checkOut.toString().equals(indexStart.toString())) {
                    count += 1;
                    isHas = true;
                }
                if (!isHas) {
                    break;
                }
            }
            if (count == count((List<Booking>) iBookingService.findBookingByHouseId(listInput.get(i).getHouseId()))) {
                listOutput.add(listInput.get(i));
            }
            count = 0;
            isHas = false;
        }

        return listOutput;
    }

    @Override
    public Iterable<House> findBestHouses() {
        return houseRepository.findBestHouses();
    }
}