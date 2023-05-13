package com.gcu.baima.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
    //    7天有效期
    private static final long tokenExpiration = 24 * 60 * 60 * 1000 * 7;
    private static final String tokenSignKey = "ukc8B34D5mimgUDaY6pZFfWus2jZWLPHO";

    //生成token字符串
    public static String createToken(String userId, String nickname) {
        String token = Jwts.builder()

                .setSubject("guli-user")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("nickName", nickname)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static String getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userId");
    }

    //从token字符串获取userType
    public static String getNickname(String token) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) (claims.get("nickName"));
    }

    //判断token是否有效
    public static boolean isExpiration(String token) {
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        } catch (Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }

    /**
     * 获取过期时间
     *
     * @return
     */
    public static String getData(String token) {
        String isExpire = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().toString();
        return isExpire;
    }


    /**
     * 刷新Token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = JwtHelper.createToken(getUserId(token), getNickname(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static void main(String[] args) {
        JwtHelper.isExpiration("");
        String token = "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSSi_NydQtLU4tUtJRSq0oULIyNLMwMTAztTQ01lECiXumAFUZmpmaGloYWpqbWhgamRiYG1maA9XnZSZn-yXmpgIVJGWW1inVAgCwWM9IVQAAAA.c3g1jErfIpItWLOpAtF6y7DVH9KpvtE9-bCyvTXYNa6mIi_8KXFhrg-Ai4L1LjGdi-wn6cfONAmY0bynrJpg6g";
        System.out.println(JwtHelper.getUserId(token));

    }
}
