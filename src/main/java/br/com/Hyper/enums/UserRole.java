package br.com.hyper.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    ARTIST("ARTIST"),
    CUSTOMER("CUSTOMER");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
