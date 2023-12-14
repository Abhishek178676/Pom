package com.phonelog.apllication.ws;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phonelog.apllication.mongo.ActiveEnquiry;
import com.phonelog.apllication.mongo.EnquiryForm;
import com.phonelog.apllication.mongo.OldEnquiry;
import com.phonelog.apllication.mongo.manager.EnquiryFormManager;
import com.phonelog.apllication.mongo.repository.ActiveEntriesRepository;
import com.phonelog.apllication.mongo.repository.OldEnquiryRepository;

@RestController
@RequestMapping("/EnquiryForm")
public class EnquiryFormController {

@Autowired EnquiryFormManager enquiryFormManager;
@Autowired ActiveEntriesRepository activeEntriesRepository;
@Autowired OldEnquiryRepository oldEnquiryRepository;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEnquiryForm(@RequestBody EnquiryForm enquiry){
		
		EnquiryForm enquiryForm = null;
		
		try {
			if(enquiry.getId()!=null) {
				enquiryForm=enquiryFormManager.findById(enquiry.getId());	
			}
			if(enquiryForm==null) {
				BeanUtils.copyProperties(enquiry, enquiryForm);
				enquiryFormManager.save(enquiryForm);	
				 return ResponseEntity.ok("Enquiry Added successfully");	
			}
			else {
				
				BeanUtils.copyProperties(enquiry, enquiryForm,"id");
				enquiryFormManager.save(enquiryForm);	
				return ResponseEntity.ok("Enquiry updated successfully");	
				
			}
		}
		catch(Exception e){
			return ResponseEntity.ok("Saving Fields Failed");	
		}	
	}
	@PostMapping("/post")
	public ResponseEntity<String> postEnquiryForm(@RequestBody EnquiryForm enquiry){
		
		 LocalDateTime currentDate = LocalDateTime.now();
	        List<ActiveEnquiry> expiredEnquiries = activeEntriesRepository.findByEndDateBefore(currentDate);

	        for (ActiveEnquiry enquiries : expiredEnquiries) {
	            OldEnquiry oldEnquiry = new OldEnquiry();
	            oldEnquiry.setFirstName(enquiries.getFirstName());
	            oldEnquiry.setLastName(enquiries.getLastName());
	            oldEnquiry.setPhoneNumber(enquiries.getPhoneNumber());
	            oldEnquiry.setEmail(enquiries.getEmail());
	            oldEnquiry.setComments(enquiries.getComments());
	           

	            oldEnquiryRepository.save(oldEnquiry);
	            activeEntriesRepository.deleteById(enquiry.getId());
	        }
	    	return ResponseEntity.ok("Active Deleted Enquiry and moved To old Enquiry successfully");
	}
	
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEnquiryForm(@PathVariable String id) {
	        try {
	        	enquiryFormManager.delete(id);
	            return ResponseEntity.ok("Enquiry deleted successfully");
	            
	        } catch (NoSuchElementException e) {
	           // return ResponseEntity.status(HttpStatusCode.).body(e.getMessage());
	        	 	return ResponseEntity.ok("Enquiry deleted successfully");
	        }
	    }
	 
	 @GetMapping("/list")
	 public List<EnquiryForm> listEnquiryForm() {
		 return enquiryFormManager.ListAll();
	 }
	 
	  @GetMapping("/active")
	    public List<ActiveEnquiry> getActiveEnquiries() {
	        return enquiryFormManager.ActiveEnquiries();
	    }

	    @GetMapping("/old")
	    public List<OldEnquiry> getOldEnquiries() {
	        return enquiryFormManager.OldEnquiries();
	    }
}
