package in.shriram.dreambiketwowheelerloan.oe.servicei;


import java.util.List;

import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;

import org.springframework.http.ResponseEntity;


import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

public interface OperationExecutiveServicei {

	public Enquiry updateEnquiryStatus(int cibilId);

	public EmailSender sendEmail(EmailSender e, int customerId);
 
}
