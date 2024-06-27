package com.java.smarthost.occupancy.controller;

import com.java.smarthost.occupancy.adapter.in.controller.OccupancyController;
import com.java.smarthost.occupancy.adapter.in.converter.CalculateOccupancyRequestConverterImpl;
import com.java.smarthost.occupancy.adapter.in.dto.BookedRoomsWithIncomeResponse;
import com.java.smarthost.occupancy.adapter.in.dto.CalculateOccupancyResponse;
import com.java.smarthost.occupancy.domain.port.in.OccupancyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OccupancyController.class)
@Import(CalculateOccupancyRequestConverterImpl.class)
public class OccupancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookedRoomsWithIncomeResponse bookedRoomsWithIncomeResponse;

    @Mock
    private CalculateOccupancyResponse calculateOccupancyResponse;

    @MockBean
    private OccupancyService occupancyService;

    @Test
    public void testCalculateRoomOccupancy() throws Exception {
        doReturn(3).when(calculateOccupancyResponse).getNoOfRooms();
        doReturn(300.20).when(calculateOccupancyResponse).getIncome();
        doReturn(calculateOccupancyResponse).when(bookedRoomsWithIncomeResponse).getUsageEconomy();
        doReturn(calculateOccupancyResponse).when(bookedRoomsWithIncomeResponse).getUsagePremium();
        doReturn(bookedRoomsWithIncomeResponse).when(occupancyService).calculateIncomeAndUsage(any());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/occupancy/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "noOfPremiumRooms": 7,
                                    "noOfEconomyRooms": 1,
                                    "customerWillingPrice": [
                                        23,
                                        45,
                                        155,
                                        374,
                                        22,
                                        99.99,
                                        100,
                                        101,
                                        115,
                                        209
                                    ]
                                }
                                """
                        )
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"usagePremium\":{\"noOfRooms\":3,\"income\":300.2},\"usageEconomy\":{\"noOfRooms\":3,\"income\":300.2}}"));
    }
}
