# hotel-management-system-jdbc
A simple Hotel Management System built using Java and JDBC to perform CRUD operations and understand database connectivity and SQL query execution.


This project helped me understand:
- Database connectivity in Java
- JDBC driver configuration
- Executing SQL queries
- Using Statement and PreparedStatement


## ⚙️ Technologies Used

- Java
- JDBC
- MySQL
- VS Code 


## ✨ Features

- Add new hotel records
- View records from database
- Update existing records
- Delete records from database
- Database connection using JDBC

---

## 📂 Project Structure


Hotel-Management-System
│
├── HotelManagement.java
├── database.sql
├── mysql-connector-j.jar
└── README.md


---

## 🛠️ Setup Instructions

### 1️⃣ Clone the repository


git clone https://github.com/Tanushri014/hotel-management-system-jdbc.git


### 2️⃣ Setup Database

Create database in MySQL:


CREATE DATABASE hotel_db;


Run the SQL file to create required tables.

### 3️⃣ Add JDBC Driver

Download MySQL JDBC Driver and add it to the classpath.

### 4️⃣ Compile and Run


javac HotelManagement.java
java HotelManagement

## 📚 What I Learned

- How Java connects to a database using JDBC
- Difference between **Statement and PreparedStatement**
- How CRUD operations work in backend systems
- How to manage database queries from Java programs
