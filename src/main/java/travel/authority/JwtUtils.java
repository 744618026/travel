package travel.authority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public class JwtUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long expiration = 1000*24*60*60;
    public static final String secret = "secret";
    public static final String claimsRole = "role";

    public static String createToken(String username, String role) {
        Map<String, Object> map = new HashMap<>();
        map.put(claimsRole, role);
        map.put("username",username);
        String token = Jwts.builder()
                .setSubject(username)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return token;
    }
    public static String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username").toString();
    }
    public static String getUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("role").toString();
    }

    public static boolean isExpiration(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private static Claims getClaims(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}