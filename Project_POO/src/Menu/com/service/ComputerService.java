package Menu.com.service;

import Menu.com.model.payment.TimeType;
import Menu.com.model.user.Customer;

public interface ComputerService {

    public void initializeComputers(Integer quantity);

    public void locateComputer(Customer user, TimeType timeType, PaymentService paymentService);

    public void deslocateComputer(UserService userService, PaymentService paymentService);

    public void listFreeComputer();

    public void listOccupiedComputers();

    public void verifyScheduled(UserService userService, PaymentService paymentService);

    public Boolean isAllUsed();

	public void listComputersUse();
}
