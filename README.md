# Youtube prez RethinkDB

https://rethinkdb.com/

## Install:
https://rethinkdb.com/docs/install/

## Admin Console:
http://localhost:8080/#tables

## ReSql:
https://rethinkdb.com/docs/sql-to-reql/java/

## Guide:
https://rethinkdb.com/docs/guide/java/

## Swagger:
http://localhost:8090/swagger-ui


## Test API
Post some data:
```
curl --location --request POST 'http://localhost:8090/chat' \
--header 'Content-Type: application/json' \
--data-raw '{
"message": "abonne toi",
"from": "moi"
}'
```

Get some data:
```
curl --location --request GET 'http://localhost:8090/chat'
```