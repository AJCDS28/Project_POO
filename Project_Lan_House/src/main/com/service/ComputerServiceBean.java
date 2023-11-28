package main.com.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import EntradaSaida.EntradaSaida;
import main.com.model.computer.Computer;
import main.com.model.computer.ComputerUse;
import main.com.model.computer.ComputerUseHistory;
import main.com.model.computer.Reserved;
import main.com.model.payment.TimeType;
import main.com.model.user.Customer;

public class ComputerServiceBean {

	HashMap<Integer, Computer> computerMap = new HashMap<Integer, Computer>();

	HashMap<Integer, ComputerUse> computerUseMap = new HashMap<Integer, ComputerUse>();

	HashMap<Integer, ComputerUseHistory> computerUseHistoryMap = new HashMap<Integer, ComputerUseHistory>();

	HashMap<Integer, Reserved> reservedMap = new HashMap<Integer, Reserved>();

    public void initializeComputers(Integer quantity) {
        for (int i = 1; i <= quantity; i++) {
            addComputer(new Computer(i));
        }
    }

    public void locateComputer(Customer user, TimeType timeType) {
        Boolean isPayed = EntradaSaida.getBoolean("Já está pago?");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Math.round(timeType.getTime() * 60));
        Computer computer = null;
        for (Computer pc : computerMap.values()) {
            if (!pc.isInUse() && reservedMap.get(pc.getId()).getReserveDate().before(calendar.getTime())) {
                computer = computerMap.get(pc.getId());
                break;
            }
        }

        if (computer == null) {
            EntradaSaida.showMessage("Todos os computadores estão reservados, tente novamente mais tarde ou pegue um tempo menor");
            return;
        }

        computer.setInUse(true);
        computer.setLastUse(new Date());

        ComputerUse computerUse = new ComputerUse();
        computerUse.setId(computerUseMap.keySet().size() + 1);
        computerUse.setInitialTime(new Date());
        computerUse.setEndTime(calendar.getTime());
        computerUse.setComputer(computer.getId());
        computerUse.setUserCpf(user.getCpf());
        computerUse.setBilling(timeType.getValue());
        computerUse.setPayed(isPayed);

        this.addComputerUse(computerUse);
        this.addComputerUseHistory(computerUse);
    }

    public void addComputerUseHistory(ComputerUse computerUse) {
        ComputerUseHistory pc = new ComputerUseHistory();
        pc.parse(computerUse);
        pc.setId(computerUseHistoryMap.keySet().size() + 1);
        computerUseHistoryMap.put(pc.getId(), pc);
    }

    public Boolean isAllUsed() {
        return computerMap.keySet().equals(computerUseMap.keySet());
    }

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
