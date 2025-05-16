package in.shriram.dreambiketwowheelerloan.oe.serviceimpl;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.oe.model.Cibil;
import in.shriram.dreambiketwowheelerloan.oe.model.Customer;
import in.shriram.dreambiketwowheelerloan.oe.model.CustomerVerification;
import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.oe.repository.EmailSenderRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveCibilRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveEnquiryRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutiveVerificationRepo;
import in.shriram.dreambiketwowheelerloan.oe.repository.OperationExecutivrCustomerRepo;
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
	
    @Autowired
    OperationExecutivrCustomerRepo oecr;
    
    @Autowired
    OperationExecutiveVerificationRepo ov;
    
    @Autowired
    ObjectMapper om;
    
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    public Cibil co;
    
 // Kafka Listener to listen for new Cibil data
//    @KafkaListener(topics = "cibil_topic", groupId = "cibil_group", containerFactory = "containerFactory")
//    public void listenCibilData(String json) throws JsonMappingException, JsonProcessingException {
//    	
//        co = om.readValue(json, Cibil.class);  // Parse Cibil object from JSON
//        System.out.println("Received Cibil: " + co);
//    }
        
    public Enquiry updateEnquiryStatus(int customerId) {
		
    	Cibil c = new Cibil();
    	
    	co = rt.postForObject("http://localhost:7777/cibil/add", c , Cibil.class);
    	
		Enquiry eo = rt.getForObject("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
		eo.setCibil(co);
		
		if(co.getStatus().equals("Approved")) {
			int length = 10;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String pass = RandomStringUtils.random(length, useLetters, useNumbers);
			eo.setPassword(pass);
		}
		eo.setEnquiryStatus(co.getStatus());
		
		rt.put("http://localhost:7777/enq/updateEnquiryStatus",eo);
				
		Enquiry eo1 = rt.getForObject("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
		
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
		SimpleMailMessage message = new SimpleMailMessage();
		
		ResponseEntity<Enquiry> enq=rt.getForEntity("http://localhost:7777/enq/enquiry/"+customerId, Enquiry.class);
			e.setMessage("Customer with CustomerId is " + enq.getBody().getCustomerId()+" has a sucessfully done "
			+enq.getBody().getCibil().getCibilRemark()+ "with enquiry and your with the status is "  +enq.getBody().getCibil().getStatus() +
			" And the cibil score is" +enq.getBody().getCibil().getCibilScore()+"Your username is "+enq.getBody().getEmail()+ "And your password is "+enq.getBody().getPassword());
			
			message.setFrom(e.getFromEmail());
			message.setTo(e.getToEmail());
			message.setSubject(e.getSubject());
			message.setText(e.getMessage());
			sender.send(message);
		return e;
	}

	@Override
	public Customer getcustomer(int customerId, String loanStatus) {
		
		Customer cust=rt.getForObject("http://localhost:7777/apploan/getaCustomer/"+customerId, Customer.class);
	
		cust.setLoanStatus(loanStatus);
		//cust.setCibil();
		return oecr.save(cust);
	}


	@Override
	public CustomerVerification addVerifictiondetails(CustomerVerification cu, int customerId) {
	
     Customer cust=rt.getForObject("http://localhost:7777/apploan/getaCustomer/"+customerId, Customer.class);
		
		CustomerVerification cvo=ov.save(cu);
		//cust.setLoanStatus(loanStatus);
		cust.setCustVerification(cvo);

		//cust.setLoanStatus(loanStatus);

		cust.setLoanStatus("Verified");

		oecr.save(cust);
		
		
		return cvo;
	}

	

}

