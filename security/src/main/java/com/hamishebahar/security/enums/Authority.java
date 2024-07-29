package com.hamishebahar.security.enums;


import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.List;

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

    public HashMap<Integer , String> getMap(){
        HashMap<Integer , String> op = new HashMap<>();
        op.put(this.ordinal() , this.name());
        return op;
    }
}
