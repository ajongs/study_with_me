package mapper;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    void signUp(User user);
    List<User> getAllUsers();
    User getUser(String id);
}
