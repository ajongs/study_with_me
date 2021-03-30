package util;

import domain.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String key = "secretKey";

    public String createToken(User user){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 2);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", user.getId());

        Claims claims = Jwts.claims(payloads)
                .setSubject("access_Token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(cal.getTimeInMillis()));

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    public Boolean validateToken(String JwtToken){
        if(!JwtToken.isEmpty()){
            try{
                Jwts.parser().setSigningKey(this.key).parseClaimsJws(JwtToken);
                return true;
            } catch(SignatureException e){ //서명 불일치
                System.out.println("Invalid signature " + e);
            } catch(ExpiredJwtException e){ //만료
                System.out.println("Expired JWT Token" + e);
            } catch(UnsupportedJwtException e){ //JWT 형식 불일치
                System.out.println("Unsupported JWT token"+e);
            } catch(MalformedJwtException e){ //jwt 구성이 올바르지 않을때
                System.out.println("Invalid JWT Token" + e);
            }
        }
        return false;
    }

}
