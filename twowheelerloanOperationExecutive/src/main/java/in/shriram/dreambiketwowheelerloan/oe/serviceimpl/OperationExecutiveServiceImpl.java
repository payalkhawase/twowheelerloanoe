package in.shriram.dreambiketwowheelerloan.oe.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


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
	public Enquiry updateEnquiryStatus(int cibilId, String status) {
		// TODO Auto-generated method stub
		
		Enquiry eo = new Enquiry();
		eo = rt.getForObject("http://localhost:7777/enq/enquiryByCibil/"+cibilId, Enquiry.class);
		
		rt.put("http://localhost:7777/enq/updateEnquiryStatus/"+eo.getCustomerId()+"/"+status,eo);
				
		Enquiry eo1 = rt.getForObject("http://localhost:7777/enq/enquiry/"+eo.getCustomerId(), Enquiry.class);

		if(status.equals("Approved")) {
			EmailSender e= new EmailSender();
			e.setToEmail(eo.getEmail());
			e.setSubject("Sennd email");
			this.sendEmail(e, eo.getCustomerId());
			
		}
		
		
		eo = rt.getForObject("http://localhost:7777/enq/enquiryByCibil/"+cibilId, Enquiry.class);

		//return rt.getForEntity("http://localhost:7777/enq/enquiry/"+eo.getCustomerId(), Enquiry.class);

		
		return eo1;
	}

	@Override
	public EmailSender sendEmail(EmailSender e, int customerId) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		ResponseEntity<Enquiry> enq=rt.getForEntity("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
			e.setMessage("Customer with CustomerId is" + enq.getBody().getCustomerId()+" has a sucessfully done"
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

