package com.api.Core;
import com.api.Views.Article;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessData implements DataHandler {

    private static HashMap<Integer, Article> articlesWithContent=null;
    private static HashMap<Integer,String> articlesWithId=null;
    private static PriorityQueue<Integer> pq=null;
    ReentrantLock lock;

    public ProcessData(HashMap<Integer,Article> list){
        articlesWithContent=list;
        articlesWithId=new HashMap<>();

        for(Map.Entry<Integer,Article> article:list.entrySet()){
            articlesWithId.put(article.getKey(),article.getValue().getTitle());
        }
        lock=new ReentrantLock();
        pq=new PriorityQueue<Integer>();
        pq.add(list.size()+1);
    }
    @Override
    public HashMap<Integer,String> getAllArticles(){
        return articlesWithId;
    }
    @Override
    public Article getArticleContentWithId(Integer id){
        return articlesWithContent.get(id);
    }
    @Override
    public boolean addArticle(Article article){
        if(!validData(article))
            return false;
        try {
            int id = getNewId();
            article.setId(id);
            articlesWithContent.put(id, article);
            articlesWithId.put(id, article.getTitle());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
    public int getNewId(){
        synchronized (pq) {
            int id = pq.poll();
            pq.offer(id + 1);
            return id;
        }
    }
    public boolean validData(Article article){
        if(article==null ||
                article.getTitle()==null||
                article.getContent()==null||
                article.getTitle().trim().isEmpty()||
                article.getContent().trim().isEmpty()||
                (article.getId()!=null && !articlesWithId.containsKey(article.getId()))) {

            return false;
        }
        return true;
    }
    @Override
    public boolean updateArticle(Integer id,Article article){
        article.setId(id);
        if(!validData(article)||articlesWithId.get(id)==null)
            return false;

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                articlesWithId.put(id,article.getTitle());
                articlesWithContent.put(id,article);
                return true;
            }
            else
                return false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            if(lock.isHeldByCurrentThread())
                lock.unlock();
        }
        return false;
    }
    @Override
    public boolean removeArticle(Integer id){
        if(articlesWithId.get(id)==null)
            return false;
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                articlesWithId.remove(id);
                articlesWithContent.remove(id);
                pq.offer(id);
                return true;
            }
            else
                return false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            if(lock.isHeldByCurrentThread())
                lock.unlock();
        }
        return false;
    }
}
