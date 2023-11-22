package main.com.service;

import java.util.HashMap;
import java.util.Map;

import main.com.model.payment.TimeType;

public class TimeTypes {
	Map<Float, TimeType> timeTypes = new HashMap<Float, TimeType>();

    public TimeType getTimeTypes(Float time) {
        return timeTypes.get(time);
    }

    public void addTimeType(TimeType timeType) {
        timeTypes.put(timeType.getTime(), timeType);
    }
}
