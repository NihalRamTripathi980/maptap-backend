# MapTap Backend

Open-source Spring Boot microservices backend for MapTap.

MapTap is a modern backend project built to demonstrate scalable microservices architecture using Spring Boot, Spring Cloud, PostgreSQL, Kafka, Docker, and JWT authentication.



## Features

- Microservices Architecture
- API Gateway
- Service Discovery (Eureka)
- Centralized Configuration
- JWT Authentication
- Spring Security
- PostgreSQL
- Kafka Event Streaming
- Docker Support
- REST APIs
- Global Exception Handling
- OpenAPI / Swagger Documentation

## Tech Stack

- Java 21
- Spring Boot
- Spring Cloud
- Spring Security
- Spring Data JPA
- PostgreSQL
- Kafka
- Docker
- Maven
- GitHub Actions (planned)


## Architecture

Client
    │
    ▼
API Gateway
    │
 ┌──┴────────────┐
 │               │
Auth Service   User Service
 │               │
 └──────┬────────┘
        │
   PostgreSQL

Future Services:
- Payment Service
- Notification Service
- Analytics Service


## Project Structure

maptap-backend/
├── api-gateway
├── service-registry
├── config-server
├── auth-service
├── user-service
├── common
├── docker
└── docs



## Getting Started

### Clone the repository

git clone https://github.com/NihalRamTripathi980/maptap-backend.git

### Build

mvn clean install

### Start Services

1. Service Registry
2. Config Server
3. API Gateway
4. Auth Service
5. User Service


## Roadmap

- User Service
- Authentication
- JWT
- Refresh Token
- Kafka Integration
- Payment Service
- Notification Service
- Docker Compose
- Kubernetes
- Monitoring


## Contributing

Contributions are welcome.

Feel free to fork the repository, open issues, and submit pull requests.



## License

MIT License

## Author

Nihal Ram Tripathi
