package main.com.model.payment;
import java.util.Date;

public class Cash {

	private Double balance;
	private Date lastMovement;
	private Double receivable;

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getLastMovement() {
		return lastMovement;
	}
	public void setLastMovement(Date lastMovement) {
		this.lastMovement = lastMovement;
	}
	public Double getReceivable() {
		return receivable;
	}
	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}
}
