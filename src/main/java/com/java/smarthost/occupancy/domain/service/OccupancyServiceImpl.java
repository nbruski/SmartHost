package com.java.smarthost.occupancy.domain.service;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.domain.port.out.OccupancyCalculationService;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.port.in.OccupancyService;
import com.java.smarthost.occupancy.domain.service.validation.OccupancyValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OccupancyServiceImpl implements OccupancyService {
    private final OccupancyValidationService occupancyValidationService;
    private final OccupancyCalculationService occupancyCalculationService;

    @Override
    public BookedRoomsWithIncomeResponse calculateIncomeAndUsage(Occupancy occupancy) {
        this.occupancyValidationService.validateOccupancy(occupancy);
        return BookedRoomsWithIncomeResponse.builder()
                .usageEconomy(this.occupancyCalculationService.calculateEconomyUsage(occupancy.getNoOfEconomyRooms(),
                        occupancy.getCustomerWillingPrice()))
                .usagePremium(this.occupancyCalculationService.calculatePremiumUsage(occupancy.getNoOfPremiumRooms(),
                        occupancy.getCustomerWillingPrice()))
                .build();
    }

}
