package com.java.smarthost.occupancy.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CalculateOccupancyRequest {
    @Schema(description = "Number of available premium rooms")
    @NotEmpty
    private int noOfPremiumRooms;

    @Schema(description = "Number of available economy rooms")
    @NotEmpty
    private int noOfEconomyRooms;

    @Schema(description = "Prices which customers are willing to pay")
    @NotNull
    private List<Double> customerWillingPrice;
}
