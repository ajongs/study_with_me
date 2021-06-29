package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String key = "secretKey";

    public String createToken(String id, String subject){
        Calendar cal = Calendar.getInstance();
        if(subject.equals("access_Token")){
            cal.add(Calendar.MINUTE, 2);
        }
        else
        {
            cal.add(Calendar.DATE, 1);
        }

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", id);

        Claims claims = Jwts.claims(payloads)
                .setSubject(subject)
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
                Claims claims = Jwts.parser().setSigningKey(this.key.getBytes()).parseClaimsJws(JwtToken).getBody();
                if(claims.getSubject().equals("access_Token")|| claims.getSubject().equals("refresh_Token")){
                    return true;
                }
            } catch(SignatureException e){ //서명 불일치
                System.out.println("Invalid signature " + e);
            } catch(ExpiredJwtException e){ //만료
                System.out.println("Expired JWT Token " + e);
            } catch(UnsupportedJwtException e){ //JWT 형식 불일치
                System.out.println("Unsupported JWT token : "+e);
            } catch(MalformedJwtException e){ //jwt 구성이 올바르지 않을때
                System.out.println("Invalid JWT Token " + e);
            }
        }
        return false;
    }

    public Map<String, Object> getTokenPayload(String token){
        //TODO 페이로드 리턴
        //TODO Base64URL로 decode 한 후 payload 리턴
        String splitToken = token.split("\\.")[1];
        //string.split은 정규표현식 사용 .은 메타문자로 사용댐 --> .을 일반문자로 사용하기 위해 escape 문자인 \\필요
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = null;
        try {
            payload = objectMapper.readValue(Base64UrlCodec.BASE64URL.decode(splitToken), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payload;
    }
}
