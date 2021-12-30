package com.api.Authorization;

import com.api.Views.User;
import io.dropwizard.auth.Authorizer;

public class AuthorizeUser implements Authorizer<User>{

    @Override
    public boolean authorize(User user, String s) {
        return user.getRole().equals(s);
    }
}
