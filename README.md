# Non Working Day Service 
Simple and light calendar micro service to check non working days

## Introduction
The application provide an API for checking given date or range for non working days in calendar. 
User can search these days by provide year or in given period of time. 

## Features
* user registration by token sent by email
* authentication with json request
* authorization with a JWT token
* searching and sorting data
* database supplied from file or through the api endpoint
* all data are stored in MySQL 

## Technologies
* Java 1.8
* Spring Boot (2.3.1.RELEASE)
* JWT (0.9.1)
* Spring Security
* Hibernate
* Mysql
* Swagger
* Lombok

## Installation


## Usage
If you want to check non working days, you can display all calendar by  endpoint "/all" with parameter "countryCode". Code for Poland is "PL".
Authorized user can searching these days by provide year using the endpoint  "/year" with parameter "year" and in given period of time using the endpoint  "/between" with parameters "date1" and "date2", for example:

```apib
# GET /between

+ Request params
    date1 - formatted string (YYYY-mm-dd)
    date2 - formatted string (YYYY-mm-dd)

+ Sample request
    /between?date1=2020-08-01&date2=2021-08-01

+ Response 200 (json)
    [
        {
            "date": "2020-08-15",
            "description": "Wniebowziecie_Najswietszej_Maryi_Panny",
            "countryCode": "PL",
            "dayOfWeek": "SATURDAY"
        },
        {
            "date": "2020-11-01",
            "description": "Wszystkich Świętych",
            "countryCode": "PL",
            "dayOfWeek": "SUNDAY"
        }
    ]
```

Admin can adding and deleting items by  endpoints "/add" and "/delete", for example:
```apib
# POST /add

Body:

 {
        "date": "2021-01-01",
        "description": "Nowy_Rok",
        "countryCode": "PL"
 }
```