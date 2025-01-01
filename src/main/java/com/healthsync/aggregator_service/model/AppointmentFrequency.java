package com.healthsync.aggregator_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFrequency {
    private String date; // ISO 8601 formatted date (e.g., "2024-12-20")
    private int appointmentCount;
}
