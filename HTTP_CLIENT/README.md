# REST API

This is a HTTP CLIENT built with Open-Feign


## Table of Contents
1. [Directory Structure](#dir)
2. [Supported Operations](#credentials)

## 1. Directory Structure
<a name="dir"></a>
<pre>
    ├───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───HttpClient
    │   │           └───demo
    │   │                   Data.java
    │   │                   Pagination.java
    │   │                   SingleUser.java
    │   │                   Support.java
    │   │                   Test.java
    │   │                   User.java
    │   │
    │   └───resources
    └───test
        └───java
</pre>

<div style="page-break-after: always;"></div>

## 2 Supported Operations

<a name="credentials"></a>

2.1. [Search](#get)

| Action | Description |
|  -------------  | ------------- |
| INPUT | Takes user_id as input. |
| OUTPUT | prints "SUCCESS" or "FAILURE". |
| RESULT  | User credentials fetched and stored in Single_user/single_user.json. |

2.2. [Pagination](#put)

| Action | Description |
|  -------------  | ------------- |
| INPUT | Takes number of records per page as input. |
| OUTPUT | prints "SUCCESS" or "FAILURE". |
| RESULT  | pages fetched and stored in Pagination/page_{page_no}.json. |