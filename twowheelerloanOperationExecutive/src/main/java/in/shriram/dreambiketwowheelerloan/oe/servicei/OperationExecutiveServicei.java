package in.shriram.dreambiketwowheelerloan.oe.servicei;

import org.springframework.http.ResponseEntity;

import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

public interface OperationExecutiveServicei {

	public Enquiry updateEnquiryStatus(int cibilId, String status);

}
