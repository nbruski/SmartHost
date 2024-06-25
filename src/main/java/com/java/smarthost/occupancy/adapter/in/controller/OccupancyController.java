package com.java.smarthost.occupancy.adapter.in.controller;

import com.java.smarthost.common.handler.ResponseExceptionHandler;
import com.java.smarthost.occupancy.adapter.in.converter.CalculateOccupancyRequestConverter;
import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyRequest;
import com.java.smarthost.occupancy.domain.port.in.OccupancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/occupancy")
@RequiredArgsConstructor
@Tag(name = "Occupancy", description = "Endpoints for room occupancy manager")
public class OccupancyController {
    private final OccupancyService occupancyService;
    private final CalculateOccupancyRequestConverter occupancyRequestConverter;

    @PostMapping("/calculate")
    @Operation(
            summary = "Calculation of room occupancy",
            description = "Calculation of room occupancy, calculation of income",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookedRoomsWithIncomeResponse.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseExceptionHandler.ExceptionResponse.class))
                            }
                    )
            }
    )
    public ResponseEntity<BookedRoomsWithIncomeResponse> calculateRoomOccupancy(@RequestBody @Valid final CalculateOccupancyRequest request) {
        return ResponseEntity.ok(this.occupancyService
                .calculateIncomeAndUsage(
                        this.occupancyRequestConverter.toOccupancy(request)));
    }
}
