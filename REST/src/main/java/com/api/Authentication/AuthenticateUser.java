package com.api.Authentication;

import com.api.Views.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.Map;
import java.util.Optional;

class Pair<K,V>{
    K key;
    V val;
    Pair(K k,V v){
        key=k;
        val=v;
    }
    K getKey(){
        return key;
    }
    V getVal(){
        return val;
    }
}
public class AuthenticateUser implements Authenticator<BasicCredentials, User> {
    private final Map<String,Pair<String,String>> VALID_USERS = Map.ofEntries(
            Map.entry("admin",new Pair<>("admin123",User.Labels.ADMIN)),
            Map.entry("guest",new Pair<>("guest123",User.Labels.GUEST))
    );

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        if (VALID_USERS.containsKey(credentials.getUsername()) && VALID_USERS.get(credentials.getUsername()).getKey().equals(credentials.getPassword()))
        {
            return Optional.of(new User(credentials.getUsername(),VALID_USERS.get(credentials.getUsername()).getVal()));
        }
        return Optional.empty();
    }
}