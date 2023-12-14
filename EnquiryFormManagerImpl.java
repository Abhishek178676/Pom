package com.phonelog.apllication.mongo.manager.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonelog.apllication.mongo.ActiveEnquiry;
import com.phonelog.apllication.mongo.EnquiryForm;
import com.phonelog.apllication.mongo.OldEnquiry;
import com.phonelog.apllication.mongo.manager.EnquiryFormManager;
import com.phonelog.apllication.mongo.repository.ActiveEntriesRepository;
import com.phonelog.apllication.mongo.repository.EnquiryFormRepository;
import com.phonelog.apllication.mongo.repository.OldEnquiryRepository;

@Service
public class EnquiryFormManagerImpl implements EnquiryFormManager{

	@Autowired EnquiryFormRepository enquiryFormRepository;
	@Autowired ActiveEntriesRepository ActiveEnquiriesRepository;
	@Autowired OldEnquiryRepository oldEnquiryRepository;

	@Override
	public EnquiryForm findById(String id) {
		Optional<EnquiryForm> u = enquiryFormRepository.findById(id);
		EnquiryForm phone = null;
		if (u.isPresent()) {
			phone = u.get();
		}
		return phone;
	}

	@Override
	public EnquiryForm save(EnquiryForm enquiryForm) {
		return enquiryFormRepository.save(enquiryForm);
		
        
	}

	@Override
	public void delete(String id) {
		  if (enquiryFormRepository.existsById(id)) {
			  enquiryFormRepository.deleteById(id);
	        } else {
	            throw new NoSuchElementException("Product not found with ID: " + id);
	        }
		
	}

	@Override
	public List<EnquiryForm> ListAll() {
		return enquiryFormRepository.findAll();
	}

	@Override
	public List<ActiveEnquiry> ActiveEnquiries() {
		 return ActiveEnquiriesRepository.findAll();
	}

	@Override
	public List<OldEnquiry> OldEnquiries() {
		return oldEnquiryRepository.findAll();
	}
	
}
