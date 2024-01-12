package com.forex.repo;

import com.forex.document.ForexRate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface ForexRateRepository extends MongoRepository<ForexRate, String> {
    @Query("{'code_from': ?0, 'code_to': ?1, 'record_date_string': { $gte: ?2, $lte: ?3 }}")
    List<ForexRate> findByCodeFromAndCodeToBetweenDateString(String codeFrom, String codeTo, String startDate, String endDate);
}
