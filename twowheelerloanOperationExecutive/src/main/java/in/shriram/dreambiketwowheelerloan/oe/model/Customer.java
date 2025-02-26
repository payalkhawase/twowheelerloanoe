package in.shriram.dreambiketwowheelerloan.oe.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private String customerDateOfBirth;
	private int customerAge;
	private String customerGender;
	private String customerEmail;
	private int password;
	private double customerMobileNumber;
	private double customerAdditionalMobileNumber;
	private double customerAmountPaidForHome;
	private double customerTotalLoanRequired;
	private String loanStatus="Submit";
	
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocuments personalDoc;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DependentInformation depInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress custAddr;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private cibilscore cibil;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails acdetails;

	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails accountdetails;

}
