package in.shriram.dreambiketwowheelerloan.oe.servicei;

import java.util.List;

import in.shriram.dreambiketwowheelerloan.oe.model.EmailSender;
import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

public interface OperationExecutiveServicei {

	public Enquiry updateEnquiryStatus(int cibilId, String status);

	public EmailSender sendEmail(EmailSender e, int customerId);

	

	

}
