package main.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EntradaSaida.EntradaSaida;
import main.com.model.user.Address;
import main.com.model.user.Customer;
import main.com.model.user.Employee;

public class UserServiceBean {
    private List<Employee> employeeList = new ArrayList<Employee>();
    private Employee employee;
    private Map<Integer, Customer> userMap = new HashMap<Integer, Customer>();

    public Customer createNewUser() {
        return createNewUser(null);
    }

    public Customer createNewUser(Integer cpf) {
        Boolean isFullUser = EntradaSaida.getBoolean("Deseja realizar o cadastro completo?");

        Customer user = new Customer();
        user.setName(EntradaSaida.getText("Nome"));
        user.setShortname(EntradaSaida.getText("Apelido"));
        user.setCpf(cpf != null ? cpf : EntradaSaida.getNumber("CPF"));
        user.setEmailAdress(EntradaSaida.getText("Email"));
        user.setPhoneNumber(EntradaSaida.getText("Telefone"));

        if (isFullUser) {
            Address address = new Address();
            address.setPostalCode(EntradaSaida.getText("CEP"));
            address.setStreet(EntradaSaida.getText("Rua"));
            address.setNumber(EntradaSaida.getNumber("Número"));
            address.setCity(EntradaSaida.getText("Cidade"));
            address.setState(EntradaSaida.getText("Estado"));
            address.setCountry(EntradaSaida.getText("País"));
        }

        this.addUser(user);
        return user;
    }

    public Customer findUser() {
        if (this.isUsersEmpty()) {
            if (EntradaSaida.getBoolean("Não há nenhum cliente, deseja criar um?")) return this.createNewUser();
            else return null;
        }

        Integer cpf = EntradaSaida.getNumber("Digite o CPF do cliente");
        return this.getUser(cpf);
    }

    public Customer validateUser() {
        if (isUsersEmpty()) {
            EntradaSaida.showMessage("Não há nenhum cliente cadastrado");
            return null;
        }

        Customer user = userMap.get(EntradaSaida.getNumber("Digite o CPF do cliente"));
        if (user == null) {
            EntradaSaida.showMessage("Cliente não encontrado");
        }
        return user;
    }

    public Boolean isUsersEmpty() {
        return userMap.entrySet().isEmpty();
    }

    public Customer getUser(Integer cpf) {
        if (userMap.get(cpf) != null) return userMap.get(cpf);

        if (EntradaSaida.getBoolean("Cliente não encontrado, deseja criar esse cliente?")) {
            return this.createNewUser(cpf);
        }

        return null;
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
