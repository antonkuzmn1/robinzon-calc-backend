//package cloud.robinzon.backend.security.jwt;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.hibernate.cfg.Environment;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//
//import java.util.Date;
//
//public class JwtUtil {
//
//    private static final String SECRET_KEY = Environment
//            .getProperties().getProperty("jwt.secret.key");
//
//    public static String generateToken(String username) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + 3600000); // 1 hour
//
//        //noinspection deprecation
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    public static String getUsernameFromToken(String token) {
//        //noinspection deprecation
//        Claims claims = Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            //noinspection deprecation
//            Jwts.parser()
//                    .setSigningKey(SECRET_KEY)
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}
