<img src="https://github.com/dev26git/ProjectVault-Java-Project/blob/main/ProjectVault%20Logo.jpg" width="479" height="160">

# ProjectVault: A Project Repository Portal

## Abstract
In colleges when we want to start a new project, we need some initial guidance. There is a good chance that someone from the same institution might have worked on a similar project. We created ProjectVault to let users list their projects in one portal. It also lets users sign in into the portal and search the database using keywords, from which the user will get the information about similar projects and also the contact details of the students and the mentor who had worked on the respective project. We used MySQL and Java Database Connectivity (JDBC) at the backend of this program.

## Usage
1. Fork the repo.
2. Copy and execute the SQL commands in the SQL_statements.txt file in MYSQL.
3. Change the username, password and url in repos -> DBUtil.java to match details of your database.
4. Run main method of Client -> Driver.java.

## Modules
***Models***: The classes in this package represent each table of our MySQL database namely - author, mentor and project. They contain relevant information like ID for each entity. All instance variables are private access and use getters and setters. Each model overrides the toString method of Object class in order to display the information contained in the object in a structured format.

***Repos***: This package contains the Database connectivity class and the conversion of sql queries into java functions using java.sql.connection object. It has Query classes to define the sql queries as strings for each model with ‘?’ as placeholders. The package also contains Repo classes that convert respective queries into functions.

***Service***: This is an intermediary between Repos and Client. It provides a layer to give access to different functionalities to different types of users.

***Client***: This package contains the Driver class that is a menu driven program for the user to interact with. It also has the Login and Signup utility classes with relevant user-defined exception handling classes.


## Classes
**Author**: Represents the author table of sql database and an author object in java.

**Mentor**: Represents the mentor table of sql database and a mentor object in java.

**Project**: Represents the project table of sql database and a project object in java.

**DBUtil**: Singleton class that can be used to get a java.sql.connection object.

**AuthorQueries**, **MentorQueries**, **ProjectQueries**: These classes contain final strings corresponding to sql queries for various operations.

**AuthorRepo**, **MentorRepo**, **ProjectRepo**: These classes contain methods corresponding to the sql queries defined for each model.

**AllUsersService**: Contains the functionalities that all users have access to. For example - keyword searching the database.

**CurrentUserService**: Contains the functionalities that only current users have access to. For example - Adding, deleting or updating a project.
**Signup**: Utility for users to signup. Enters data into a csv. Login: Utility for users to login. Extracts data from a csv.

**Driver**: Menu-driven java program that acts as the front-end of the application. It makes calls to the relevant methods for each action that the user chooses to perform.


## Future Work
1. Hash password before saving to csv.
2. Hide password while entering.
3. Create a web-based front-end.