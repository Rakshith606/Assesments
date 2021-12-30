package com.ApiCall.demo;
import java.lang.reflect.Field;
import java.util.*;

public class Data {
    String id;
    String email;
    String first_name;
    String last_name;
    String avatar;
    public HashMap<String,String> getAllDetails(){
        HashMap<String,String> hash=new HashMap<>();
        hash.put("id",this.id);
        hash.put("email",this.email);
        hash.put("first_name",this.first_name);
        hash.put("last_name",this.last_name);
        hash.put("avatar",this.avatar);
        return hash;
    }
}
