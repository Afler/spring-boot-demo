package group.springbootdemo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER, SELLER;

    @Override
    public String getAuthority() {
        return name();
    }
}
