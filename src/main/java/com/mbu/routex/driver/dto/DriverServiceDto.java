package com.mbu.routex.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverServiceDto {
    private String driverName;
    private String licenseNumber;
    private String driverPhone;
    private String busNumber;
    private String registration;
    private Integer busCapacity;
    private Long studentCount;
    private String routeName;
    private String routeCode;
    private List<String> stops;
    private String tripStatus;
    private String departureTime;
}
