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
public class MaintenanceRecordDto {
    private Long id;
    private Long busId;
    private String busNumber;
    private String component;
    private String description;
    private String serviceDate;
    private String nextServiceDate;
    private String status;
}
