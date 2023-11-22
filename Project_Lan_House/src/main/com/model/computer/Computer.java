package main.com.model.computer;
import java.util.Date;

public class Computer {

	private Integer id;
	private Integer ip;
	private boolean active;
	private boolean inUse;
	private Date lastUse;
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	public Date getLastUse() {
		return lastUse;
	}
	public void setLastUse(Date lastUse) {
		this.lastUse = lastUse;
	}
	
}
