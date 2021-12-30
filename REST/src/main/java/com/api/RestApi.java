package com.api;

import com.api.Authentication.AuthenticateUser;
import com.api.Authorization.AuthorizeUser;
import com.api.Core.*;
import com.api.Views.Article;
import com.api.Views.User;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import java.util.HashMap;

public class RestApi extends Application<Configuration> {
    private static HashMap<Integer, Article> hmp=new HashMap<>();
    static {
        hmp.put(1,new Article(1,"World War 1","World War 1 1914-1918"));
        hmp.put(2,new Article(2,"World War 2","World War 2 1939-1945"));
        hmp.put(3,new Article(3,"Korean War","Korean War 1950-1953"));
        hmp.put(4,new Article(4,"Yom Kippur War","1973-1973"));
        hmp.put(5,new Article(5,"Iran–Iraq War","1980-1988"));

    }
    public static void main( String[] args ) throws Exception {
        new RestApi().run(args);
    }
    @Override
    public void run(Configuration config, Environment environment) throws Exception {
        DataHandler data=new ProcessData(hmp);
        EndpointHandler m=new HandleRequest(data);

        environment.jersey().register(new Router(m));
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AuthenticateUser())
                .setAuthorizer(new AuthorizeUser())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
