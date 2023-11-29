package Menu.com.model.computer;
import java.util.Date;

public class Computer {

	private Integer id;
	private boolean active = Boolean.TRUE;
	private boolean inUse = Boolean.FALSE;
	private Date lastUse;

	public Computer() {}

	public Computer(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
