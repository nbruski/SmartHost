package com.java.smarthost.occupancy.domain.service.rules;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupancyRulesServiceImpl implements OccupancyRulesService {

    @Override
    public boolean applyRulesForPrices(List<Double> numbers) {
        return numbers.stream()
                .allMatch(number -> number > 0);
    }

    @Override
    public boolean applyRulesForRooms(int number) {
        return number >= 0;
    }
}
