package com.emergency_link.emergency_link.security.auth;

import com.emergency_link.emergency_link.entity.UserInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {
    private final UserInfo userInfo;

    public CustomUserDetails(UserInfo userInfo) { this.userInfo = userInfo; }

    // 사용자의 권한을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userInfo.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() { return userInfo.getUserPwd(); }

    @Override
    public String getUsername() {return userInfo.getUserId(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
