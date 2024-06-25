package com.java.smarthost.occupancy.adapter.out.service;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyResponse;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.port.out.OccupancyCalculationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Double> premiumCustomers = new ArrayList<>();
        List<Double> economyCustomers = new ArrayList<>();

        for (double payment : customerPayments) {
            if (payment >= 100) {
                premiumCustomers.add(payment);
            } else {
                economyCustomers.add(payment);
            }
        }

        premiumCustomers.sort(Collections.reverseOrder());
        economyCustomers.sort(Collections.reverseOrder());

        int premiumRoomsOccupied = 0;
        int economyRoomsOccupied = 0;
        double totalPremiumRevenue = 0;
        double totalEconomyRevenue = 0;

        for (double payment : premiumCustomers) {
            if (premiumRoomsOccupied < premiumRooms) {
                premiumRoomsOccupied++;
                totalPremiumRevenue += payment;
            } else {
                break;
            }
        }

        for (double payment : economyCustomers) {
            if (premiumRoomsOccupied < premiumRooms) {
                premiumRoomsOccupied++;
                totalPremiumRevenue += payment;
            } else {
                break;
            }
        }

        for (double payment : economyCustomers) {
            if (economyRoomsOccupied < economyRooms) {
                economyRoomsOccupied++;
                totalEconomyRevenue += payment;
            } else {
                break;
            }
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
