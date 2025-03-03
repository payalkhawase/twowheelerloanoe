package in.shriram.dreambiketwowheelerloan.oe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmailSender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String message;
	
	
}
