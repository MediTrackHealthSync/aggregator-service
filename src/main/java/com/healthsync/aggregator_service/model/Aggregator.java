package com.healthsync.aggregator_service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Data
public class Aggregator {
    private String doctorName;
    private long totalAppointments;
    private Map<String, Long> appointmentFrequencyByDate;
    private Map<String, Long> appointmentsByGender;
    private Map<String, Long> appointmentsBySpecialty;
    private Map<String, Long> appointmentStatusCounts;
    private double averageWaitingTime;
}
