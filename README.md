# Doctors Management System (DMS)

### Table of Content
* [Installation](#Installation)
* [Requirements](#Requirements)
* [How to start the application](#Usage)
* [API Endpoints](#API)
* [Sentences](#Sentences)
* [Assignment description](#Assignment)

# Installation
The application can be started with an IDE (like IntelliJ IDEA) and the locally installed MySQL DBS

# Requirements
* IntelliJ IDEA (or an IDE of your choice)
* MySQL - with a database named "dms"
    * Instructions on how to install MySQL on Mac with brew: [Link](https://flaviocopes.com/mysql-how-to-install/)

# Usage
1. Clone the Project into your IDE/IntelliJ
2. Set up the database credentials for your MySQL service
    1. In your IDE open the Project folder and open the [application.properties](src/main/resources/application.properties):
        1. Replace:
            * ${SQL_USER} with your MySQL username (usually: root)
            * ${SQL_PASSWORD} with your MySQL password
              `
              spring.datasource.username=${SQL_USER} -> your MySQL username
              spring.datasource.password=${SQL_PASSWORD} -> your MySQL password
              `

3. Start the MySQL server in a terminal (if not already running)
    * (`mysql.server start`)
4. Start the Application from the [*DoctorsApiApplication*](src/main/java/com/endava/doctorsapi/DoctorsApiApplication.java)

# API

    Base URL
    http://localhost:8080/api/v1/<resource>/<ID>?<ParamKey>=<ParamVal>

#### FACILITIES URLS

    Returns a JSON list of facility objects
    GET http://localhost:8080/api/v1/facilities

````json
Response body example:
[
    {
        "id": 123,
        "name": "St. Joseph Krankenhaus"
    },
    {
        "id": 124,
        "name": "Urban Krankenhaus"
    }
]
````

--------------------------------------------------------------

    Returns a JSON of an facility object
    GET http://localhost:8080/api/v1/facilities/<id>
 
````json
Response body example:
{
    "id": 123,
    "name": "St. Joseph Krankenhaus"
}
````

--------------------------------------------------------------

    POST http://localhost:8080/api/v1/facilities

````json
Request body example:
{
    "name": "St. Joseph Krankenhaus"
}
````

--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/facilities/<id>

````json
Request body example:
{
   "name": "St. Joseph Krankenhaus"
}
````

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/facilities/<id>
--------------------------------------------------------------

#### ADDRESSES URLS

    Returns a JSON list of address objects
    GET http://localhost:8080/api/v1/addresses

````json
Response body example:
[
    {
        "id": 123143,
        "street": "Alexanderstr",
        "house_number": "13A",
        "zip": 12314,
        "location": "Berlin"
    },
    {
        "id": 123144,
        "street": "Oberbaumbrücke",
        "houseNumber": "1B",
        "zip": 12032,
        "location": "Berlin"
    }
]
````

--------------------------------------------------------------

    Returns a JSON of an address object
    GET http://localhost:8080/api/v1/addresses/<id>

````json
Response body example:
{
    "id": 123143,
    "street": "Alexanderstr",
    "houseNumber": "13A",
    "zip": 12314,
    "location": "Berlin"
}
````

--------------------------------------------------------------

    POST http://localhost:8080/api/v1/addresses

````json
Request body example:
{
    "street": "Kleistr",
    "houseNumber": "15",
    "zip": 12234,
    "location": "Berlin"
}
````

--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/addresses/<id>

````json
Request body example:
{
    "street": "Mehringdamm",
    "houseNumber": "46",
    "zip": 10965,
    "location": "Berlin"
}
````

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/addresses/<id>
--------------------------------------------------------------

#### DOCTORS URLS

    Returns a json list of doctor objs
    GET http://localhost:8080/api/v1/doctors

```json
Response body example:
  [
    {
      "id": 420394,
      "firstName": "fName",
      "lastName": "lName"
    },
  ]
```

--------------------------------------------------------------

    Returns a JSON of a doctor object
    GET http://localhost:8080/api/v1/doctors/<id>

````json
Response body example:
{
  "id": 420394,
  "firstName": "fName",
  "lastName": "lName"
}
````

--------------------------------------------------------------

    POST http://localhost:8080/api/v1/doctors

```json
Request body example:
{
    "firstName": "fName",
    "lastName": "lName"
}
```

--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/doctors/<id>

````json
Request body example:
{
    "firstName": "fName",
    "lastName": "lName"
}
````

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/doctors/<id>
--------------------------------------------------------------

#### APPOINTMENTS URLS

    Returns a JSON list of appointment objects
    GET http://localhost:8080/api/v1/appointments

````json
Response body example:
[
    {
        "id": 1,
        "startTime": <dateTime>,
        "endTime": <dateTime>,
        "patient": { <patient obj> }
        "doctor" { <doctor obj> },
        "location": {
            "completeAddress": "",
            "department": "dep name",
            "facility": "facility name"
        }
    },
    {
        "id": 2,
        "startTime": <dateTime>,
        "endTime": <dateTime>,
        "patient": { <patient obj> }
        "doctor" { <doctor obj> },
        "location": {
            "completeAddress": "",
            "department": "dep name",
            "facility": "facility name"
        }
    }
]
````

--------------------------------------------------------------

    Returns a JSON of a appointment object
    GET http://localhost:8080/api/v1/appointments/<id>

````json
Response body example:
{
    "id": 1,
    "startTime": <dateTime>,
    "endTime": <dateTime>,
    "patient": { <patient obj> }
    "doctor" { <doctor obj> },
    "location": {
        "completeAddress": "",
        "department": "dep name",
        "facility": "facility name"
    }
}
````

--------------------------------------------------------------

    POST http://localhost:8080/api/v1/appointments

````json
Request body example:
{
    "startTime": <dateTime>,
    "endTime": <dateTime>,
    "patient": { <patient obj> }
    "doctor" { <doctor obj> },
    "location": {
        "completeAddress": "",
        "department": "dep name",
        "facility": "facility name"
    }
}
````

--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/appointments/<id>

```json
Request body example:
{
    "startTime": <dateTime>,
    "endTime": <dateTime>,
    "patient": { <patient obj> }
    "doctor" { <doctor obj> },
    "location": {
        "completeAddress": "",
        "department": "dep name",
        "facility": "facility name"
    }
}
```

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/appointments/<id>
--------------------------------------------------------------

#### PATIENTS URLS

    Returns a json list of patient objs
    GET http://localhost:8080/api/v1/patients

```json
Response body example:
[
    {
        "insuranceNumber": 1,
        "firstName": "fName",
        "lastName": "lName",
        "age": 47,
        "phone": "",
        "address": {
            <address obj>
        }
    },
]
```

--------------------------------------------------------------

    Returns a JSON of a patient object
    GET http://localhost:8080/api/v1/patients/<insurance-number>

````json
Response body example:
{
  "insuranceNumber": 1,
  "firstName": "fName",
  "lastName": "lName",
  "age": 47,
  "phone": "",
  "address": {
    <address obj>
  }
}
````

--------------------------------------------------------------

    POST http://localhost:8080/api/v1/patients

```json
Request body example:
    {
        "insuranceNumber": 1,
        "firstName": "fName",
        "lastName": "lName",
        "age": 47,
        "phone": "",
        "address": {
            <address obj>
        }
    }
```
    
--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/patients/<insurance-number>

```json
Request body example:
{
  "firstName": "fName",
  "lastName": "lName",
  "age": 47,
  "phone" "",
  "address": {
    <address obj>
  }
}
```

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/patients/<insurance-number>
--------------------------------------------------------------

# Sentences
    Get all addresses where a specific doctor works.
    Get all addresses from a specific department.
    Get the address from a patient.

    Get all appointments in a facility.
    Get all doctors working in a facility.
    Get all patients be treated in a facility.

    Get all doctors from a department.
    Get all facilities with a specific department.
    
    Get all appointments from a specific doctor.
    Get all appointments from a given timespan.
    Get all appointments from a specific patient.


# Assignment
## Headless - Spring-Application
Create a headless cms via java and spring framework


## Getting started

### Step - 1
- [ ] Create an appointment with your body to figure out, which topics are left to start

### Step - 2
Watch this videos to understand, how is going on with spring and REST
- [ ] Understand the spring Roadmap (https://www.youtube.com/watch?v=cehTm_oSrqA)
- [ ] Understand the spring (https://www.youtube.com/watch?v=9SGDpanrc8U)

### Step - 3
- [ ] Create an appointment with your body to create rough concept
- [ ] Fork this repo and create your own issues (for each Entity, Service, Controller, Repository and REST-API-Endpoints, you should have an issue)

### Step - 4
- [ ] Create your DB via SQL (without JPA / JAVA / Spring)

### Step - 5
- [ ] Create a structure for REST - API - Endpoints (CRUD for Backend)
- [ ] Create your Entities and connect with your DB-Tables (https://www.youtube.com/watch?v=8SGI_XS5OPw)
- [ ] Create your Services
- [ ] Create your Controllers
- [ ] Create your Repositories

### Step - 6
- [ ] Create Postman - Collections to CRUD the Entities directly via REST - API


## BONUS:
- [ ] Use Unit-Test to test your Application (https://youtu.be/Geq60OVyBPg or https://www.youtube.com/watch?v=z6gOPonp2t0)
- [ ] Security for REST - API (https://www.youtube.com/watch?v=TOox3CGarf8)
