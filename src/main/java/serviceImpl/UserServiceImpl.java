package serviceImpl;

import domain.User;
import exception.unauthenticateException;
import mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import util.JwtTokenProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper usermapper;

    @Autowired
    JwtTokenProvider jwt;

    @Override
    public void signUp(User user){
        //비밀번호 암호화
        user.setPw(BCrypt.hashpw(user.getPw(), BCrypt.gensalt()));
        usermapper.signUp(user);
    }

    @Override
    public Map<String, String> login(User user) {
        //암호 체크
        User db = usermapper.getUser(user.getId());
        if(!BCrypt.checkpw(user.getPw(), db.getPw())){
            throw new unauthenticateException();
        }
        //로그인 성공시 토큰 생성
        Map<String, String> token = new HashMap<>();
        token.put("access_token",jwt.createToken(user));
        return token;
    }

    @Override
    public List<User> getAllUsers() {
        return usermapper.getAllUsers();
    }

    public User getUser(String id){
        return usermapper.getUser(id);
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
