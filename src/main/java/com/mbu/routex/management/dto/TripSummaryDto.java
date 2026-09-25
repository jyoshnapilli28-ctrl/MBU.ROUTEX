package com.mbu.routex.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripSummaryDto {
    private Long id;
    private String busNumber;
    private String routeName;
    private String routeCode;
    private String driverName;
    private String scheduledDeparture;
    private String scheduledArrival;
    private String status;
}
