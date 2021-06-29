package serviceImpl;

import domain.User;
import exception.unauthenticateException;
import mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.UserService;
import util.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
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
        String id = user.getId();
        User db = usermapper.getUser(id);
        if(!BCrypt.checkpw(user.getPw(), db.getPw())){
            throw new unauthenticateException();
        }
        //로그인 성공시 토큰 생성
        Map<String, String> token = new HashMap<>();
        token.put("access_Token",jwt.createToken(id, "access_Token"));
        token.put("refresh_Token", jwt.createToken(id, "refresh_Token"));
        return token;
    }

    public Map<String, Object> refresh(){
        //ToDO 여기서 해야 할것
        //TODO refreshToken의 payload에서 id값 가져오기 --> 새로운 access Token, Refresh 토큰을 생성 후 return(완료)
        String id = getUserId();

        Map<String, Object> newToken = new HashMap<>();
        newToken.put("access_Token", jwt.createToken(id.toString(), "access_Token"));
        newToken.put("refresh_Token", jwt.createToken(id.toString(), "refresh_Token"));
        return newToken;
    }
    @Override
    public String getUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        //토큰 유효성 검사
        if(token==null || !jwt.validateToken(token)){
            throw new unauthenticateException();
        }
        //token id 값 가져오기
        Map<String, Object> payload = jwt.getTokenPayload(token);
        String token_id = payload.get("id").toString();
        return token_id;
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
