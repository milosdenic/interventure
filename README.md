# Listing Browser

This is a Spring Boot application for management and browsing of car listings. It supports adding, updating and deleting listing via kafka messages. Browsing listings is via REST APIs.

## Technology Stack

- **Spring Boot:** Backend framework
- **Elasticsearch:** NoSQL database used for storing listings
- **Kafka:** Used for sending listing messages
- **Docker & Docker Compose:** Used for building and running services

## Getting Started

### Build and Run the Application
Prerequisites

Make sure you have the following installed on your machine:

    Java 17
    Docker and Docker-compose

To build & run the application, navigate to the root directory of the projects and execute:

```bash
docker-compose build
docker-compose up
```

Listing Browser application will run on localhost:8081.

### API Endpoints

Service currently supports the following API operation:

    GET /listings - get all listings
    GET /listings/{make} - get all listings of a selected maker
    GET /listings/{make}/{model} - get all listings of selected maker and model
    GET /listings/{make}/{model}/{year} - get all listings of selected maker and model and year of manufacture

All APIs support search filters:
```
price_from
price_to
mileage_from
mileage_to
fuel_type
gear_box
```
Pagination and sorting is also supported. It can be sorted by multiple fields with adding additional &sort=value,asc(/desc)

Example of one request:
```
localhost:8081/listings/Audi/Q7?fuel_type=Gasoline&body_type=SUV&price_from=10000&price_to=60000&size=3&page=1&sort=make,asc&sort=model,desc
```



### API Documentation

Swagger UI

For exploring and testing the API, you can access the Swagger UI by visiting [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) in your browser.


### Kafka Helper
listing-producer is a small helper service, used to send messages to kafka easily. Since this service wasn't part of the taske, it doesn't have any validation, it was created just to help with sending listing messages and it's expected that messages are created in valid format.
Listing Producer application will run on localhost:8082.

Kafka messages are in the format <Long, Json> (key=userId, value=listing)

It supports following APIs.

    POST /listings - create new listing
    PUT /listings/{listingId} - update existing listing
    DELETE /listings/{userId}/{listingId} - delete existing listing for user
    POST /listings/random - create new random listing, with not realistic values, but it makes it easy to generate many listings fast 

Creating new and updating existing listing require body in the request:
```
{
    "userId": 1,
    "make": "BMW",
    "model": "X6",
    "year": 2023,
    "mileage": 15000,
    "power": 350,
    "fuelType": "Gasoline",
    "gearBox": "Automatic",
    "bodyType": "Sedan",
    "price": 12345
}
```

Creating random listing doesn't require body since it will generate kafa message from provided data in the code.

