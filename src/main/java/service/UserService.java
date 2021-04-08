package service;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserService {
    void signUp(User user);
    Map<String, String> login(User user);
    public List<User> getAllUsers();
    User getUser(String id);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
