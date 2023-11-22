package main.com.model.computer;
import java.util.Date;

public class Reserved {

	private Date reserveDate;
	private Integer computerId;
	private Integer userCpf;
	private Double billing;
	private boolean payed;

	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Integer getComputerId() {
		return computerId;
	}
	public void setComputerId(Integer computerId) {
		this.computerId = computerId;
	}
	public Integer getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(Integer userCpf) {
		this.userCpf = userCpf;
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
