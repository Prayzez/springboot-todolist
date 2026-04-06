# springbasicfinal

A simple Todo management web application built with Spring Boot.

This project uses Spring Boot, Spring MVC, Thymeleaf, and Spring Data JPA to provide basic CRUD functionality for Todo items. Users can create, view, update, and delete todos through a web interface. The application also includes input validation and automatic timestamp management for created and updated records.

## Features

- Display a list of all Todo items
- View Todo details
- Create new Todo items
- Edit existing Todo items
- Delete Todo items
- Validate form input
- Automatically manage created and updated timestamps
- Sort Todo items by latest update time

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Jakarta Validation
- Gradle
- MySQL

## Project Structure

```text
src/main/java/com/example/springbasicfinal
├── SpringbasicfinalApplication.java
├── controller
│   └── TodoController.java
├── entity
│   └── Todo.java
├── form
│   └── TodoForm.java
└── repository
    └── TodoRepository.java

src/main/resources
├── application.properties
└── templates/todo
    ├── index.html
    ├── create.html
    ├── detail.html
    └── update.html
```

## Main Functions

### Todo List
Displays all registered Todo items in descending order of update time.

### Todo Detail
Shows detailed information for a selected Todo item.

### Todo Create
Allows users to create a new Todo item by entering a title and content.

### Todo Update
Allows users to edit an existing Todo item.

### Todo Delete
Allows users to delete a Todo item from the list.

## Validation

The application validates user input when creating or updating a Todo.

- Title is required
- Title must be 50 characters or less

If the input is invalid, an error message is displayed on the screen.

## Timestamp Management

The `Todo` entity automatically manages timestamps:

- `createdAt` is set when a Todo is first created
- `updatedAt` is updated whenever a Todo is edited

This is implemented using:

- `@PrePersist`
- `@PreUpdate`

## How to Run

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project in Eclipse or another Java IDE.

### 3. Configure the database

This project uses MySQL.

Set your database connection in `application.properties` or use environment variables for safer local development.

Example:

```properties
spring.application.name=springbasicfinal

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/springbasicfinal}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

If you use environment variables, set:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### 4. Run the application

Run the main class:

```text
SpringbasicfinalApplication.java
```

Or use Gradle:

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

### 5. Open in browser

After starting the application, open:

```text
http://localhost:8080/todos
```

## Pages

- `/todos` : Todo list page
- `/todos/create` : Create page
- `/todos/detail/{id}` : Detail page
- `/todos/update/{id}` : Update page
- `/todos/delete/{id}` : Delete action

## Example Workflow

1. Open the Todo list page
2. Click "新規投稿" to create a new Todo
3. Enter a title and content
4. Submit the form
5. View the created Todo in the list
6. Edit or delete the Todo if needed

## Notes

- This project was created as a Spring Boot practice project
- The UI is simple and focused on functionality
- The database configuration may need to be adjusted depending on your local environment
- Do not upload real passwords or other secrets to GitHub

## Future Improvements

Possible improvements for this project:

- Add CSS styling for a better UI
- Add search functionality
- Add pagination
- Add login/authentication
- Add a completed / incomplete status
- Add REST API support
- Improve exception handling

## Author

Created as a Spring Boot practice project.
