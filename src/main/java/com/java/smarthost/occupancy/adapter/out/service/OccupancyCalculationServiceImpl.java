package com.java.smarthost.occupancy.adapter.out.service;

import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyResponse;
import com.java.smarthost.occupancy.domain.port.out.OccupancyCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupancyCalculationServiceImpl implements OccupancyCalculationService {


    @Override
    public CalculateOccupancyResponse calculatePremiumUsage(int noOfPremiumRooms, List<Double> prices) {
        return CalculateOccupancyResponse.builder().build();
    }

    @Override
    public CalculateOccupancyResponse calculateEconomyUsage(int noOfEconomyRooms, List<Double> prices) {
        return CalculateOccupancyResponse.builder().build();
    }
}
