package com.projectmgr.managecompany.security;

import com.projectmgr.managecompany.models.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.projectmgr.managecompany.security.SecurityConstants.EXPIRATION_TIME;
import static com.projectmgr.managecompany.security.SecurityConstants.SECRET;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    //Generate the token
    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expireDate = new Date(now.getTime()+ EXPIRATION_TIME);

        //Token is the String that's why. Cast to String
        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",(Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        String jwt = Jwts.builder()
                .setSubject(userId)
                .setClaims(claims) //Claims is information about the user.
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();

        return jwt;
    }
    //Validate the token

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex){
            System.out.println("Invalid JTW Signature");
        } catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex){
            System.out.println("JWT claims sting is empty");
        }
        return false;
    }

    //Get user Id from token

    public Long getUserIdFromJWT (String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String  id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
