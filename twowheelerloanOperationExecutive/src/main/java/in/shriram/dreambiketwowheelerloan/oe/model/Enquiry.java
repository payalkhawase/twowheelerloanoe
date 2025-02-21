package in.shriram.dreambiketwowheelerloan.oe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
/*import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
*/
import lombok.Data;

@Entity
@Data
public class Enquiry {

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String firstname;
	private String lastName;
	private String address;
	private String city;
	private int age;
	private String email;
	private String mobileNo;
	private long alternateMobno;
	private String pancardNo;
	private String adharcardNo;
	private String enquiryStatus="Pending";
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cb;
	
}
