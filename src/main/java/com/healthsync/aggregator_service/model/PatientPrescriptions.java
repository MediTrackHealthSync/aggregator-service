package com.healthsync.aggregator_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientPrescriptions {
    private String patientId;
    private int prescriptionCount;
}
