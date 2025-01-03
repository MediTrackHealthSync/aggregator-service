package com.healthsync.aggregator_service.controller;

import com.healthsync.aggregator_service.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing manual and scheduled triggers of the aggregation process.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/aggregator")
public class AggregatorController {

    private final AggregatorService aggregatorService;

    /**
     * API to manually trigger the aggregation process for all tables.
     *
     * @return Response indicating the aggregation process status.
     */
    @GetMapping("/run")
    public String triggerAggregation() {
        log.info("Manual aggregation process started.");
        runAggregationProcess();
        log.info("Manual aggregation process completed.");
        return "Aggregation process triggered successfully.";
    }

    /**
     * Scheduled task to run the aggregation process daily at 12 PM.
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduledAggregation() {
        log.info("Scheduled aggregation process started.");
        runAggregationProcess();
        log.info("Scheduled aggregation process completed.");
    }

    /**
     * Executes the aggregation and saving process for all tables.
     */
    private void runAggregationProcess() {
        try {
            log.info("Processing DoctorWorkload data...");
            aggregatorService.saveDoctorWorkload();
            log.info("DoctorWorkload data processed successfully.");

            log.info("Processing AppointmentFrequency data...");
            aggregatorService.saveAppointmentFrequency();
            log.info("AppointmentFrequency data processed successfully.");

            log.info("Processing PatientPrescriptions data...");
            aggregatorService.savePatientPrescriptions();
            log.info("PatientPrescriptions data processed successfully.");

            log.info("Processing PatientAppointments data...");
            aggregatorService.savePatientAppointments();
            log.info("PatientAppointments data processed successfully.");

            log.info("Processing CommonLabTests data...");
            aggregatorService.saveCommonLabTests();
            log.info("CommonLabTests data processed successfully.");

        } catch (Exception e) {
            log.error("Error occurred during the aggregation process: {}", e.getMessage(), e);
        }
    }
}
