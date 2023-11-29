package Menu.com.model.computer;
import java.util.Date;

public class ComputerUseHistory {

	private Integer id;
	private Date initialTime;
	private Date endTime;
	private String userCpf;
	private Integer computerId;
	private Double billing;
	private boolean payed;
	private Date finishedDate;

	public void parse(ComputerUse computerUse) {
		this.initialTime = computerUse.getInitialTime();
		this.endTime = computerUse.getEndTime();
		this.userCpf = computerUse.getUserCpf();
		this.computerId = computerUse.getComputer();
		this.billing = computerUse.getBilling();
		this.payed = computerUse.isPayed();
	}

	public Integer getId() {
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
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public Integer getComputerId() {
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
