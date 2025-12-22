# Project Management REST API – Assignment

##  Assignment Overview
This project is a **Project Management REST API** developed using **Spring Boot**.  
It enables authenticated users to manage **Projects** and **Tasks** securely using **JWT authentication**.

The application is built following **RESTful standards**, **layered architecture**, and **clean coding practices**, as required by the assignment.

---

##  Features
- User Registration & Login
- JWT-based Authentication
- Create, Read, Update, Delete **Projects**
- Create, Read, Update, Delete **Tasks** under Projects
- Filter tasks by status (PENDING, IN_PROGRESS, COMPLETED)
- Sort tasks by due date or created date
- Global Exception Handling
- DTO-based API communication
- Clean separation of concerns

---

##  Technology Stack
- Java 17
- Spring Boot  4
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven
- Lombok


---

##  Project Structure

com.demo
├── config
├── controller
├── dto 
├── entity 
├── exception
├── repository 
├── service 

---

##  Authentication APIs

### ➤ Register User

### ➤ Login User


##  Project APIs

### ➤ Create Project

### ➤ Get All Projects

### ➤ Get Project By ID

### ➤ Update Project

### ➤ Delete Project


## Task APIs

### ➤ Add Task to Project

### ➤ Get Tasks (Filter & Sort)

### ➤ Update Task

### ➤ Delete Task


## Db setup 
download -> mysql , dbeaver  
create db with the name 'projectdb'
if you want to change configurations change it from the application.properties


---




