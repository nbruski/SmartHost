package com.java.smarthost.occupancy.adapter.in.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class BookedRoomsWithIncomeResponse {

    private CalculateOccupancyResponse usagePremium;

    private CalculateOccupancyResponse usageEconomy;

}
