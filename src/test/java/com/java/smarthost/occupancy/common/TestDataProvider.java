package com.java.smarthost.occupancy.common;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

@UtilityClass
public class TestDataProvider {

    public static Stream<Arguments> provideInputForTests() {
        return Stream.of(
                Arguments.of(3, 3,
                        Arrays.asList(23.00, 45.00, 155.00, 374.00, 22.00, 99.99, 100.00, 101.00, 115.00, 209.00))
        );
    }

    public static Stream<Arguments> provideInputForTestsOfValidator() {
        return Stream.of(
                Arguments.of(Arrays.asList(23.00, 45.00, 155.00, 374.00, 22.00, 99.99, 100.00, 101.00, 115.00, 209.00))
        );
    }

    public static Stream<Arguments> provideInputForTestsOfValidatorWithWrongInput() {
        return Stream.of(
                Arguments.of(Arrays.asList(-23.00, 45.00, 155.00, 374.00, -22.00, 99.99, 100.00, 101.00, 115.00, 209.00))
        );
    }

}
