package com.api.Views;
import java.security.Principal;
import java.util.Set;

public class User implements Principal {
    public static class Labels{
        public static final String ADMIN = "Admin";
        public static final String GUEST = "Guest";
    }
    private final String name;

    private final String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
}
