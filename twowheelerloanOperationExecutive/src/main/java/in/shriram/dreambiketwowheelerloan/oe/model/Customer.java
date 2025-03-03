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
	private String password;
	private String customerMobileNumber;
	private double customerAdditionalMobileNumber;
	private double customerAmountPaidForHome;
	private double customerTotalLoanRequired;
	private String loanStatus="Submit";
	private double onRoadPrice;
	//private 

	
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocuments personalDoc;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DependentInformation depInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress custAddr;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails acdetails;

	@OneToOne(cascade = CascadeType.ALL)
	private GuarantorDetails gdetails;
 
	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loandisburst;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ledger led;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionletter;

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerVerification custVerification;



}
