package main.com.model.payment;

public class Defaulter {

	private String userCpf;
	private Double amountOwed = 0.00d;

	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public Double getAmountOwed() {
		return amountOwed;
	}
	public void setAmountOwed(Double amountOwed) {
		this.amountOwed = amountOwed;
	}
}
