package main.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.model.user.Customer;
import main.com.model.user.Employee;

public class UserServiceBean {
    private List<Employee> employeeList = new ArrayList<Employee>();
    private Employee employee;
    private Map<Integer, Customer> userMap = new HashMap<Integer, Customer>();

    public Customer getUser(Integer cpf) {
        return userMap.get(cpf);
    }

    public void addUser(Customer user) {
        userMap.put(user.getCpf(), user);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
