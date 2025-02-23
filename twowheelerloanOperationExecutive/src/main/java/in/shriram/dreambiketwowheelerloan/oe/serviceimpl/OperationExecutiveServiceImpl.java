package in.shriram.dreambiketwowheelerloan.oe.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	RestTemplate rt;

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
}

