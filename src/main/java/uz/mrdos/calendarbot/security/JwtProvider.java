package uz.mrdos.calendarbot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.mrdos.calendarbot.entity.RoleEntity;

import java.util.Date;

@Component
public class JwtProvider {

    private static final long expireTime =1000*60*60*24;
    private static final String keyWord =")Sc=max4xaksiysbsiAhgaNAgcnsmscikAknmAnbnA454cac*0c.x31?as";

    public String generateToken(String  username, RoleEntity roleEntities){

        Date expireDate = new Date(System.currentTimeMillis() + expireTime);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", roleEntities)
                .signWith(SignatureAlgorithm.HS512, keyWord)
                .compact();
        return token;
    }

    public String getUserNameFromToken(String token){
        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(keyWord)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return  username;

        }catch (Exception e){
            return null;
        }
    }

}
