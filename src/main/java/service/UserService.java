package service;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserService {
    void signUp(User user);
    Map<String, String> login(User user);
    List<User> getAllUsers();
    public Map<String, Object> refresh();
    public String getUserId();
    public Map<String, Object> getTokenPayload();
    User getUser(String id);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
