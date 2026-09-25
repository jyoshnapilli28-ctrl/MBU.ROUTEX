package com.mbu.routex.student.dto;

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
public class StudentTrackDto {
    private String busNumber;
    private String routeName;
    private String routeCode;
    private String tripStatus;
    private String currentStop;
    private String nextStop;
    private Integer etaMinutes;
    private String lastUpdated;
    private String departureTime;
    private String driverName;
}
