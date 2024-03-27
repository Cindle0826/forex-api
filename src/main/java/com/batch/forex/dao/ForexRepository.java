package com.batch.forex.dao;

import com.batch.forex.entity.ForexType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ForexRepository extends MongoRepository<ForexType, String> {
    @Query(value = "{'Date': {$gte: ?0, $lte: ?1}}")
    List<ForexType> findByDateRange (String startDate, String endDate);
}
