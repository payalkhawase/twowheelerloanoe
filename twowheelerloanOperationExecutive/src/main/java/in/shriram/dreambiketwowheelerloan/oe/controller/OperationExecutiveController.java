package in.shriram.dreambiketwowheelerloan.oe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.oe.servicei.OperationExecutiveServicei;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/oe")
@Slf4j
public class OperationExecutiveController {

	@Autowired
	OperationExecutiveServicei oes;
	
	@Autowired
	RestTemplate rt;
	
	@Value("${spring.mail.username}")
	String fromEmail;
	
	@GetMapping("/getPendingEnuiry")
	public ResponseEntity<List> getPendingEnquiry()
	{
		List list = rt.getForObject("http://localhost:7777/enq/enquiry/getPendingEnuiry", List.class);
		return new ResponseEntity<List>(list,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAllCustomer")
	public ResponseEntity<List> getAllCustomerDataSubmit(){
	
		List l=rt.getForObject("http://localhost:7777/enq/enquiry/getAllCustomer", List.class);
		return new ResponseEntity<List>(l,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateOeEnquiryStatus/{custmerId}")
	public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable ("custmerId") int custmerId)
	{
		Enquiry eo =  oes.updateEnquiryStatus(custmerId);
		return new ResponseEntity<Enquiry>(eo,HttpStatus.OK);
		
	}
	
	@PostMapping("/sendEmailOE/{customerId}")
	public String sendmail(@RequestBody EmailSender e, @PathVariable ("customerId") int customerId) {
		
		try {
			e.setFromEmail(fromEmail);
		
			EmailSender es=	oes.sendEmail(e, customerId);
			log.info(e.getMessage());
			log.warn("this is warning");
		}
		catch(Exception e2) {
		
		return "email not send";
		}
		
		return "Email is send successfully";
		
		
	}
	
}
