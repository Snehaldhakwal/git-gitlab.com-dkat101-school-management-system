# School Management System
This is a REST API that can manage the day to day activities of a school.

## Backend
Java - Spring

## Database
MySQL

## Features
1. Manage attendance for both staff and students {implemented CRUD}.
2. Manage marks {implemented CRUD}.
3. Manage reports {implemented CRUD}.
4. Manage subjects {implemented CRUD}.
5. Manage users {implemented CRUD}.

## Getting started
1. Setup the project either through cloning or zip
2. Configure application.properties
3. Run the application
4. Launch http://localhost:8080/swagger-ui.html to test the endpoints. Swagger 2.9.2 is used.
5. Register a user using jwt-authentication-controller > /register endpoint
6. Generate JWT using jwt-authentication-controller > /authenticate endpoint
7. Copy the returned token in the response
8. Click the Authorize button just above the endpoint categories and paste Bearer TOKEN
9. Start using the endpoints
