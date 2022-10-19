package YugiohProject.repository;

import YugiohProject.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This repository represents a repository using MongoDB services.
 * In this repository all customers data are stored.
 * Reminder - Each customer holds data on his User - username/password/name.
 * Additionally, each customer holds data on his card collection.
 * Where the data of his cards is actually stored in the CardRepository.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
