package com.healthsync.aggregator_service.repository;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import static com.healthsync.aggregator_service.service.impl.AggregatorServiceImpl.MONGO_DB_NAME;
import static com.healthsync.aggregator_service.service.impl.AggregatorServiceImpl.MONGO_DB_URI;

/**
 * Repository class to interact with MongoDB collections.
 */
@Repository
public class AggregatorRepository {


    // Method to get MongoDB collection for doctors
    public MongoCollection<org.bson.Document> getDoctorCollection() {
        return MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME).getCollection("doctors");
    }

    // Method to get MongoDB collection for appointments
    public MongoCollection<Document> getAppointmentCollection() {
        return MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME).getCollection("appointments");
    }

    // Method to get MongoDB collection for patients
    public MongoCollection<Document> getPatientCollection() {
        return MongoClients.create(MONGO_DB_URI).getDatabase(MONGO_DB_NAME).getCollection("patients");
    }


}
