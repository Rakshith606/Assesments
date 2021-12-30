package com.ApiCall.demo;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Test {
    private static<T> void print(T obj){
        System.out.println(obj);
    }
    private static boolean storeUser(File file,JSONObject usrObj){
        boolean success=true;
        try(FileWriter fp=new FileWriter(file,true)){
            fp.write(usrObj.toJSONString());
            fp.flush();
        }
        catch (Exception e){
            e.printStackTrace();
            success=false;
        }
        finally {
            return success;
        }
    }
    private static boolean handleSingleUser(User usr,int id) {
        File dir=new File("Single_user");
        if(!dir.mkdir()) {
            File[] files=dir.listFiles();
            for(File f:files)
                f.delete();
        }
        File file=new File("Single_user/single_user.json");
        SingleUser Su=usr.fetchUser(id);
        JSONObject usrObj = new JSONObject();
        usrObj.putAll(Su.data.getAllDetails());
        return storeUser(file,usrObj);
    }
    private static boolean handlePagination(User usr,int per_page) {
        Pagination p=usr.performPagination(1,per_page);
        int loop=p.total_pages;
        File dir=new File("Pagination");
        if(!dir.mkdir()) {
            File[] files=dir.listFiles();
            for(File f:files)
                f.delete();
        }
        boolean validAll=true;
        for(int i=1;i<=loop;i++){
            File file=new File("Pagination/page_"+i+".json");
            JSONObject pageObj = new JSONObject();
            JSONArray datarr=new JSONArray();
            for(Data u:p.data){
                JSONObject page_data=new JSONObject();
                page_data.putAll(u.getAllDetails());
                datarr.add(page_data);
            }
            pageObj.put("data",datarr);
            validAll|=storeUser(file,pageObj);
            p=usr.performPagination(i+1,per_page);
        }
        return validAll;
    }
    public static void main(String... args) {
        User usr = Feign.builder()
                .decoder(new GsonDecoder())
                .target(User.class, "https://reqres.in/");
        Scanner s=new Scanner(System.in);
        int choice=3;
        do{
            print("1.\t Search Single User");
            print("2.\t Perform Pagination");
            print("3.\t Exit");
            choice=s.nextInt();
            switch (choice){
                case 1:
                    print("Enter User ID");
                    int id=s.nextInt();
                    print(handleSingleUser(usr,id)?"SUCCESS":"FAILED");
                    break;
                case 2:
                    print("Number of records per page");
                    int per_page=s.nextInt();
                    per_page=Math.max(per_page,1);
                    print(handlePagination(usr,per_page)?"SUCCESS":"FAILED");
                    break;
            }
        }while(choice!=3);
    }
}
