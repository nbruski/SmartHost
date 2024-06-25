package com.java.smarthost.occupancy.domain.port.in;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.domain.model.Occupancy;

public interface OccupancyService {

    BookedRoomsWithIncomeResponse calculateIncomeAndUsage(Occupancy occupancy);

}
