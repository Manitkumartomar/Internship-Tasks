SpringRest Application
This is a Spring Boot application that provides RESTful API endpoints for managing students.

Building the Application
To build the application, you need to have Maven and Java installed on your system. Follow these steps:

Clone the repository to your local machine:
git clone https://github.com/your_username/SpringRest.git

Navigate to the project directory:
cd SpringRest

Build the application using Maven:
mvn clean package

Running the Application
Once the application is built, you can run it using the following command:
java -jar target/SpringRest-1.0.jar

The application will start, and you should see log messages indicating that the server has started successfully.

Testing the Application
You can test the application by sending HTTP requests to the provided RESTful API endpoints. Below is the documentation for the available endpoints:

Endpoints

GET /home:
Description: Returns a welcome message.
Example: http://localhost:8080/home

GET /students:
Description: Returns a list of all students.
Example: http://localhost:8080/students

POST /students:
Description: Adds a new student.
Example: http://localhost:8080/students
Request Body: JSON object representing the new student.

PUT /students/{id}:
Description: Updates an existing student by ID.
Example: http://localhost:8080/students/1
Request Body: JSON object representing the updated student.

DELETE /students/{id}:
Description: Deletes a student by ID.
Example: http://localhost:8080/students/1
Sample Requests

GET /home:
curl -X GET http://localhost:8080/home

GET /students:
curl -X GET http://localhost:8080/students

POST /students:
curl -X POST -H "Content-Type: application/json" -d '{"id": 1, "name": "Alice", "city": "New York"}' http://localhost:8080/students

PUT /students/{id}:
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Bob", "city": "Los Angeles"}' http://localhost:8080/students/1

DELETE /students/{id}:
curl -X DELETE http://localhost:8080/students/1
