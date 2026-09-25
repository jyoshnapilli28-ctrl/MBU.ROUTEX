package com.mbu.routex.driver.dto;

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
public class DriverStatusDto {
    private Long busId;
    private String busNumber;
    private String registration;
    private String currentStatus;
    private String routeCode;
    private String routeName;
    private String lastUpdated;
}
