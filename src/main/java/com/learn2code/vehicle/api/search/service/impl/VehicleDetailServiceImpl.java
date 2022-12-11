package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dto.VehicleDetailsDTO;
import com.learn2code.vehicle.api.search.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public VehicleDetailsDTO getAllVehicleDetails() {
        return restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details",VehicleDetailsDTO.class);
    }
}
