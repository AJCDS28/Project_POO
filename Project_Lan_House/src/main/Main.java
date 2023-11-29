package main;

import java.util.Timer;
import java.util.TimerTask;

import EntradaSaida.EntradaSaida;
import main.com.model.payment.TimeType;
import main.com.model.user.Customer;
import main.com.service.ComputerService;
import main.com.service.ComputerServiceBean;
import main.com.service.PaymentService;
import main.com.service.PaymentServiceBean;
import main.com.service.TimeTypes;
import main.com.service.UserService;
import main.com.service.UserServiceBean;

public class Main {

	public static void main(String[] args) {
		TimeTypes timeTypes = new TimeTypes();
		UserService userService = new UserServiceBean();
		PaymentService paymentService = new PaymentServiceBean();
		ComputerService computerService = new ComputerServiceBean();

		computerService.initializeComputers(EntradaSaida.getNumber("Quantos computadores a Lan House terá?"));

		TimerTask minuteSchedule = new TimerTask() {
            @Override
            public void run() {
				System.out.println("Verificação Minute Schedule");
                computerService.verifyScheduled(userService, paymentService);
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(minuteSchedule, 0, 60 * 1000);

		Integer option = 0;
		while(option != EntradaSaida.NUMBER_OF_OPTIONS) {
			option = EntradaSaida.mainScreen();
			switch (option) {
				case 1:
					userService.createNewUser();
					break;

				case 2:
					timeTypes.createNewTime();
					break;

				case 3:
					if (computerService.isAllUsed()) {
						EntradaSaida.showMessage("Infelizmente todos os computadores estão ocupados, volte mais tarde");
						break;
					}
					Customer customer = userService.findUser();
					if (customer == null) break;
					TimeType timeType = timeTypes.findTimeType();
					if (timeType == null) break;
					Double defaulte = paymentService.isDefaulter(customer);
					if (defaulte > 0.0d) {
						EntradaSaida.showMessage("Usuário está devendo, infelizmente não será possível alocar até que pague o valor de R$ " + defaulte.toString());
						break;
					}
					computerService.locateComputer(customer, timeType, paymentService);
					break;

				case 4:
					computerService.deslocateComputer(userService, paymentService);
					break;

				case 5:
					Customer user = userService.validateUser();
					if (user != null) {
						paymentService.receivePayment(user, EntradaSaida.getDouble("Valor a ser recebido"));
					}
					break;

				case 6:
					computerService.listFreeComputer();
					break;

				case 7:
					computerService.listOccupiedComputers();
					break;

				case 8:
					paymentService.printCash();
					break;

				case 9:
					break;

				case 10:
					break;

				case 11:
					break;

				default:
					EntradaSaida.showMessage("Opção inválida");
					break;
			}
		}
		EntradaSaida.showMessage("Fim da execução do programa");
	}

}
