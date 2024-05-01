package com.hamishebahar.security.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    OP_ACCESS_ADMIN,
    OP_ADMIN_EDIT_USER,
    OP_ADMIN_DELETE_USER,
    OP_EDIT_USER,
    OP_ACCESS_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
