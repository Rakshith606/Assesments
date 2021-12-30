package com.api.Views;

public class Article {
    private Integer id;
    private String title;
    private String content;
    public Article(){

    }
    public Article(int id, String title, String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTitle() {
        return title;
    }
    public Integer getId() {
        return id;
    }
    public String getContent() {
        return content;
    }

}
