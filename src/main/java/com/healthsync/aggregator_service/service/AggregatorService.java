package com.healthsync.aggregator_service.service;

import java.sql.Connection;

public interface AggregatorService {

    // Method for saving the doctor workload
    void saveDoctorWorkload(Connection redshiftConnection);

    // Method for saving appointment frequency
    void saveAppointmentFrequency(Connection redshiftConnection);

    // Method for saving patient prescriptions
    void savePatientPrescriptions(Connection redshiftConnection);

    // Method for saving common lab tests
    void saveCommonLabTests(Connection redshiftConnection);

    // Method for saving patient appointments
    void savePatientAppointments(Connection redshiftConnection);
}
