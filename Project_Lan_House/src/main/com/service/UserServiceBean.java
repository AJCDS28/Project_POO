package main.com.service;

import java.util.HashMap;
import java.util.Map;

import EntradaSaida.EntradaSaida;
import main.com.model.user.Address;
import main.com.model.user.Customer;

public class UserServiceBean implements UserService {
    private Map<String, Customer> userMap = new HashMap<String, Customer>();

    @Override
    public Customer createNewUser() {
        return createNewUser(null);
    }

    private Customer createNewUser(String cpf) {
        Boolean isFullUser = EntradaSaida.getBoolean("Deseja realizar o cadastro completo?");

        Customer user = new Customer();
        user.setCpf(cpf != null ? cpf : EntradaSaida.getCpf("CPF"));

        if (userMap.get(user.getCpf()) != null) {
            EntradaSaida.showMessage("Cliente já cadastrado");
            return null;
        }

        user.setName(EntradaSaida.getText("Nome"));
        if (isFullUser)  user.setShortname(EntradaSaida.getText("Apelido"));
        if (isFullUser) user.setEmailAdress(EntradaSaida.getText("Email"));
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

    @Override
    public Customer findUser() {
        if (this.isUsersEmpty()) {
            if (EntradaSaida.getBoolean("Não há nenhum cliente, deseja criar um?")) return this.createNewUser();
            else return null;
        }

        String cpf = EntradaSaida.getCpf("Digite o CPF do cliente");
        return this.getUser(cpf);
    }

    @Override
    public Customer validateUser() {
        if (isUsersEmpty()) {
            EntradaSaida.showMessage("Não há nenhum cliente cadastrado");
            return null;
        }

        Customer user = userMap.get(EntradaSaida.getCpf("Digite o CPF do cliente"));
        if (user == null) {
            EntradaSaida.showMessage("Cliente não encontrado");
        }
        return user;
    }

    @Override
    public Customer getUser(String cpf) {
        if (userMap.get(cpf) != null) return userMap.get(cpf);

        if (EntradaSaida.getBoolean("Cliente não encontrado, deseja criar esse cliente?")) {
            return this.createNewUser(cpf);
        }

        return null;
    }

    private Boolean isUsersEmpty() {
        return userMap.entrySet().isEmpty();
    }

    private void addUser(Customer user) {
        userMap.put(user.getCpf(), user);
    }
}
