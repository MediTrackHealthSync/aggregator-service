package com.healthsync.aggregator_service.repository;

import com.healthsync.aggregator_service.model.Aggregator;

public interface AggregatorRepository {
    void saveMetrics(Aggregator aggregator);

    Aggregator findMetricsByDoctorName(String doctorName);

    void deleteMetricsByDoctorName(String doctorName);
}
