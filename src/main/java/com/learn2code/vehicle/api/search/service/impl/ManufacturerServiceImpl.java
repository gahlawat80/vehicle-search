package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ManufacturerDAO;
import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerDAO.save(manufacturer);
    }

    @Override
    public List<Manufacturer> fetchAllManufacturers() {
        List<Manufacturer> dbManufacturers = manufacturerDAO.findAll();
        return dbManufacturers;
    }

    @Override
    public Manufacturer getManufacturerForId(int id) {
        Optional<Manufacturer> dbManufacturer = manufacturerDAO.findById(id);
        if(dbManufacturer.isPresent()){
            return dbManufacturer.get();
        }
        return null;
    }
}
