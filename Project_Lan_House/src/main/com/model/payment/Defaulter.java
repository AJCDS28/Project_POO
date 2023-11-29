package main.com.model.payment;

public class Defaulter {

	private Integer userCpf;
	private Double amountOwed = 0.00d;

	public Integer getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(Integer userCpf) {
		this.userCpf = userCpf;
	}
	public Double getAmountOwed() {
		return amountOwed;
	}
	public void setAmountOwed(Double amountOwed) {
		this.amountOwed = amountOwed;
	}
}
