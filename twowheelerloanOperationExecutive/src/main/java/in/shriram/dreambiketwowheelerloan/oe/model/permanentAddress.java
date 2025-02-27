package in.shriram.dreambiketwowheelerloan.oe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class permanentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int permanentAddressId;
	private String areaname;
	private String cityname;
	private String district;
	private String state;
	private long pincode;
	private int houseNumber;
	private String streetName;

}
