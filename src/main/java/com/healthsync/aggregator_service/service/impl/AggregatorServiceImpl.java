package com.healthsync.aggregator_service.service.impl;

import com.healthsync.aggregator_service.service.AggregatorService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AggregatorServiceImpl implements AggregatorService {

//    // MongoDB connection constants
//    public static final String MONGO_DB_URI = "mongodb+srv://diwyangivithana1:VALAXwmzmbIrzf1f@health-sync.n81nt.mongodb.net";
//    public static final String MONGO_DB_NAME = "health_sync_db";
//    private static final String DOCTOR_COLLECTION_NAME = "doctors";
//    private static final String APPOINTMENT_COLLECTION_NAME = "appointments";
//    private static final String PATIENTS_COLLECTION_NAME = "patients";
//
//    // AWS Redshift connection constants
//    private static final String REDSHIFT_URL = "jdbc:redshift://health-sync.084828584810.us-east-1.redshift-serverless.amazonaws.com:5439/dev";
//    private static final String REDSHIFT_USER = "admin";
//    private static final String REDSHIFT_PASSWORD = "XCCEBpkcgk905-)";
//
//    // Establish Redshift connection
//    private Connection getRedshiftConnection() throws Exception {
//        return DriverManager.getConnection(REDSHIFT_URL, REDSHIFT_USER, REDSHIFT_PASSWORD);
//    }

    @Override
    public void saveDoctorWorkload() {
//        try (Connection redshiftConnection = getRedshiftConnection()) {
//            // Connect to MongoDB and retrieve collections
//            MongoDatabase database = MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME);
//            MongoCollection<Document> doctorCollection = database.getCollection(DOCTOR_COLLECTION_NAME);
//            MongoCollection<Document> appointmentCollection = database.getCollection(APPOINTMENT_COLLECTION_NAME);
//
//            List<Document> doctors = doctorCollection.find().into(new ArrayList<>());
//            List<Document> appointments = appointmentCollection.find().into(new ArrayList<>());
//
//            Map<String, Map<String, Object>> workloadMap = new HashMap<>();
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//
//            // Process doctor data
//            for (Document doctor : doctors) {
//                String doctorId = doctor.getString("doctorId");
//                String doctorName = doctor.getString("doctorName");
//                String speciality = doctor.getString("speciality");
//                LocalTime startTime = LocalTime.parse(doctor.getString("startTime"), timeFormatter);
//                LocalTime endTime = LocalTime.parse(doctor.getString("endTime"), timeFormatter);
//                double hoursWorked = (endTime.toSecondOfDay() - startTime.toSecondOfDay()) / 3600.0;
//
//                Map<String, Object> workload = new HashMap<>();
//                workload.put("doctorId", doctorId);
//                workload.put("doctorName", doctorName);
//                workload.put("speciality", speciality);
//                workload.put("appointmentCount", 0);
//                workload.put("hoursWorked", hoursWorked);
//                workload.put("createdOn", LocalDateTime.now());
//
//                workloadMap.put(doctorId, workload);
//            }
//
//            // Process appointment data
//            for (Document appointment : appointments) {
//                String doctorId = appointment.getString("doctorId");
//                if (workloadMap.containsKey(doctorId) && "Scheduled".equals(appointment.getString("status"))) {
//                    Map<String, Object> workload = workloadMap.get(doctorId);
//                    int appointmentCount = (int) workload.get("appointmentCount");
//                    workload.put("appointmentCount", appointmentCount + 1);
//                }
//            }
//
//            // Prepare data for Redshift
//            List<Object[]> workloadData = new ArrayList<>();
//            for (Map<String, Object> workload : workloadMap.values()) {
//                String doctorId = (String) workload.get("doctorId");
//                String doctorName = (String) workload.get("doctorName");
//                String speciality = (String) workload.get("speciality");
//                int appointmentCount = (int) workload.get("appointmentCount");
//                double hoursWorked = (double) workload.get("hoursWorked");
//                Timestamp createdOn = Timestamp.valueOf((LocalDateTime) workload.get("createdOn"));
//
//                workloadData.add(new Object[]{doctorId, doctorName, speciality, appointmentCount, hoursWorked, createdOn});
//            }
//
//            // Insert data into Redshift
//            String insertQuery = "INSERT INTO doctor_workload (DoctorID, DoctorName, Specialty, AppointmentCount, HoursCount, CreatedOn) VALUES (?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = redshiftConnection.prepareStatement(insertQuery)) {
//                for (Object[] row : workloadData) {
//                    for (int i = 0; i < row.length; i++) {
//                        preparedStatement.setObject(i + 1, row[i]);
//                    }
//                    preparedStatement.addBatch();
//                }
//                preparedStatement.executeBatch();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void saveAppointmentFrequency() {
//        try (Connection redshiftConnection = getRedshiftConnection()) {
//            MongoDatabase database = MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME);
//            MongoCollection<Document> appointmentCollection = database.getCollection(APPOINTMENT_COLLECTION_NAME);
//
//            List<Document> appointments = appointmentCollection.find().into(new ArrayList<>());
//
//            Map<String, Integer> frequencyMap = new HashMap<>();
//            for (Document appointment : appointments) {
//                String doctorId = appointment.getString("doctorId");
//                frequencyMap.put(doctorId, frequencyMap.getOrDefault(doctorId, 0) + 1);
//            }
//
//            // Prepare data for Redshift
//            List<Object[]> frequencyData = new ArrayList<>();
//            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
//                String doctorId = entry.getKey();
//                int appointmentCount = entry.getValue();
//                frequencyData.add(new Object[]{doctorId, appointmentCount});
//            }
//
//            // Insert data into Redshift
//            String insertQuery = "INSERT INTO appointment_frequency (DoctorID, AppointmentCount) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = redshiftConnection.prepareStatement(insertQuery)) {
//                for (Object[] row : frequencyData) {
//                    for (int i = 0; i < row.length; i++) {
//                        preparedStatement.setObject(i + 1, row[i]);
//                    }
//                    preparedStatement.addBatch();
//                }
//                preparedStatement.executeBatch();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void savePatientPrescriptions() {
//        try (Connection redshiftConnection = getRedshiftConnection()) {
//            MongoDatabase database = MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME);
//            MongoCollection<Document> patientsCollection = database.getCollection(PATIENTS_COLLECTION_NAME);
//
//            List<Document> patients = patientsCollection.find().into(new ArrayList<>());
//
//            String insertQuery = "INSERT INTO patient_prescriptions (PatientID, Prescription) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = redshiftConnection.prepareStatement(insertQuery)) {
//                for (Document patient : patients) {
//                    String patientId = patient.getString("patientId");
//                    String prescription = patient.getString("prescription");
//
//                    preparedStatement.setString(1, patientId);
//                    preparedStatement.setString(2, prescription);
//
//                    preparedStatement.addBatch();
//                }
//                preparedStatement.executeBatch();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void saveCommonLabTests() {
//        try (Connection redshiftConnection = getRedshiftConnection()) {
//            MongoDatabase database = MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME);
//            MongoCollection<Document> patientsCollection = database.getCollection(PATIENTS_COLLECTION_NAME);
//
//            List<Document> patients = patientsCollection.find().into(new ArrayList<>());
//
//            String insertQuery = "INSERT INTO common_lab_tests (PatientID, LabTest) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = redshiftConnection.prepareStatement(insertQuery)) {
//                for (Document patient : patients) {
//                    String patientId = patient.getString("patientId");
//                    String labTest = patient.getString("labTest");
//
//                    preparedStatement.setString(1, patientId);
//                    preparedStatement.setString(2, labTest);
//
//                    preparedStatement.addBatch();
//                }
//                preparedStatement.executeBatch();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void savePatientAppointments() {
//        try (Connection redshiftConnection = getRedshiftConnection()) {
//            MongoDatabase database = MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME);
//            MongoCollection<Document> appointmentsCollection = database.getCollection(APPOINTMENT_COLLECTION_NAME);
//
//            List<Document> appointments = appointmentsCollection.find().into(new ArrayList<>());
//
//            String insertQuery = "INSERT INTO patient_appointments (AppointmentID, PatientID, DoctorID, AppointmentDate, AppointmentTime) VALUES (?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = redshiftConnection.prepareStatement(insertQuery)) {
//                for (Document appointment : appointments) {
//                    String appointmentId = appointment.getString("appointmentId");
//                    String patientId = appointment.getString("patientId");
//                    String doctorId = appointment.getString("doctorId");
//                    String appointmentDate = appointment.getString("appointmentDate");
//                    String appointmentTime = appointment.getString("appointmentTime");
//
//                    preparedStatement.setString(1, appointmentId);
//                    preparedStatement.setString(2, patientId);
//                    preparedStatement.setString(3, doctorId);
//                    preparedStatement.setString(4, appointmentDate);
//                    preparedStatement.setString(5, appointmentTime);
//
//                    preparedStatement.addBatch();
//                }
//                preparedStatement.executeBatch();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
  }
}