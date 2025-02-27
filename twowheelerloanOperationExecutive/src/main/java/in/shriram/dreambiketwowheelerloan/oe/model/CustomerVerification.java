package in.shriram.dreambiketwowheelerloan.oe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CustomerVerification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int verificationID;
	private String verificationDate;
	private String status;
	private String remarks;

	
}
