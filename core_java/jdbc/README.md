# Java Database Connectivity (JDBC)
- [Introduction](#introduction)
- [Implementation](#implementation)
    - [ER Diagram](#er-diagram)
    - [Design Patterns](#design-patterns)
      - [DAO Pattern](#dao-pattern)
      - [Repository Pattern](#repository-pattern)
- [Test](#test)

# Introduction

The application creates connectivity between RDBMS (PostgreSQL) and a Java executable file using JDBC
(Java Database Connectivity API).
The predefined classes and interface implemented in Java 8 allow users to have virtual access
to any data source and leverage CRUD commands
(CREATE, READ, UPDATE, DELETE) on existing databases.<br />
The app was built with PostgreSQL Docker image,
Java 8, JDBC API, Maven project management system and SQL.

# Implementation

## ER Diagram

![](assets/customer_order.png)

## Design Patterns

Design patterns are well-known solutions for solving common object-oriented design problems.
Patterns will make the code manageable and reusable so the project navigation will become smooth and stable.
Patterns provide clear system architecture and previous software engineering experiences. 
In this application, the DAO pattern was used.

### DAO Pattern

The Data Access Object (DAO) pattern is a structural pattern that allows users 
to keep low-level data accessing API or operations from a high business level (closer to storage systems).
It is an abstraction of data persistence. The goal of this concept
is to create classes and interfaces that will perform CRUD operations on an object in the project,
therefore allowing to hide massive and complex queries from users. 

### Repository Pattern

Just like DAO, the Repository pattern also deals with data and hides extensive and heavy queries.
It separates the application working layers at a higher level (closer to business logic).
The repository is an abstraction of a collection of objects and can be implemented using DAO (but not the opposite).
This pattern can fetch or prepare data from the database and send it to a database using a DAO for persistence. 
This pattern is more useful, when you have a large amounts of classes with massive queries.

# Test

The application was manually tested to verify the proper command execution and related database changes.<br />

The CLI command `psql -h localhost -U postgres` was used to confirm that the database was up and running.<br />
Then the database was populated with the sample data using SQL scripts. 
Java `JDBCExecutor` class provides the connection with PostgresSQL. DBeaver was used to visually verify the CRUD operations, called from JDBC.
