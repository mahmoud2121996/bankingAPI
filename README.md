# bankingAPI
this a banking API using spring boot 3, java 17 and H2 database 

the database is seeded with the following customers with a Bank account for each of them.
```
[
  {
    "id": 1,
    "name": "Arisha Barron"
  },
  {
    "id": 2,
    "name": "Branden Gibson"
  },
  {
    "id": 3,
    "name": "Rhonda Church"
  },
  {
    "id": 4,
    "name": "Georgina Hazel"
  }
]
```

## how to run:
- clone this repo


#### run on local machine: 
1- run `mvn clean compile install -DskipTests`<br>
2- run `mvn spring-boot:run`<be>

#### run using docker: 
1- build the docker image by running : `docker build -t bankingapi .`<br>
2- run it using : `docker run -d -p 8080:8080 bankingapi`<be>

#### run the tests:
1- run `mvn test`<br>
