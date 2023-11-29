package main.com.service;

import java.util.HashMap;
import java.util.Map;

import EntradaSaida.EntradaSaida;
import main.com.model.payment.TimeType;

public class TimeTypes {
	Map<Float, TimeType> timeTypes = new HashMap<Float, TimeType>();

    public TimeType createNewTime() {
        return this.createNewTime(null);
    }

    public TimeType createNewTime(Float time) {
        TimeType timeType = new TimeType();
        timeType.setTime(time != null ? time : EntradaSaida.getFloat("Insira o tempo em horas"));
        timeType.setValue(EntradaSaida.getDouble("Insira o valor"));
        timeType.setDescription(EntradaSaida.getText("Insira a descrição desse tempo"));

        this.addTimeType(timeType);
        return timeType;
    }

    public TimeType findTimeType() {
        if (this.isTimeTypesEmpty()) {
            if (EntradaSaida.getBoolean("Não há nenhum Tempo criado, deseja criar um?")) return this.createNewTime();
            else return null;
        }

        Float time = EntradaSaida.getFloat("Digite o Tempo em horas");
        return this.getTimeType(time);
    }

    public Boolean isTimeTypesEmpty() {
        return timeTypes.entrySet().isEmpty();
    }

    public TimeType getTimeType(Float time) {
        if (timeTypes.get(time) != null) return timeTypes.get(time);

        if (EntradaSaida.getBoolean("Tempo não encontrado, deseja cadastrar esse tempo?")) {
            return this.createNewTime(time);
        }

        return null;
    }

    public TimeType getTimeTypes(Float time) {
        return timeTypes.get(time);
    }

    public void addTimeType(TimeType timeType) {
        timeTypes.put(timeType.getTime(), timeType);
    }
}
