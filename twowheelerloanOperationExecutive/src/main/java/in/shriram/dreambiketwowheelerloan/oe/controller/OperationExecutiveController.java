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
	
	@Value("${spring.mail.username}")
	String fromEmail;
	

	@PutMapping("updateOeEnquiryStatus/{cibilId}/{status}")
	public String updateEnquiryStatus(@PathVariable ("cibilId") int cibilId,@PathVariable ("status") String status)
	{
<<<<<<< HEAD
		Enquiry eo = oes.updateEnquiryStatus(cibilId,status);
		
		return new ResponseEntity<Enquiry>(eo,HttpStatus.OK);
=======
		Enquiry eo =  oes.updateEnquiryStatus(cibilId,status);
		//return new ResponseEntity<Enquiry>(eo,HttpStatus.OK);
		
		String str = "";
		if(eo.getEnquiryStatus().matches(status))
		str = "Enquiry update successfully";
		else
		str = "Enquiry not update successfully";	
		return str;
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerloanoe.git
	}
	
<<<<<<< HEAD
=======
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

	
	
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerloanoe.git
}
