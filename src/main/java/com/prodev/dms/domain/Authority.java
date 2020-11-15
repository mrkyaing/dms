package com.prodev.dms.domain;


import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {


	private static final long serialVersionUID = -7997506769144563444L;
	private final String authority;

    public Authority(String authority) {

        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
