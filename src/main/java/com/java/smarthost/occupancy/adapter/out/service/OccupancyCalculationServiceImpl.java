package com.java.smarthost.occupancy.adapter.out.service;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyResponse;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.port.out.OccupancyCalculationService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OccupancyCalculationServiceImpl implements OccupancyCalculationService {

    @Override
    public BookedRoomsWithIncomeResponse calculateUsage(final Occupancy occupancy) {
        return calculateBooking(
                occupancy.getNoOfPremiumRooms(),
                occupancy.getNoOfEconomyRooms(),
                occupancy.getCustomerWillingPrice());
    }

    private BookedRoomsWithIncomeResponse calculateBooking(int premiumRooms, int economyRooms, List<Double> customerPayments) {
        final List<Double> premiumCustomers = customerPayments.stream()
                .filter(payment -> payment >= 100)
                .sorted(Collections.reverseOrder())
                .toList();

        final List<Double> economyCustomers = customerPayments.stream()
                .filter(payment -> payment < 100)
                .sorted(Collections.reverseOrder())
                .toList();

        double totalPremiumRevenue = 0;
        int premiumRoomsOccupied = 0;

        for (int i = 0; i < Math.min(premiumCustomers.size(), premiumRooms); i++) {
            totalPremiumRevenue += premiumCustomers.get(i);
            premiumRoomsOccupied++;
        }

        for (int i = 0; i < economyCustomers.size() && premiumRoomsOccupied < premiumRooms; i++) {
            totalPremiumRevenue += economyCustomers.get(i);
            premiumRoomsOccupied++;
        }

        double totalEconomyRevenue = 0;
        int economyRoomsOccupied = 0;

        final int remainingEconomyStartIndex = Math.max(0, premiumRooms - premiumCustomers.size());

        for (int i = remainingEconomyStartIndex; i < economyCustomers.size() && economyRoomsOccupied < economyRooms; i++) {
            totalEconomyRevenue += economyCustomers.get(i);
            economyRoomsOccupied++;
        }

        return BookedRoomsWithIncomeResponse.builder()
                .usagePremium(CalculateOccupancyResponse.builder()
                        .noOfRooms(premiumRoomsOccupied)
                        .income(totalPremiumRevenue)
                        .build())
                .usageEconomy(CalculateOccupancyResponse.builder()
                        .noOfRooms(economyRoomsOccupied)
                        .income(totalEconomyRevenue)
                        .build())
                .build();
    }
}
