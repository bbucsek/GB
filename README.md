## endpoints
```
/clients
method: POST 
reuquest body: 
{
 name: string,
 email: string
}

response: 
apiKey: string
```

```
/positions
method: POST 
reuquest body: 
{
 title: string,
 location: string,
 apiKey: string
}

response: 
url: string
```

```
/positions
method: GET 
reuquest parameters: 

title: string,
location: string,
apiKey: string

response: 
{
   title: string,
   location: string,
   url: string
}
```

```
/positions/{id}
method: GET 

response: 
{
   title: string,
   location: string,
   url: string
}
```

## to improve:
-```storing api keys in this way is never a good idea. use encoding like bcrypt for this purpose.```  
-```improve exception handling```  
-```add swagger to the application, to have proper documentatin```  
-```add unit, integration and regression tests```  
-```running on kubernetes to have scaling```  
