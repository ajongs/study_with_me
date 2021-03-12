package service;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {
    void signUp(User user);
    public List<User> getAllUsers();
    User getUser(int class_no);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
