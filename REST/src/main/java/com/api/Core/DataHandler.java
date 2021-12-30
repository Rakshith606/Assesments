package com.api.Core;
import com.api.Views.Article;

import java.util.HashMap;

public interface DataHandler {
    public HashMap<Integer,String> getAllArticles();
    public Article getArticleContentWithId(Integer id);
    public boolean addArticle(Article article);
    public boolean updateArticle(Integer id,Article article);
    public boolean removeArticle(Integer id);
}
