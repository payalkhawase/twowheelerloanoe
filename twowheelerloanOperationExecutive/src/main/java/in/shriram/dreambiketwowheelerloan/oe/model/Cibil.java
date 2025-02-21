package in.shriram.dreambiketwowheelerloan.oe.model;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Cibil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cibilId;
	private int cibilScore;
	private Date cibilscoredDateTime = new Date();
	private String status;
	
	private String cibilRemark;
	
	
	public int getCibilId() {
		return cibilId;
	}
	public void setCibilId(int cibilId) {
		this.cibilId = cibilId;
	}
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		Random randam = new Random();
		
		int low = 300;
		int high = 900;
		int score =  randam.nextInt(high-low) + low;
		
		this.cibilScore = score;
	}
	public Date getCibilscoredDateTime() {
		return cibilscoredDateTime;
	}
	public void setCibilscoredDateTime(Date cibilscoredDateTime) {
		this.cibilscoredDateTime = new Date();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		
		String status1;
		
		if(this.getCibilScore() > 700)
			status1 = "Approved";
		else
			status1="Rejected";
		
		this.status = status1;
	}
	public String getCibilRemark() {
		return cibilRemark;
	}
	public void setCibilRemark(String cibilRemark) {
		
		String remark = "Good";
		
		
		if(this.getCibilScore() >= 300 && this.getCibilScore() <= 600)
			remark="Need Help";
		
		if(this.getCibilScore() > 600 && this.getCibilScore() <= 700)
			remark="Average";
		
		if(this.getCibilScore() > 700 && this.getCibilScore() <= 760)
			remark="Good";
		
		if(this.getCibilScore() > 760 && this.getCibilScore() <= 800)
			remark="Very Good";
		
		if(this.getCibilScore() > 800 && this.getCibilScore() <= 900)
			remark="Excellent";
		
		this.cibilRemark = remark;
	}
	
	
	
}
