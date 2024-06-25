package com.java.smarthost.occupancy.adapter.in.dto;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
public class CalculateOccupancyResponse {

    private int noOfRooms;

    private String income;

}
