package com.api.Core;

import com.api.Views.Article;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public interface EndpointHandler {
    public HashMap<Integer,String> GET();
    public Article GET(int id);
    public Response PUT(int id, Article article);
    public Response POST(Article article);
    public Response DELETE(int id);
}
