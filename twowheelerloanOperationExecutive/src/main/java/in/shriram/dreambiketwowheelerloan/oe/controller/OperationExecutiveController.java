package in.shriram.dreambiketwowheelerloan.oe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.oe.servicei.OperationExecutiveServicei;

@RestController
@RequestMapping("/oe")
public class OperationExecutiveController {

	@Autowired
	OperationExecutiveServicei oes;

	@PutMapping("updateEnquiryStatus/{cibilId}/{status}")
	public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable ("cibilId") int cibilId,@PathVariable ("status") String status)
	{
		Enquiry eo = oes.updateEnquiryStatus(cibilId,status);
		return new ResponseEntity<Enquiry>(eo,HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomerDataSubmit")
	public ResponseEntity<List> getAllCustomerDataSubmit(){
		List l=oes.getAllCustomerDataSubmit();
		return new ResponseEntity<List>(l,HttpStatus.OK);
	}
}
