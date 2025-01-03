package com.healthsync.aggregator_service.service;

import java.sql.Connection;

public interface AggregatorService {

    // Method for saving the doctor workload
    void saveDoctorWorkload();

    // Method for saving appointment frequency
    void saveAppointmentFrequency();

    // Method for saving patient prescriptions
    void savePatientPrescriptions();

    // Method for saving common lab tests
    void saveCommonLabTests();

    // Method for saving patient appointments
    void savePatientAppointments();
}
