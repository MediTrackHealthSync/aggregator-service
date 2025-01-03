package com.healthsync.aggregator_service.service;

/**
 * Service interface for managing the aggregation and saving process
 * from MongoDB to Redshift for various tables.
 */
public interface AggregatorService {

    /**
     * Aggregates and saves data for the `doctor_workload` table.
     */
    void saveDoctorWorkload();

    /**
     * Aggregates and saves data for the `appointment_frequency` table.
     */
    void saveAppointmentFrequency();

    /**
     * Aggregates and saves data for the `patient_prescriptions` table.
     */
    void savePatientPrescriptions();

    /**
     * Aggregates and saves data for the `patient_appointments` table.
     */
    void savePatientAppointments();

    /**
     * Aggregates and saves data for the `common_lab_tests` table.
     */
    void saveCommonLabTests();
}
