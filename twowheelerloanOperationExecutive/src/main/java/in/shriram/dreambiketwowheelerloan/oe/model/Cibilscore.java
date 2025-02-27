package in.shriram.dreambiketwowheelerloan.oe.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cibilscore {
	@Id	
	private int cibilId; 
	private int cibilScore;
	private Date cibilscoredDateTime;
	private String status;
	private String cibilRemark;
}
