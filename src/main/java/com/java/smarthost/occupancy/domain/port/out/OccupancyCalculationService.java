package com.java.smarthost.occupancy.domain.port.out;

import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyResponse;

import java.util.List;

public interface OccupancyCalculationService {

    CalculateOccupancyResponse calculatePremiumUsage(int noOfPremiumRooms, List<Double> prices);

    CalculateOccupancyResponse calculateEconomyUsage(int noOfEconomyRooms, List<Double> prices);

}
