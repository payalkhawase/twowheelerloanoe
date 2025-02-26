package in.shriram.dreambiketwowheelerloan.oe.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import in.shriram.dreambiketwowheelerloan.oe.model.Cibil;
import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.oe.repository.EmailSenderRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveCibilRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveEnquiryRepo;
import in.shriram.dreambiketwowheelerloan.oe.servicei.OperationExecutiveServicei;

@Service
public class OperationExecutiveServiceImpl implements OperationExecutiveServicei{

	@Autowired
	OperationExecutiveCibilRepo oer;
	
	@Autowired
	EmailSenderRepo oerEnq;

	@Autowired
	OperationExecutiveEnquiryRepo oers;
	
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	JavaMailSender sender;
	

	@Override
	public Enquiry updateEnquiryStatus(int custmerId) {
		// TODO Auto-generated method stub
		
		Cibil c = new Cibil();
		
		Cibil co = rt.postForObject("http://localhost:7777/cibil/add", c , Cibil.class);
		
		Enquiry eo = rt.getForObject("http://localhost:7777/enq/enquiry/"+custmerId, Enquiry.class);
		
		eo.setCb(co);
		eo.setEnquiryStatus(co.getStatus());
		
		rt.put("http://localhost:7777/enq/updateEnquiryStatus",eo);
				
		Enquiry eo1 = rt.getForObject("http://localhost:7777/enq/enquiry/"+custmerId, Enquiry.class);
		
		if(eo1.getEnquiryStatus().equals("Approved")) {
			EmailSender e= new EmailSender();
			e.setToEmail(eo1.getEmail());
			e.setSubject("Sennd email");
			this.sendEmail(e, eo1.getCustomerId());
		}
		
		return eo1;
	}


	@Override
	public EmailSender sendEmail(EmailSender e, int customerId) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		
		ResponseEntity<Enquiry> enq=rt.getForEntity("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
			e.setMessage("Customer with CustomerId is " + enq.getBody().getCustomerId()+" has a sucessfully done "
			+enq.getBody().getCb().getCibilRemark()+ "with enquiry and your with the status is "  +enq.getBody().getCb().getStatus() +
			" And the cibil score is" +enq.getBody().getCb().getCibilScore());
			message.setTo(e.getToEmail());
			message.setFrom(e.getFromEmail());
			message.setSubject(e.getSubject());
			message.setText(e.getMessage());
			sender.send(message);
		return e;
	}


}

