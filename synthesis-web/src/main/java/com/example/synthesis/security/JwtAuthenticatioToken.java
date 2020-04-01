//package com.example.synthesis.security;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
///**
// * 自定义令牌对象
// *
// * @author: bill
// * @create: 2020/03/29 14:06
// */
//public class JwtAuthenticatioToken extends UsernamePasswordAuthenticationToken {
//
//    private static final long serialVersionUID = 1L;
//
//    private String token;
//
//    public JwtAuthenticatioToken(Object principal, Object credentials) {
//        super(principal, credentials);
//    }
//
//    public JwtAuthenticatioToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
//        super(principal, credentials, authorities);
//    }
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}
