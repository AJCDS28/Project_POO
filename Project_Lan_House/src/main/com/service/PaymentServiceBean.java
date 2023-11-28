package main.com.service;

import java.util.HashMap;
import java.util.Map;

import main.com.model.payment.Defaulter;
import main.com.model.user.Customer;

public class PaymentServiceBean {
    private Map<Integer, Defaulter> defaulterMap = new HashMap<Integer, Defaulter>();

    public Double isDefaulter(Customer user) {
        for (Defaulter def : defaulterMap.values()) {
            if (def.getUserCpf().equals(user.getCpf())) return def.getAmountOwed();
        }
        return 0.0d;
    }

    public Defaulter getDefaulter(Integer cpf) {
        return defaulterMap.get(cpf);
    }

    public void addTimeType(Defaulter defaulter) {
        defaulterMap.put(defaulter.getUserCpf(), defaulter);
    }
}
