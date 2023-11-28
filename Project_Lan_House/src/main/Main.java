package main;

import EntradaSaida.EntradaSaida;
import main.com.model.payment.TimeType;
import main.com.model.user.Customer;
import main.com.service.ComputerServiceBean;
import main.com.service.PaymentServiceBean;
import main.com.service.TimeTypes;
import main.com.service.UserServiceBean;

public class Main {

	public static void main(String[] args) {
		TimeTypes timeTypes = new TimeTypes();
		UserServiceBean userService = new UserServiceBean();
		PaymentServiceBean paymentService = new PaymentServiceBean();
		ComputerServiceBean computerService = new ComputerServiceBean();

		computerService.initializeComputers(EntradaSaida.getNumber("Quantos computadores a Lan House terá?"));

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
					computerService.locateComputer(customer, timeType);
					break;

				case 4:
					break;

				case 5:
					break;

				case 6:
					break;

				case 7:
					break;

				case 8:
					break;

				case 9:
					break;

				case 10:
					break;

				case 11:
					break;

				case 12:
					break;

				default:
					EntradaSaida.showMessage("Opção inválida");
					break;
			}
		}
		EntradaSaida.showMessage("Fim da execução do programa");
	}

}
