package main.com.service;

import main.com.model.user.Customer;

public interface PaymentService {

    public Double isDefaulter(Customer user);

    public void receivePayment(Customer user, Double value);

    public void addDefaulter(Customer user, Double value);

    public void printCash();
}
