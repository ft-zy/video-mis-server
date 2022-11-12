package com.zy.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    private String isUser;
    private String secret;

    private int expiration;

    Long time = System.currentTimeMillis() + 5 * 60 * 1000;
    Date date = new Date(time);

    //生成 Token
    public String generateToken(Map<String, String> map) {

        JWTCreator.Builder builder = JWT.create();
        // 设置自定义参数
        map.forEach(builder::withClaim);
        // 生成 Token
        return builder.withIssuer(isUser)
                // 设置颁发的时间
                .withIssuedAt(new Date())
                // 设置过期时间
                .withExpiresAt(date)
                // 生成密钥
                .sign(Algorithm.HMAC256(secret));
    }

    //验证 Token
    public boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    //解析 Token
    public DecodedJWT jwtDecode(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token签名错误!");
        } catch (AlgorithmMismatchException e) {
            throw new RuntimeException("token算法不匹配!");
        } catch (TokenExpiredException e) {
            throw new RuntimeException("token过期!");
        } catch (Exception e) {
            throw new RuntimeException("token解析失败!");
        }
    }

}
