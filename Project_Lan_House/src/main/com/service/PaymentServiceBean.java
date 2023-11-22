package main.com.service;

import java.util.HashMap;
import java.util.Map;

import main.com.model.payment.Defaulter;

public class PaymentServiceBean {
    private Map<Integer, Defaulter> defaulterMap = new HashMap<Integer, Defaulter>();

    public Defaulter getDefaulter(Integer cpf) {
        return defaulterMap.get(cpf);
    }

    public void addTimeType(Defaulter defaulter) {
        defaulterMap.put(defaulter.getUserCpf(), defaulter);
    }
}
