//package com.example.demo.util;
//
//import com.example.demo.model.po.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//    private final User user; // 您的User實體
//
//    public CustomUserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // 返回用戶的權限信息，這需要根據您的業務邏輯實現
//        // 可以返回一個代表用戶權限的集合
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        // 根據需要實現帳戶是否過期的邏輯
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // 根據需要實現帳戶是否被鎖定的邏輯
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // 根據需要實現憑證是否過期的邏輯
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // 根據需要實現帳戶是否啟用的邏輯
//        return true;
//    }
//}
