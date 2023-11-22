package main.com.service;

import java.util.HashMap;
import java.util.Map;

import main.com.model.computer.Computer;
import main.com.model.computer.ComputerUse;
import main.com.model.computer.ComputerUseHistory;
import main.com.model.computer.Reserved;

public class ComputerServiceBean {

	HashMap<Integer, Computer> computerMap = new HashMap<Integer, Computer>();
	
	HashMap<Integer, ComputerUse> computerUseMap = new HashMap<Integer, ComputerUse>();
	
	HashMap<Integer, ComputerUseHistory> computerUseHistoryMap = new HashMap<Integer, ComputerUseHistory>();
	
	HashMap<Integer, Reserved> reservedMap = new HashMap<Integer, Reserved>();
	
	
	private Computer findComputer(){
		return new Computer();
	}
	
	private Computer getComputer(Integer computerId) {
		return new Computer();
	}
	
	
}
