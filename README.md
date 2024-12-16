# apt-cinema

## Theme: Cinema
## Description
### For our theme for this Advanced Programming Topics project, we decided to go with a somewhat dying breath of entertainment, the cinema! We figured that there would be plenty of choices when it came to designing and thinking of microservices for a cinema, so that's why we decided to pick it! For this project, we mainly focused on the "movie part" of a cinema.

## Entities used
### - Movie
### - Auditorium
### - Visitor
### - Reservation

## Microservices
### - MovieService (Database: MongoDB)
### - AuditoriumService (Database: MySQL)
### - VisitorService (Database: MongoDB)
### - ReservationService (Database: MySQL)

## Other Components
### - Api Gateway
### - Main Workflow
### - Docker Compose

## Deployment Diagram
### ![alt text](images/image-3.png)

## MovieService Endpoints
### Get
#### Getting all movies
#### Endpoint: /api/movie/all
#### ![alt text](images/image-5.png)
#### Getting a specific movie by their code id
#### Endpoint: /api/movie
#### Query parameter: ?codeId=reco
#### ![alt text](images/image-14.png)

### Post
#### Posting a movie
#### Endpoint: /api/movie
#### ![alt text](images/image-6.png)
#### Getting all movies after the post
#### ![alt text](images/image-7.png)

## AuditoriumService Endpoints
### Get
#### Getting all auditoriums
#### Endpoint: /api/auditorium/all
#### ![alt text](images/image-8.png)
#### Getting a specific auditorium by their code id
#### Endpoint: /api/auditorium
#### Query parameter: ?codeId=51
#### ![alt text](images/image-22.png)

### Post
### Posting an auditorium
#### Endpoint: /api/auditorium
#### ![alt text](images/image-9.png)
#### Getting all auditoriums after post
#### ![alt text](images/image-10.png)

### Put
#### Updating an already existing auditorium (codeId in body needs to belong to existing auditorium)
#### Endpoint: /api/auditorium
#### ![alt text](images/image-11.png)

## VisitorService Endpoints
### Get
#### Getting all visitors
#### Endpoint: /api/visitor/all
#### ![alt text](images/image.png)
#### Getting a specific visitor by their code id
#### Endpoint: /api/visitor
#### Query parameter: ?codeId=je04
#### ![alt text](images/image-12.png)

### Post
#### Posting a visitor
#### Endpoint: /api/visitor
#### ![alt text](images/image-1.png)
#### Getting all visitors after the post
#### ![alt text](images/image-2.png)

## ReservationService Endpoints
### Get
#### Getting all reservations
#### Endpoint: /api/reservation
#### ![alt text](images/image-15.png)

### Post
#### Posting a reservation
#### Endpoint: /api/reservation
#### ![alt text](images/image-16.png)
#### Getting all reservations after the post
#### ![alt text](images/image-17.png)

### Delete
#### Deleting a reservation
#### ![alt text](images/image-18.png)

## Test Results
### Movie
### ![alt text](images/image-4.png)

### Auditorium
### ![alt text](images/image-13.png)

### Visitor
### ![alt text](images/image-23.png)

### Reservation
### ![alt text](images/image-19.png)

### Deployment
#### Github
#### ![alt text](images/image-20.png)
#### Main Workflow
#### ![alt text](images/image-21.png)
