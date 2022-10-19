package YugiohProject.repository;

import YugiohProject.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This repository represents a repository using MongoDB services.
 * In this repository all cards data are stored.
 */
public interface CardRepository extends MongoRepository<Card, String> {

}
