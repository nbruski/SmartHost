package com.java.smarthost.occupancy.adapter.in.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/occupancy")
@RequiredArgsConstructor
@Tag(name = "Occupancy", description = "Endpoints for room occupancy manager")
public class OccupancyController {
}
