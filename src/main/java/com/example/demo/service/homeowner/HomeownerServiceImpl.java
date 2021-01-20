package com.example.demo.service.homeowner;

import com.example.demo.model.Homeowner;
import com.example.demo.repository.IHomeownerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeownerServiceImpl implements IHomeownerService {
    @Autowired
    IHomeownerRepository iHomeownerRepository;

    @Override
    public Iterable<Homeowner> findAll() {
        return iHomeownerRepository.findAll();
    }

    @Override
    public Homeowner save(Homeowner homeowner) {
        return iHomeownerRepository.save(homeowner);
    }

    @Override
    public Optional<Homeowner> findById(Long id) {
        return iHomeownerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iHomeownerRepository.deleteById(id);
    }
}
