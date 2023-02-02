### DATABASE API ENDPOINTS

    Base URL
    http://localhost:8080/api/v1/<resource>/<ID>?<ParamKey>=<ParamVal>

#### Facilities URL

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
    "id": 123,
    "name": "St. Joseph Krankenhaus"
}
````

--------------------------------------------------------------

    PUT http://localhost:8080/api/v1/facilities/<id>

````json
Request body example:
{
   "id": 123,
   "name": "St. Joseph Krankenhaus"
}
````

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/facilities/<id>
--------------------------------------------------------------

#### Address URL

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
    "id": 123145,
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
    "id": 123143,
    "street": "Mehringdamm",
    "houseNumber": "46",
    "zip": 10965,
    "location": "Berlin"
}
````

--------------------------------------------------------------
    DELETE http://localhost:8080/api/v1/addresses/<id>
--------------------------------------------------------------

### Sentences
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


# Headless - Spring-Application
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

