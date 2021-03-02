package mapper;

import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAllUsers();
    User getUser(int class_no);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
