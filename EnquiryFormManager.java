package com.phonelog.apllication.mongo.manager;

import java.util.List;

import com.phonelog.apllication.mongo.ActiveEnquiry;
import com.phonelog.apllication.mongo.EnquiryForm;
import com.phonelog.apllication.mongo.OldEnquiry;

public interface EnquiryFormManager {

	EnquiryForm findById(String id);
	EnquiryForm save(EnquiryForm  enquiryForm);
	void delete(String id);
	List<EnquiryForm> ListAll();
	public List<ActiveEnquiry> ActiveEnquiries();
    public List<OldEnquiry> OldEnquiries();

}
