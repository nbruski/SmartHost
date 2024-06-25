package com.java.smarthost.occupancy.domain.service.rules;

import java.util.List;

public interface OccupancyRulesService {

    boolean applyRulesForPrices(List<Double> numbers);

    boolean applyRulesForRooms(int number);

}
