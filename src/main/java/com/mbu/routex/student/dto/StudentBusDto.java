package com.mbu.routex.student.dto;

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
public class StudentBusDto {
    private String busNumber;
    private String registration;
    private Integer capacity;
    private String routeName;
    private String routeCode;
    private String driverName;
    private String driverPhone;
    private String busStatus;
    private String pickupStop;
    private List<String> stops;
}
