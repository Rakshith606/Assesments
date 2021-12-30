package com.HttpClient.demo;
import feign.Param;
import feign.RequestLine;
import feign.Headers;

public interface User{
    @RequestLine("GET /api/users/{id}")
    @Headers("Accept:application/json")
    SingleUser fetchUser(@Param("id") Integer id);
    @RequestLine("GET /api/users?page={page_no}&per_page={per_page}")
    Pagination performPagination(@Param("page_no") Integer page_no ,@Param("per_page") Integer per_page);
}