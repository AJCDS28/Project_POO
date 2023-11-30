package Menu.com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import EntradaSaida.EntradaSaida;
import Menu.com.model.payment.Cash;
import Menu.com.model.payment.Defaulter;
import Menu.com.model.user.Customer;

public class PaymentServiceBean implements PaymentService {
    private Map<String, Defaulter> defaulterMap = new HashMap<String, Defaulter>();
    private Cash cash = new Cash();

    @Override
    public Double isDefaulter(Customer user) {
        for (Defaulter def : defaulterMap.values()) {
            if (def.getUserCpf().equals(user.getCpf())) return def.getAmountOwed();
        }
        return 0.0d;
    }

    @Override
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

    @Override
    public void addDefaulter(Customer user, Double value) {
		Defaulter defaulter= defaulterMap.get(user.getCpf()) != null ? defaulterMap.get(value) : new Defaulter();
        if (defaulter.getUserCpf() == null) defaulter.setUserCpf(user.getCpf());
        defaulter.setAmountOwed(defaulter.getAmountOwed()+value);

        this.addDefaulter(defaulter);
    }

    @Override
    public void printCash() {
        EntradaSaida.printCash(cash);
    }

    private void addDefaulter(Defaulter defaulter) {
        defaulterMap.put(defaulter.getUserCpf(), defaulter);
        this.increaseReceivable(defaulter.getAmountOwed());
    }

    private void increaseBalance(Double value) {
        this.cash.setBalance(this.cash.getBalance() + value);
        this.cash.setLastMovement(new Date());
    }

    private void increaseReceivable(Double value) {
        this.cash.setReceivable(this.cash.getReceivable() + value);
    }

    private void decreaseReceivable(Double value) {
        this.cash.setReceivable(this.cash.getReceivable() - value);
    }
    public void printDefaulters() {
        if(defaulterMap.keySet().isEmpty()) {
            EntradaSaida.showMessage("Não há clientes inadimplentes");
        } else {
            EntradaSaida.listDefaulters(defaulterMap.values());
        }
    }

}