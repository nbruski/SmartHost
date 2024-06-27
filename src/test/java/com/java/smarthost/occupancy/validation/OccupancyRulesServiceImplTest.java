package com.java.smarthost.occupancy.validation;


import com.java.smarthost.occupancy.domain.service.rules.OccupancyRulesServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OccupancyRulesServiceImplTest {

    @ParameterizedTest
    @MethodSource("com.java.smarthost.occupancy.common.TestDataProvider#provideInputForTestsOfValidator")
    public void testIfReturnTrueWhenNumbersAreBiggerThan0(List<Double> numbers) {
        final OccupancyRulesServiceImpl service = new OccupancyRulesServiceImpl();

        assertAll(
                () -> assertTrue(service.applyRulesForPrices(numbers))
        );
    }

    @ParameterizedTest
    @MethodSource("com.java.smarthost.occupancy.common.TestDataProvider#provideInputForTestsOfValidatorWithWrongInput")
    public void testIfReturnFalseWhenNumbersAreNotBiggerThan0(List<Double> numbers) {
        final OccupancyRulesServiceImpl service = new OccupancyRulesServiceImpl();

        assertAll(
                () -> assertFalse(service.applyRulesForPrices(numbers))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1,
            2,
            3
    })
    public void testIfReturnTrueWhenNumberIsBiggerThan0(int number) {
        final OccupancyRulesServiceImpl service = new OccupancyRulesServiceImpl();

        assertAll(
                () -> assertTrue(service.applyRulesForRooms(number))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1,
            -2,
            -3
    })
    public void testIfReturnFalseWhenNumberIsNotBiggerThan0(int number) {
        final OccupancyRulesServiceImpl service = new OccupancyRulesServiceImpl();

        assertAll(
                () -> assertFalse(service.applyRulesForRooms(number))
        );
    }
}
