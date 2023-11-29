package main.com.model.computer;
import java.util.Date;

public class ComputerUse {

	private Integer id;
	private Date initialTime;
	private Date endTime;
	private String userCpf;
	private Integer computer;
	private Double billing;
	private boolean payed;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public Integer getComputer() {
		return computer;
	}
	public void setComputer(Integer computer) {
		this.computer = computer;
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
}
