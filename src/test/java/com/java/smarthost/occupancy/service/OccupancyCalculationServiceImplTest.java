package com.java.smarthost.occupancy.service;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.adapter.out.service.OccupancyCalculationServiceImpl;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class OccupancyCalculationServiceImplTest {

    @Mock
    private Occupancy occupancy;

    @InjectMocks
    private OccupancyCalculationServiceImpl service;

    @ParameterizedTest
    @MethodSource("com.java.smarthost.occupancy.common.TestDataProvider#provideInputForTests")
    public void returnCorrectNumberOfRooms(int premiumRooms, int economyRooms, List<Double> customerWillingPrices) {
        doReturn(premiumRooms).when(occupancy).getNoOfPremiumRooms();
        doReturn(economyRooms).when(occupancy).getNoOfEconomyRooms();
        doReturn(customerWillingPrices).when(occupancy).getCustomerWillingPrice();

        final BookedRoomsWithIncomeResponse response = service.calculateUsage(occupancy);

        assertAll(
                () -> assertEquals(3, response.getUsagePremium().getNoOfRooms()),
                () -> assertEquals(738.00, response.getUsagePremium().getIncome()),
                () -> assertEquals(3, response.getUsageEconomy().getNoOfRooms()),
                () -> assertEquals(167.99, response.getUsageEconomy().getIncome()),
                () -> verifyNoMoreInteractions(occupancy)
        );
    }


}
