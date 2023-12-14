package com.phonelog.apllication.mongo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phonelog.apllication.mongo.ActiveEnquiry;
@Repository
public interface ActiveEntriesRepository extends MongoRepository<ActiveEnquiry, String>{

	List<ActiveEnquiry> findByEndDateBefore(LocalDateTime currentDate);

}
