# 🛒 E-Commerce Web Application

A full-stack e-commerce application built using **Spring Boot** for the backend and **React + Vite** for the frontend.

This project focuses mainly on backend development concepts such as REST API design, layered architecture, DTO usage, image handling, and database integration.

> ⚠️ **Note:** The frontend was adapted from a course project and integrated with my backend APIs. The main focus of this project is backend development.

---

## 📌 Features

### 🛍️ Product Management

- Get all products
- Get product by ID
- Add new product
- Update existing product
- Delete product
- Search products by keyword
- Upload and retrieve product images

### 📦 Order Management

- Place orders
- Retrieve all orders

---

## ⚙️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring Data JPA
- MS SQL Server
- Maven
- Lombok

### Frontend

- React
- Vite
- Axios

---

## 🧠 Backend Architecture

The backend follows a layered architecture:

```
Controller → Service → Repository → Database
```

| Layer          | Responsibility                                    |
| -------------- | ------------------------------------------------- |
| **Controller** | Handles HTTP requests and API endpoints           |
| **Service**    | Contains business logic                           |
| **Repository** | Handles database operations using Spring Data JPA |
| **DTO**        | Used for request/response data transfer           |

---

## 📂 Project Structure

```
ecommerce-springboot-react-project/
│
├── Backend/
│   ├── src/main/java/org/example/ecomercerestapi/
│   │   ├── controller/
│   │   │   ├── OrderController.java
│   │   │   └── ProductController.java
│   │   ├── model/
│   │   │   ├── DTO/
│   │   │   ├── Order.java
│   │   │   ├── OrderItem.java
│   │   │   └── Product.java
│   │   ├── repository/
│   │   │   ├── OrderRepo.java
│   │   │   └── ProductRepo.java
│   │   ├── service/
│   │   │   ├── OrderService.java
│   │   │   └── ProductService.java
│   │   └── EcommerceRestApiApplication.java
│   ├── resources/
│   ├── pom.xml
│   └── mvnw
│
├── Frontend/
│   ├── public/
│   ├── src/
│   ├── package.json
│   ├── package-lock.json
│   ├── vite.config.js
│   └── index.html
│
└── README.md
```

---

## 🔌 API Endpoints

### 🛍️ Product APIs

| Method   | Endpoint                             | Description       |
| -------- | ------------------------------------ | ----------------- |
| `GET`    | `/api/products`                      | Get all products  |
| `GET`    | `/api/product/{id}`                  | Get product by ID |
| `POST`   | `/api/product`                       | Add new product   |
| `PUT`    | `/api/product/{id}`                  | Update product    |
| `DELETE` | `/api/product/{productId}`           | Delete product    |
| `GET`    | `/api/products/search?keyword=value` | Search products   |

### 🖼️ Product Image API

| Method | Endpoint                         | Description       |
| ------ | -------------------------------- | ----------------- |
| `GET`  | `/api/product/{productId}/image` | Get product image |

### 📦 Order APIs

| Method | Endpoint            | Description    |
| ------ | ------------------- | -------------- |
| `POST` | `/api/orders/place` | Place an order |
| `GET`  | `/api/orders`       | Get all orders |

---

## ▶️ Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yousef-hussein222/ecommerce-springboot-react-project.git
cd ecommerce-springboot-react-project
```

### 2️⃣ Backend Setup

```bash
cd Backend
```

Configure your database connection in:

```
src/main/resources/application.properties
```

Then run the backend:

```bash
mvn spring-boot:run
```

### 3️⃣ Frontend Setup

```bash
cd Frontend
npm install
npm run dev
```

---

## 🧠 Backend Highlights

- RESTful API development
- Layered architecture (Controller → Service → Repository)
- DTO-based request/response handling
- Multipart image upload support
- Spring Data JPA integration
- Organized package structure with separation of concerns

---

## 🚀 Future Improvements

- [ ] JWT Authentication & Authorization
- [ ] Shopping cart persistence
- [ ] Payment gateway integration
- [ ] Admin dashboard
- [ ] Product categories & filtering
- [ ] Pagination support
- [ ] Global exception handling

---

## 👨‍💻 Author

**Yousef Hussein**  
GitHub: [@yousef-hussein222](https://github.com/yousef-hussein222)

---

⭐ _This project is part of my backend development learning journey, focusing on building scalable and maintainable backend applications using Spring Boot._
