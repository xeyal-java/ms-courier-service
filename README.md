# Courier Service (ms-courier-service)

This microservice is a core component of the **Delivery Management System**. It manages courier profiles, tracks their real-time availability states (`FREE`, `BUSY`), and processes asynchronous order events via RabbitMQ.

## Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.x (Spring Data JPA, Spring Web)
- **Database:** PostgreSQL
- **Migration Tool:** Liquibase
- **Message Broker:** RabbitMQ
- **Utilities:** Lombok, MapStruct

## System Architecture Boundary
- Manages courier-specific states.
- Reacts asynchronously to order cycle changes.
- *Does NOT handle payment distributions or financial bookkeeping.*

## API Endpoints

### 1. Create a New Courier
- **HTTP Method:** `POST`
- **Path:** `/api/v1/couriers`
- **Request Body:**
```json
{
  "name": "Khayal Gasimov",
  "phone": "+994554154745"
}
