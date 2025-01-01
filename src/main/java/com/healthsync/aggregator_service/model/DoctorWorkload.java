package com.healthsync.aggregator_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorWorkload {
    private String doctorId;
    private String doctorName;
    private String specialty;
    private int appointmentCount;
    private double hoursCount;
}
