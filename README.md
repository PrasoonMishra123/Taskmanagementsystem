# Taskmanagementsystem


## Introduction

The Task Management System allows users to register, log in, create, update, and delete tasks. Additionally, users can mark tasks as complete or incomplete and search/filter tasks based on various criteria such as title, description, assigned user, completion status, and due date.

## Technologies Used

This project utilizes a variety of technologies to ensure robust functionality and a seamless user experience:

- **Java**: The backend logic of the application is written in Java, providing scalability and flexibility.
- **Spring Boot**: Spring Boot is used to create the web application, simplifying setup and development.
- **Spring MVC**: The Model-View-Controller (MVC) architecture is implemented using Spring MVC, ensuring separation of concerns and maintainability.
- **HTML/CSS/JavaScript/ajax/Jquery**: Frontend components are built using HTML, CSS, ajax, and jQuery, enabling dynamic and interactive user interfaces.
- **Maven**: Maven is used for project management and dependency resolution, streamlining the development process.
- **Oracle Database**: Oracle Database is used to store user information and task data, ensuring data integrity and reliability.
- **Eclipse IDE**: Eclipse IDE is used for development and debugging, providing a robust development environment.
- **PostMan tool**:for testing.


## Installation

To set up the Task Management System project, follow these steps:

1. **Clone the Repository**: 
   - Clone the project repository to your local machine using Git:
     into the eclipse and directly do changes from eclipse.


use spring initializer to make project and add depndency like spring web,spring boot devtools and oracledriver.
2. **Import the Project into Eclipse**: 
   - Open Eclipse IDE.
   - Select File > Import.
   - Choose Maven > Existing Maven Projects.
   - Browse to the directory where you cloned the project and select the project folder.
   - Click Finish.

3. **Configure Dependencies**: 
   - Ensure that Maven is installed and configured in Eclipse.
   - Add the necessary dependencies to your `pom.xml` file.
   - Add the Oracle database driver dependency to your `pom.xml` file.

4. **Configure Database Connection**: 
   - Ensure that your `application.properties` file is configured properly for your Oracle database connection.

5. **Run the Project**: 
   - Run the project as a Spring Boot application within Eclipse.

## Usage

To use the Task Management System:

1. **Register and Log In**: 
   - Register a new account with a unique username and password.
   - Log in to the system using your registered credentials.

2. **Task Management**: 
   - Create New Tasks:
     - Provide a title, description, and due date for the new task.
   - Update Existing Tasks:
     - Modify the title, description, due date, or assignment to another user for existing tasks, here option taskid is nothing but task name.
     - Mark tasks as complete or incomplete.
   - Delete Tasks:
     - Remove tasks that are no longer needed based on taskname.

3. **Search and Filtering**: 
   - Search for Tasks:
     - Use the search functionality to find tasks based on title, description, or assigned user.
   - Filter Tasks:
     - Filter tasks based on completion status and due date.
