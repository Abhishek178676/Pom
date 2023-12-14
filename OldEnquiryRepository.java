package com.phonelog.apllication.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phonelog.apllication.mongo.OldEnquiry;

@Repository
public interface OldEnquiryRepository extends MongoRepository<OldEnquiry, String>{

}
