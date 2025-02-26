package in.shriram.dreambiketwowheelerloan.oe.servicei;

import java.util.List;

import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

public interface OperationExecutiveServicei {

	

	public EmailSender sendEmail(EmailSender e, int customerId);

	public Enquiry updateEnquiryStatus(int custmerId);

	

	

}
