package in.shriram.dreambiketwowheelerloan.oe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.oe.model.Enquiry;

@Repository
public interface OperationExecutiveEnquiryRepo extends JpaRepository<Enquiry, Integer> {

	
	
	
}
