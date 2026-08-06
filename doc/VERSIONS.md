# 🛠️ MapTap Technology Stack

This document maintains the technology stack, versions, and dependencies used across the MapTap backend project.

---

# Runtime

| Technology | Version | Notes |
|------------|---------|-------|
| Java | 21 | LTS |
| Maven | 3.9+ | Build Tool |

---

# Spring Framework

| Technology | Version |
|------------|---------|
| Spring Boot | 3.5.16 |
| Spring Cloud | 2025.0.3 |

---

# Spring Boot Starters

| Dependency | Version | Managed By |
|------------|---------|------------|
| spring-boot-starter-web | Boot Managed | Spring Boot |
| spring-boot-starter-security | Boot Managed | Spring Boot |
| spring-boot-starter-validation | Boot Managed | Spring Boot |
| spring-boot-starter-data-jpa | Boot Managed | Spring Boot |
| spring-boot-starter-actuator | Boot Managed | Spring Boot |
| spring-boot-starter-oauth2-resource-server | Boot Managed | Spring Boot |
| spring-boot-starter-test | Boot Managed | Spring Boot |

---

# Spring Cloud Components

| Dependency | Version |
|------------|---------|
| Eureka Server | Spring Cloud Managed |
| Eureka Client | Spring Cloud Managed |
| Config Server | Spring Cloud Managed |
| Config Client | Spring Cloud Managed |
| API Gateway | Spring Cloud Managed |
| OpenFeign | Spring Cloud Managed |

---

# Database

| Technology | Version |
|------------|---------|
| PostgreSQL | 16+ |
| Hibernate | Boot Managed |
| Spring Data JPA | Boot Managed |

---

# Security

| Technology | Version |
|------------|---------|
| Spring Security | Boot Managed |
| JWT (JJWT) | 0.12.7 |
| BCrypt | Spring Security |

---

# Utilities

| Dependency | Version |
|------------|---------|
| Lombok | Latest Stable |
| MapStruct | 1.6.3 |
| Apache Commons Lang | 3.17.0 |

---

# API Documentation

| Dependency | Version |
|------------|---------|
| springdoc-openapi | 2.8.9 |

---

# Logging

| Dependency | Version |
|------------|---------|
| SLF4J | Boot Managed |
| Logback | Boot Managed |

---

# Build Tool

| Tool | Version |
|------|---------|
| Maven | 3.9+ |

---

# Development Tools

| Tool | Version |
|------|---------|
| IntelliJ IDEA | Latest |
| Postman | Latest |
| pgAdmin | Latest |
| Git | Latest |
| GitHub | Cloud |

---

# Future Technologies (V2)

| Technology | Planned Version |
|------------|-----------------|
| Docker | Latest Stable |
| Docker Compose | Latest Stable |
| Redis | 8.x |
| Apache Kafka | 4.x |

---

# Future Technologies (V3)

| Technology | Planned Version |
|------------|-----------------|
| Prometheus | Latest Stable |
| Grafana | Latest Stable |
| Kubernetes | Latest Stable |
| AWS | Latest |

---

# Version Compatibility

| Component | Version |
|-----------|---------|
| Java | 21 |
| Spring Boot | 3.5.16 |
| Spring Cloud | 2025.0.3 |
| PostgreSQL | 16+ |
| Maven | 3.9+ |

---

# Version Update Policy

- Always use Java LTS versions.
- Upgrade Spring Boot only to stable releases.
- Keep Spring Cloud compatible with the selected Spring Boot version.
- Avoid milestone (M), RC (Release Candidate), or SNAPSHOT dependencies.
- Upgrade third-party libraries only after verifying compatibility.

---

# Notes

This file serves as the single source of truth for all technology versions used in the MapTap backend project.