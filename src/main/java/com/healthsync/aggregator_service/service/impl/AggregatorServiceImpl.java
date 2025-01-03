package com.healthsync.aggregator_service.service.impl;

import com.healthsync.aggregator_service.model.*;
import com.healthsync.aggregator_service.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregatorServiceImpl implements AggregatorService {

    private final MongoTemplate mongoTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveDoctorWorkload() {
        List<DoctorWorkload> doctorWorkloads = this.getDoctorWorkload();
        log.info("Saving DoctorWorkload data to Redshift.");
        String query = "INSERT INTO doctor_workload (DoctorID, DoctorName, Specialty, AppointmentCount, HoursCount, CreatedOn) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (DoctorID, CreatedOn) DO UPDATE SET " +
                "DoctorName = EXCLUDED.DoctorName, " +
                "Specialty = EXCLUDED.Specialty, " +
                "AppointmentCount = EXCLUDED.AppointmentCount, " +
                "HoursCount = EXCLUDED.HoursCount";

        doctorWorkloads.forEach(workload -> jdbcTemplate.update(query,
                workload.getDoctorId(),
                workload.getDoctorName(),
                workload.getSpecialty(),
                workload.getAppointmentCount(),
                workload.getHoursCount(),
                java.sql.Date.valueOf(java.time.LocalDate.now())
        ));
    }

    @Override
    public void saveAppointmentFrequency() {
        List<AppointmentFrequency> appointmentFrequencies = this.getAppointmentFrequency();
        log.info("Saving AppointmentFrequency data to Redshift.");
        String query = "INSERT INTO appointment_frequency (Date, AppointmentCount, CreatedOn) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (Date, CreatedOn) DO UPDATE SET " +
                "AppointmentCount = EXCLUDED.AppointmentCount";

        appointmentFrequencies.forEach(frequency -> jdbcTemplate.update(query,
                frequency.getDate(),
                frequency.getAppointmentCount(),
                java.sql.Date.valueOf(java.time.LocalDate.now())
        ));
    }

    @Override
    public void savePatientPrescriptions() {
        List<PatientPrescriptions> patientPrescriptions = this.getPatientPrescriptions();
        log.info("Saving PatientPrescriptions data to Redshift.");
        String query = "INSERT INTO patient_prescriptions (PatientID, PrescriptionCount, CreatedOn) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (PatientID, CreatedOn) DO UPDATE SET " +
                "PrescriptionCount = EXCLUDED.PrescriptionCount";

        patientPrescriptions.forEach(prescription -> jdbcTemplate.update(query,
                prescription.getPatientId(),
                prescription.getPrescriptionCount(),
                java.sql.Date.valueOf(java.time.LocalDate.now())
        ));
    }

    @Override
    public void savePatientAppointments() {
        List<PatientAppointments> patientAppointments = this.getPatientAppointments();
        log.info("Saving PatientAppointments data to Redshift.");
        String query = "INSERT INTO patient_appointments (PatientID, AppointmentCount, CreatedOn) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (PatientID, CreatedOn) DO UPDATE SET " +
                "AppointmentCount = EXCLUDED.AppointmentCount";

        patientAppointments.forEach(appointment -> jdbcTemplate.update(query,
                appointment.getPatientId(),
                appointment.getAppointmentCount(),
                java.sql.Date.valueOf(java.time.LocalDate.now())
        ));
    }

    @Override
    public void saveCommonLabTests() {
        List<CommonLabTests> commonLabTests = this.getCommonLabTests();
        log.info("Saving CommonLabTests data to Redshift.");
        String query = "INSERT INTO common_lab_tests (TestName, TotalTest, CreatedOn) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (TestName, CreatedOn) DO UPDATE SET " +
                "TotalTest = EXCLUDED.TotalTest";

        commonLabTests.forEach(test -> jdbcTemplate.update(query,
                test.getTestName(),
                test.getTotalTest(),
                java.sql.Date.valueOf(java.time.LocalDate.now())
        ));
    }

    /**
     * Aggregation logic for DoctorWorkload from MongoDB.
     */
    private List<DoctorWorkload> getDoctorWorkload() {
        log.info("Starting aggregation for DoctorWorkload from MongoDB.");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("doctorId", "doctorName", "specialty")
                        .count().as("appointmentCount")
                        .sum("hoursWorked").as("hoursCount"),
                Aggregation.project("appointmentCount", "hoursCount")
                        .and("doctorId").previousOperation()
                        .and("doctorName").previousOperation()
                        .and("specialty").previousOperation()
        );

        AggregationResults<DoctorWorkload> results =
                mongoTemplate.aggregate(aggregation, Appointment.class, DoctorWorkload.class);

        log.info("DoctorWorkload aggregation completed with {} records.", results.getMappedResults().size());
        return results.getMappedResults();
    }

    /**
     * Aggregation logic for AppointmentFrequency from MongoDB.
     */
    private List<AppointmentFrequency> getAppointmentFrequency() {
        log.info("Starting aggregation for AppointmentFrequency from MongoDB.");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("appointmentDate")
                        .andExpression("{$dateToString: {format: '%Y-%m-%d', date: '$appointmentDate'}}").as("date"),
                Aggregation.group("date").count().as("appointmentCount"),
                Aggregation.project("appointmentCount").and("date").previousOperation()
        );

        AggregationResults<AppointmentFrequency> results =
                mongoTemplate.aggregate(aggregation, Appointment.class, AppointmentFrequency.class);

        log.info("AppointmentFrequency aggregation completed with {} records.", results.getMappedResults().size());
        return results.getMappedResults();
    }

    /**
     * Aggregation logic for PatientPrescriptions from MongoDB.
     */
    private List<PatientPrescriptions> getPatientPrescriptions() {
        log.info("Starting aggregation for PatientPrescriptions from MongoDB.");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("patientId").count().as("prescriptionCount"),
                Aggregation.project("prescriptionCount").and("patientId").previousOperation()
        );

        AggregationResults<PatientPrescriptions> results =
                mongoTemplate.aggregate(aggregation, "prescriptions", PatientPrescriptions.class);

        log.info("PatientPrescriptions aggregation completed with {} records.", results.getMappedResults().size());
        return results.getMappedResults();
    }

    /**
     * Aggregation logic for PatientAppointments from MongoDB.
     */
    private List<PatientAppointments> getPatientAppointments() {
        log.info("Starting aggregation for PatientAppointments from MongoDB.");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("patientId").count().as("appointmentCount"),
                Aggregation.project("appointmentCount").and("patientId").previousOperation()
        );

        AggregationResults<PatientAppointments> results =
                mongoTemplate.aggregate(aggregation, Appointment.class, PatientAppointments.class);

        log.info("PatientAppointments aggregation completed with {} records.", results.getMappedResults().size());
        return results.getMappedResults();
    }

    /**
     * Aggregation logic for CommonLabTests from MongoDB.
     */
    private List<CommonLabTests> getCommonLabTests() {
        log.info("Starting aggregation for CommonLabTests from MongoDB.");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("testName").count().as("totalTest"),
                Aggregation.project("totalTest").and("testName").previousOperation()
        );

        AggregationResults<CommonLabTests> results =
                mongoTemplate.aggregate(aggregation, "labTests", CommonLabTests.class);

        log.info("CommonLabTests aggregation completed with {} records.", results.getMappedResults().size());
        return results.getMappedResults();
    }
}
