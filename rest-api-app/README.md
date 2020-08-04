Run the app with

    mvn spring-boot:run

```bash
curl http://localhost:8080/
```

Examples:

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "admin",
    "password": "password"
}' http://localhost:8080/sign-up
```

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "admin",
    "password": "password"
}' http://localhost:8080/login
```

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "firstName": "Bruno",
    "lastName": "Krebs"
}'  http://localhost:8080/
```


```bash
curl -X DELETE http://localhost:8080/1
```

```bash
curl -H "Content-Type: application/json" -X PUT -d '{
    "firstName": "Bruno",
    "lastName": "Simões Krebs"
}'  http://localhost:8080/6
```
