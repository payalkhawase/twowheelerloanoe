package in.shriram.dreambiketwowheelerloan.oe.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;

import in.shriram.dreambiketwowheelerloan.oe.model.Cibil;
import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveCibilRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveEnquiryRepo;
import in.shriram.dreambiketwowheelerloan.oe.servicei.OperationExecutiveServicei;

@Service
public class OperationExecutiveServiceImpl implements OperationExecutiveServicei{

	@Autowired
	OperationExecutiveCibilRepo oer;
	
	@Autowired
	OperationExecutiveEnquiryRepo oerEnq;

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
		
		//return rt.getForEntity("http://localhost:7777/enq/enquiry/"+eo.getCustomerId(), Enquiry.class);
		
		return eo1;
	}

	@Override
	public Enquiry getEnquiryEmail(String to) {
		Enquiry ers= oerEnq.findByEmail(to);
		return ers;
	}

	@Override
	public EmailSender sendEmail(EmailSender e, int customerId) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		Enquiry enq=rt.getForObject("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
		e.setMessage("Customer with " + enq.getCustomerId()+" has a sucessfully done "
		+enq.getCb().getCibilRemark() + "with enquiry and your with the status "+enq.getCb().getStatus());

		message.setTo(e.getToEmail());
		message.setFrom(e.getFromEmail());
		message.setSubject(e.getSubject());
		message.setText(e.getMessage());
		sender.send(message);
	
		return e;
		
	}


}

