# Bank Management System

A console-based Bank Management System developed using Java and PostgreSQL. The application allows users to manage bank accounts and perform secure transactions.

## Overview
This project is a Java-based Bank Management System that simulates real-world banking operations using PostgreSQL and JDBC. It supports secure money transfer with transaction handling.

## How It Works
The system connects to PostgreSQL using JDBC. During transfer, it deducts money from one account and adds to another using transactions (commit/rollback) to ensure consistency.

## Features
- View account details
- Transfer money between accounts
- Transaction management using commit & rollback
- Database integration using JDBC

## Tech Stack
- Java
- PostgreSQL
- JDBC

## How to Run
1. Clone the repository
2. Import project in IDE (VS Code / IntelliJ)
3. Setup PostgreSQL database using schema.sql
4. Update DB credentials in code
5. Run Main.java

## Project Highlights
- Implemented real-time transaction handling
- Ensured data consistency using database transactions
- Designed modular and maintainable code
