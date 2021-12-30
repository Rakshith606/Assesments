package com.api.Core;

import com.api.Views.Article;
import com.api.Views.User;
import io.dropwizard.auth.Auth;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class Router {
    EndpointHandler apiEndpoints;
    public Router(EndpointHandler m){
        this.apiEndpoints =m;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Integer,String> listArticles(@Auth User user) {
        return apiEndpoints.GET();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleWithId(@PathParam("id") Integer id,@Auth User user) {
        return apiEndpoints.GET(id);
    }

    @POST
    @RolesAllowed({ User.Labels.ADMIN })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishArticle(Article article,@Auth User user) {
        return apiEndpoints.POST(article);
    }

    @PUT
    @RolesAllowed({User.Labels.ADMIN  })
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editArticle(@PathParam("id") Integer id,Article article,@Auth User user) {
        return apiEndpoints.PUT(id,article);
    }

    @DELETE
    @RolesAllowed({User.Labels.ADMIN })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") Integer id, @Auth User user) {
        return apiEndpoints.DELETE(id);
    }
}
