package edu.fafic.msstock.infrastructure.repository;

import edu.fafic.msstock.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    boolean existsByNameAndSupplier(String name, String supplier);
}
