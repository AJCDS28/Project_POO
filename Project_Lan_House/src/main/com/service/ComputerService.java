package main.com.service;

import main.com.model.payment.TimeType;
import main.com.model.user.Customer;

public interface ComputerService {

    public void initializeComputers(Integer quantity);

    public void locateComputer(Customer user, TimeType timeType, PaymentService paymentService);

    public void deslocateComputer(UserService userService, PaymentService paymentService);

    public void listFreeComputer();

    public void listOccupiedComputers();

    public void verifyScheduled(UserService userService, PaymentService paymentService);

    public Boolean isAllUsed();
}
