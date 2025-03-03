package in.shriram.dreambiketwowheelerloan.oe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.oe.model.CustomerVerification;

@Repository
public interface OperationExecutiveVerificationRepo extends JpaRepository<CustomerVerification, Integer>{

}
