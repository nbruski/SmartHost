package com.java.smarthost.occupancy.domain.port.out;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.domain.model.Occupancy;

public interface OccupancyCalculationService {

    BookedRoomsWithIncomeResponse calculateUsage(Occupancy occupancy);

}
