package main.com.service;

import java.util.HashMap;

import main.com.model.computer.Computer;
import main.com.model.computer.ComputerUse;
import main.com.model.computer.ComputerUseHistory;
import main.com.model.computer.Reserved;

public class ComputerServiceBean {

	HashMap<Integer, Computer> computerMap = new HashMap<Integer, Computer>();

	HashMap<Integer, ComputerUse> computerUseMap = new HashMap<Integer, ComputerUse>();

	HashMap<Integer, ComputerUseHistory> computerUseHistoryMap = new HashMap<Integer, ComputerUseHistory>();

	HashMap<Integer, Reserved> reservedMap = new HashMap<Integer, Reserved>();

	public Computer getComputer(Integer id) {
        return computerMap.get(id);
    }

    public void addComputer(Computer computer) {
        computerMap.put(computer.getId(), computer);
    }

	public ComputerUse getComputerUse(Integer id) {
        return computerUseMap.get(id);
    }

    public void addComputerUse(ComputerUse computerUse) {
        computerUseMap.put(computerUse.getId(), computerUse);
    }

	public ComputerUseHistory getComputerUseHistory(Integer id) {
        return computerUseHistoryMap.get(id);
    }

    public void addComputerUseHistory(ComputerUseHistory computerUseHistory) {
        computerUseHistoryMap.put(computerUseHistory.getId(), computerUseHistory);
    }

	public Reserved getReserved(Integer computerId) {
        return reservedMap.get(computerId);
    }

    public void addReserved(Reserved reserved) {
        reservedMap.put(reserved.getComputerId(), reserved);
    }
}
