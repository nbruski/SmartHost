package com.java.smarthost.occupancy.validation;

import com.java.smarthost.common.exception.ValidationException;
import com.java.smarthost.common.validation.error.ValidationError;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.service.rules.OccupancyRulesService;
import com.java.smarthost.occupancy.domain.service.validation.OccupancyValidationService;
import com.java.smarthost.occupancy.domain.service.validation.OccupancyValidationServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class OccupancyValidationServiceImplTest {

    @Mock
    private OccupancyRulesService occupancyRulesService;

    @Mock
    private Occupancy occupancy;

    @InjectMocks
    private OccupancyValidationServiceImpl occupancyValidationService;

    @ParameterizedTest
    @MethodSource("com.java.smarthost.occupancy.common.TestDataProvider#provideInputForTests")
    public void testIfValidationNotThrowAnyException(int premiumRooms, int economyRooms, List<Double> customerWillingPrices) {
        doReturn(premiumRooms).when(occupancy).getNoOfPremiumRooms();
        doReturn(economyRooms).when(occupancy).getNoOfEconomyRooms();
        doReturn(customerWillingPrices).when(occupancy).getCustomerWillingPrice();
        doReturn(true).when(occupancyRulesService).applyRulesForRooms(anyInt());
        doReturn(true).when(occupancyRulesService).applyRulesForPrices(any());

        assertAll(
                () -> assertDoesNotThrow(() -> occupancyValidationService.validateOccupancy(occupancy)),
                () -> verifyNoMoreInteractions(occupancy)
        );
    }

    @ParameterizedTest
    @MethodSource("com.java.smarthost.occupancy.common.TestDataProvider#provideInputForTests")
    public void testIfValidationThrowException(int premiumRooms, int economyRooms, List<Double> customerWillingPrices) {
        doReturn(premiumRooms).when(occupancy).getNoOfPremiumRooms();
        doReturn(economyRooms).when(occupancy).getNoOfEconomyRooms();
        doReturn(customerWillingPrices).when(occupancy).getCustomerWillingPrice();
        doReturn(false).when(occupancyRulesService).applyRulesForRooms(anyInt());
        doReturn(false).when(occupancyRulesService).applyRulesForPrices(any());

        assertAll(
                () -> assertThrows(ValidationException.class, () -> occupancyValidationService.validateOccupancy(occupancy)),
                () -> verifyNoMoreInteractions(occupancy)
        );
    }

}
