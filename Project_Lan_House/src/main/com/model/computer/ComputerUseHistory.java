package main.com.model.computer;
import java.util.Date;

public class ComputerUseHistory {

	private int id;
	private Date initialTime;
	private Date endTime;
	private int userCpf;
	private int computerId;
	private Double billing;
	private boolean payed;
	private Date finishedDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInitialTime() {
		return initialTime;
	}
	public void setInitialTime(Date initialTime) {
		this.initialTime = initialTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(int userCpf) {
		this.userCpf = userCpf;
	}
	public int getComputerId() {
		return computerId;
	}
	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}
	public Double getBilling() {
		return billing;
	}
	public void setBilling(Double billing) {
		this.billing = billing;
	}
	public boolean isPayed() {
		return payed;
	}
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
}
