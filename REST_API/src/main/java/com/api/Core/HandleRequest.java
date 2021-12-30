package com.api.Core;
import com.api.Views.Article;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class HandleRequest implements EndpointHandler {
    DataHandler data;
    private static enum Operation{
        SUCCESS,
        FAILURE
    }
    public HandleRequest(DataHandler data){
        this.data=data;
    }
    @Override
    public HashMap<Integer,String> GET() {
        return data.getAllArticles();
    }

    @Override
    public Article GET(int id) {
        return data.getArticleContentWithId(id);
    }

    @Override
    public Response PUT(int id, Article article) {
        boolean result=data.updateArticle(id,article);
        JSONObject response = new JSONObject();
        if(result){
            response.put("Status",Operation.SUCCESS);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        }
        else {
            response.put("Status",Operation.FAILURE);
            response.put("Message","Please validate your data");
            return Response.status(Response.Status.BAD_REQUEST).entity(response.toString()).build();
        }
    }

    @Override
    public Response POST(Article article) {
        boolean result=data.addArticle(article);
        JSONObject response = new JSONObject();
        if(result){
            response.put("Status",Operation.SUCCESS);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        }
        else {
            response.put("Status",Operation.FAILURE);
            response.put("Message","Please validate your data");
            return Response.status(Response.Status.BAD_REQUEST).entity(response.toString()).build();
        }
    }

    @Override
    public Response DELETE(int id) {
        boolean result=data.removeArticle(id);
        JSONObject response = new JSONObject();
        if(result){
            response.put("Status",Operation.SUCCESS);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        }
        else {
            response.put("Status",Operation.FAILURE);
            response.put("Message","Article with given ID does not exist");
            return Response.status(Response.Status.BAD_REQUEST).entity(response.toString()).build();
        }
    }
}
