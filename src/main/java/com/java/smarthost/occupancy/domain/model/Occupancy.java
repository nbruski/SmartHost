package com.java.smarthost.occupancy.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Occupancy {

    private int noOfPremiumRooms;

    private int noOfEconomyRooms;

    private List<Double> customerWillingPrice;

}
