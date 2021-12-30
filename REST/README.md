# REST API

This is a REST Api for articles written about war.
The REST Api is built with Dropwizard.

## Table of Contents
1. [Directory Structure](#dir)
2. [Credentials](#credentials)
3. [Usage](#use)

## 1. Directory Structure
<a name="dir"></a>
![](C:\Users\raksh\Desktop\tree.png)

<div style="page-break-after: always;"></div>

## 2 Credentials
<a name="credentials"></a>

### Authentication : <strong>BasicAuth</strong>

| username  | Password |
| -------------|-------------|
| guest  | guest123|
| admin  | admin123|

## 3. Usage
<a name="use"></a>
#### API Methods implemented are
3.1. [GET](#get)

3.2. [PUT](#put)

3.3. [POST](#post)

3.4. [DELETE](#del)

Username and Password is mandatory for making any API call.

### 3.1 GET Method
<a name="get"></a>
`GET` : <https://localhost:9000/>

`GET` : <https://localhost:9000/{id}>

| Role  | Accessible |
|  -------------  | -------------       |
| Guest  | ![](https://img.icons8.com/fluent/32/000000/checked-2.png)|
| Admin  | ![](https://img.icons8.com/fluent/32/000000/checked-2.png)|

Returns
```

    {
        "id1":title of article1
        "id2":title of article2
        "id3":title of article3
        .
        .
        "idN": title of articleN
    }
    
    OR
    
    {
        "id": id of article
        "title": title of article
        "content": content of article
    }
    
Based on aboved mentioned URL's
```

### 3.2 PUT Method
<a name="put"></a>
`PUT` : <https://localhost:9000/{article_id}>

| Role  | Authorized |
|  -------------  | -------------       |
| Guest  | ![](https://img.icons8.com/ios-filled/32/ffffff/cancel.png)|
| Admin  | ![](https://img.icons8.com/fluent/32/000000/checked-2.png)|

### Sample Payload
```
{
    "title":"Kosovo War",
    "content":"KLA vs FR Yugoslavia 1998-1999"
}
```
Returns

```
1. Upon successful update

    {
        "Status" : "SUCCESS"
    }
    
2. Incase of unsuccessful update (possibly due to incorrect fields in json data or empty content or invalid article id )

    {
        "Status" : "FAILURE",
        "Message":"Please validate your data"
    }

```

<div style="page-break-after: always;"></div>

### 3.3 POST Method
<a name="post"></a>
`POST` : <https://localhost:9000/>

| Role  | Authorized |
|  -------------  | -------------       |
| Guest  | ![](https://img.icons8.com/ios-filled/32/ffffff/cancel.png)|
| Admin  | ![](https://img.icons8.com/fluent/32/000000/checked-2.png)|

### Sample Payload
```
{
    "title":"Kosovo War",
    "content":"KLA vs FR Yugoslavia 1998-1999"
}
```
<strong>id is automatically assigned by Api</strong>.


Returns

```
1. Upon successful insertion

    {
        "Status" : "SUCCESS"
    }
    
2. Incase of unsuccessful insertion (possibly due to incorrect fields in json data or empty content)

    {
        "Status" : "FAILURE",
        "Message":"Please validate your data"
    }

```
<div style="page-break-after: always;"></div>

### 3.4 DELETE Method
<a name="del"></a>
`DELETE` : <https://localhost:9000/{article_id}>

| Role  | Authorized |
|  -------------  | -------------       |
| Guest  | ![](https://img.icons8.com/ios-filled/32/ffffff/cancel.png)|
| Admin  | ![](https://img.icons8.com/fluent/32/000000/checked-2.png)|

Returns

```
1. Upon successful deletion

    {
        "Status" : "SUCCESS"
    }
    
2. Incase of unsuccessful deletion or invalid article id

    {
        "Status" : "FAILURE",
        "Message":"Please validate your data"
    }
```
