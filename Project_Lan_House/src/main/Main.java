package main;

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
	}

}
