package Menu.com.service;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import EntradaSaida.EntradaSaida;
import Menu.com.model.computer.Computer;
import Menu.com.model.computer.ComputerUse;
import Menu.com.model.computer.ComputerUseHistory;
import Menu.com.model.payment.TimeType;
import Menu.com.model.user.Customer;

public class ComputerServiceBean implements ComputerService {

	HashMap<Integer, Computer> computerMap = new HashMap<Integer, Computer>();

	HashMap<Integer, ComputerUse> computerUseMap = new HashMap<Integer, ComputerUse>();

	HashMap<Integer, ComputerUseHistory> computerUseHistoryMap = new HashMap<Integer, ComputerUseHistory>();

    @Override
    public void initializeComputers(Integer quantity) {
        for (int i = 1; i <= quantity; i++) {
            addComputer(new Computer(i));
        }
    }

    @Override
    public void locateComputer(Customer user, TimeType timeType, PaymentService paymentService) {
        Boolean isPayed = EntradaSaida.getBoolean("Já está pago?");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Math.round(timeType.getTime() * 60));
        Computer computer = null;
        for (Computer pc : computerMap.values()) {
            if (!pc.isInUse()) {
                computer = computerMap.get(pc.getId());
                break;
            }
        }

        if (computer == null) {
            EntradaSaida.showMessage("Ocorreu um erro, tente novamente mais tarde...");
            return;
        }

        computer.setInUse(true);
        computer.setLastUse(new Date());
        computerMap.put(computer.getId(), computer);

        ComputerUse computerUse = new ComputerUse();
        computerUse.setId(computerUseMap.keySet().size() + 1);
        computerUse.setInitialTime(new Date());
        computerUse.setEndTime(calendar.getTime());
        computerUse.setComputer(computer.getId());
        computerUse.setUserCpf(user.getCpf());
        computerUse.setBilling(timeType.getValue());
        computerUse.setPayed(isPayed);

        if (isPayed) paymentService.receivePayment(user, timeType.getValue());

        this.addComputerUse(computerUse);
        this.addComputerUseHistory(computerUse);
    }

    @Override
    public void deslocateComputer(UserService userService, PaymentService paymentService) {
        if (computerUseMap.keySet().isEmpty()) {
            EntradaSaida.showMessage("Nenhum computador em uso");
            return;
        }
        Integer computerId = EntradaSaida.getNumber("Informe o número do computador a ser desalocado");
        ComputerUse computerInUse = null;
        for (ComputerUse pc : computerUseMap.values()) {
            if (pc.getComputer().equals(computerId)) {
                computerInUse = pc;
                break;
            }
        }
        if (computerInUse == null) {
            EntradaSaida.showMessage("Computador não encontrado");
            return;
        }

        Customer user = userService.getUser(computerInUse.getUserCpf());
        if (!computerInUse.isPayed()) {
            if (EntradaSaida.getBoolean("O usuário já irá pagar?")) {
                computerInUse.setPayed(true);
                paymentService.receivePayment(user, computerInUse.getBilling());

            } else {
                paymentService.addDefaulter(user, computerInUse.getBilling());
            }
        }
        this.attComputerUseHistory(user, computerInUse.isPayed());
        computerUseMap.remove(computerInUse.getId());

        Computer computer = computerMap.get(computerId);
        computer.setInUse(false);
        computer.setLastUse(new Date());
        computerMap.put(computer.getId(), computer);
    }

    @Override
    public void listFreeComputer() {
        if (isAllUsed()) {
            EntradaSaida.showMessage("Todos os computadores estão ocupados");
        } else {
            EntradaSaida.listFreeComputers(computerMap.values());
        }
    }

    @Override
    public void listOccupiedComputers() {
        if (computerUseMap.keySet().isEmpty()) {
            EntradaSaida.showMessage("Não há nenhum computador ocupado");
        } else {
            EntradaSaida.listOccupiedComputers(computerUseMap.values());
        }
    }

    @Override
    public void verifyScheduled(UserService userService, PaymentService paymentService) {
        Date now = new Date();
        for (ComputerUse pc : computerUseMap.values()) {
            if (pc.getEndTime().before(now)) {
                silentDeslocateComputer(pc, userService, paymentService);
            }
        }
    }

    private void silentDeslocateComputer(ComputerUse computerInUse, UserService userService, PaymentService paymentService) {
        if (computerUseMap.keySet().isEmpty()) return;

        Customer user = userService.getUser(computerInUse.getUserCpf());
        if (!computerInUse.isPayed()) {
            paymentService.addDefaulter(user, computerInUse.getBilling());
        }
        this.attComputerUseHistory(user, computerInUse.isPayed());
        computerUseMap.remove(computerInUse.getId());

        Computer computer = computerMap.get(computerInUse.getComputer());
        computer.setInUse(false);
        computer.setLastUse(new Date());
        computerMap.put(computer.getId(), computer);
    }

    @Override
    public Boolean isAllUsed() {
        return computerMap.keySet().equals(computerUseMap.keySet());
    }

    private void attComputerUseHistory(Customer user, Boolean isPayed) {
        ComputerUseHistory computerUseHistory = null;
        for (ComputerUseHistory pc : computerUseHistoryMap.values()) {
            if (!pc.isPayed() && pc.getUserCpf().equals(user.getCpf())) {
                computerUseHistory = pc;
                computerUseHistory.setPayed(isPayed);
                computerUseHistory.setFinishedDate(new Date());
                break;
            }
        }
        this.addComputerUseHistory(computerUseHistory);
    }

    private void addComputerUseHistory(ComputerUse computerUse) {
        ComputerUseHistory pc = new ComputerUseHistory();
        pc.parse(computerUse);
        pc.setId(computerUseHistoryMap.keySet().size() + 1);
        computerUseHistoryMap.put(pc.getId(), pc);
    }

    private void addComputer(Computer computer) {
        computerMap.put(computer.getId(), computer);
    }

    private void addComputerUse(ComputerUse computerUse) {
        computerUseMap.put(computerUse.getId(), computerUse);
    }

    private void addComputerUseHistory(ComputerUseHistory computerUseHistory) {
        computerUseHistoryMap.put(computerUseHistory.getId(), computerUseHistory);
    }
    public void listComputersUse() { 
    	if(computerUseHistoryMap.keySet().isEmpty()) { 
    		EntradaSaida.showMessage("Não há histórico de uso de computadores"); 
    	} else { 
    		EntradaSaida.listComputersUse(computerUseHistoryMap.values()); 
    		} 
    	}
}
