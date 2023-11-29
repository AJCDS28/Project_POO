package main.com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import EntradaSaida.EntradaSaida;
import main.com.model.payment.Cash;
import main.com.model.payment.Defaulter;
import main.com.model.user.Customer;

public class PaymentServiceBean {
    private Map<String, Defaulter> defaulterMap = new HashMap<String, Defaulter>();
    private Cash cash = new Cash();

    public Double isDefaulter(Customer user) {
        for (Defaulter def : defaulterMap.values()) {
            if (def.getUserCpf().equals(user.getCpf())) return def.getAmountOwed();
        }
        return 0.0d;
    }

    public void receivePayment(Customer user, Double value) {
        Defaulter defaulter = defaulterMap.get(user.getCpf());
        if (defaulterMap.get(user.getCpf()) != null) {
            defaulter.setAmountOwed(defaulter.getAmountOwed() - value);
            this.decreaseReceivable(value);

            if (defaulter.getAmountOwed() > 0.00d) {
                EntradaSaida.showMessage("Cliente ainda deve R$ " + defaulter.getAmountOwed());
                defaulterMap.put(defaulter.getUserCpf(), defaulter);
            } else {
                defaulterMap.remove(defaulter.getUserCpf());
            }
        }

        this.increaseBalance(value);
    }

    public Defaulter getDefaulter(Long cpf) {
        return defaulterMap.get(cpf);
    }

    public void addDefaulter(Customer user, Double value) {
        Defaulter defaulter= defaulterMap.get(user.getCpf()) != null ? defaulterMap.get(value) : new Defaulter();
        if (defaulter.getUserCpf() == null) defaulter.setUserCpf(user.getCpf());
        defaulter.setAmountOwed(defaulter.getAmountOwed()+value);

        this.addDefaulter(defaulter);
    }

    public void addDefaulter(Defaulter defaulter) {
        defaulterMap.put(defaulter.getUserCpf(), defaulter);
        this.increaseReceivable(defaulter.getAmountOwed());
    }

    public void printCash() {
        EntradaSaida.printCash(cash);
    }

    public void increaseBalance(Double value) {
        this.cash.setBalance(this.cash.getReceivable() + value);
        this.cash.setLastMovement(new Date());
    }

    public void decreaseBalance(Double value) {
        this.cash.setBalance(this.cash.getReceivable() - value);
        this.cash.setLastMovement(new Date());
    }

    public void increaseReceivable(Double value) {
        this.cash.setReceivable(this.cash.getReceivable() + value);
    }

    public void decreaseReceivable(Double value) {
        this.cash.setReceivable(this.cash.getReceivable() - value);
    }
}
