package in.shriram.dreambiketwowheelerloan.oe.model;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Cibil {

	@Id 
	private int cibilId;
	private int cibilScore;
	private Date cibilscoredDateTime = new Date();
	private String status;
	private String cibilRemark;
	
	
	
	
}
