package in.shriram.dreambiketwowheelerloan.oe.servicei;


import java.util.List;

import in.shriram.dreambiketwowheelerloan.oe.model.Customer;
import in.shriram.dreambiketwowheelerloan.oe.model.CustomerVerification;
import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;

import org.springframework.http.ResponseEntity;


import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

public interface OperationExecutiveServicei {


	public Enquiry updateEnquiryStatus(int cibilId);


	public EmailSender sendEmail(EmailSender e, int customerId);

	public Customer getcustomer(int customerId, String loanStatus);

	public CustomerVerification addVerifictiondetails(CustomerVerification cu, int customerId);
 

}
