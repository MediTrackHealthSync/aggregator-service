package com.healthsync.aggregator_service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Data
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;
    private String doctorName;
    private String speciality;
    private LocalDate availabilityDate;
    private LocalTime startTime;
    private LocalTime endTime;

}
