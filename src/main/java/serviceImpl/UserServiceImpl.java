package serviceImpl;

import domain.User;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

import java.util.List;
@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper usermapper;
    @Override
    public List<User> getAllUsers() {
        return usermapper.getAllUsers();
    }

    public User getUser(int class_no){
        return usermapper.getUser(class_no);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void insert(User user) {
        usermapper.insert(user);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void update(User user) {
        usermapper.update(user);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void delete(User user) {
        usermapper.delete(user);
    }
}
