package com.java.smarthost.occupancy.adapter.in.converter;

import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyRequest;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = ALWAYS)
public interface CalculateOccupancyRequestConverter {
    Occupancy toOccupancy(CalculateOccupancyRequest request);
}
