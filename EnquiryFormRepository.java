package com.phonelog.apllication.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phonelog.apllication.mongo.EnquiryForm;
@Repository
public interface EnquiryFormRepository extends MongoRepository<EnquiryForm, String>{

	
}
