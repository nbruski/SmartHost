package com.java.smarthost.occupancy.domain.service;

import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.port.in.OccupancyService;
import org.springframework.stereotype.Service;

@Service
public class OccupancyServiceImpl implements OccupancyService {

    @Override
    public BookedRoomsWithIncomeResponse calculateIncomeAndUsage(Occupancy occupancy) {
        return null;
    }

}
