package Menu.com.service;

import Menu.com.model.user.Customer;

public interface UserService {

    public Customer createNewUser();

    public Customer findUser();

    public Customer validateUser();

    public Customer getUser(String cpf);
}
