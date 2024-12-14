# BioMedicaLibrary

## Overview

BioMedicaLibrary is a Java-based application designed to facilitate user registrations, logins, and article management for a biomedical library. The application supports two types of users: Authors and Learners, each with specific functionalities. The system is built using Object-Oriented Programming (OOP) principles, ensuring modularity, reusability, and maintainability.

## Features

- **User  Registration**: Allows new users to register as Authors or Learners.
- **User  Login**: Enables registered users to log in to their accounts.
- **Admin Login**: Provides administrative access for managing users and articles.
- **Article Management**: Authors can add and manage articles, while Learners can browse and filter articles.

## Technologies Used

- **Java SE**: The programming language used for development.
- **JDBC**: For database connectivity.
- **SQL Server**: The database used to store user and article information.

## OOP Principles

### 1. Encapsulation

Encapsulation is the practice of bundling the data (attributes) and methods (functions) that operate on the data into a single unit, or class. It restricts direct access to some of the object's components, which helps prevent accidental modification of data.

**Example in the Project:**
- The `User Management` class encapsulates user-related data and methods. Attributes like `username` and `password` are private, and access to them is provided through public methods (getters and setters).
- 
'''java
public class UserManagement {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
'''

### 2. Abstraction
Abstraction is the principle of hiding the complex implementation details and exposing only the essential features of the object. This reduces programming complexity and increases efficiency.

**Example in the Project:**

The User ManagementInterface defines the methods for user management without exposing the underlying implementation. Classes like User Management implement this interface, providing specific functionality while hiding the complexity from the user.

public interface UserManagementInterface {
    void register(Scanner scanner) throws SQLException;
    boolean loginAsAuthor(String username, String password);
    boolean loginAsLearner(String username, String password);
}

### 3. Inheritance
Inheritance is a mechanism where a new class inherits properties and behavior (methods) from an existing class. This promotes code reusability and establishes a relationship between classes.

**Example in the Project:**

A base class User  contains common attributes like username and password. Subclasses like Author and Learner inherit from User , allowing them to reuse common functionality while adding specific features.

public class User {
    protected String username;
    protected String password;

    // Common methods for User
}

public class Author extends User {
    // Author-specific methods
}

public class Learner extends User {
    // Learner-specific methods
}

### 4. Polymorphism
Polymorphism allows methods to do different things based on the object it is acting upon, even if they share the same name. This can be achieved through method overriding and method overloading.

**Example in the Project:**

Both Author and Learner classes have a method called login. Polymorphism allows you to call this method on an object of type User , and the correct method will be executed based on the actual object type.

public class User {
    public void login() {
        // Default login behavior
    }
}

public class Author extends User {
    @Override
    public void login() {
        // Author-specific login behavior
    }
}

public class Learner extends User {
    @Override
    public void login() {
        // Learner-specific login behavior
    }
}

### Usage
**1. Clone the Repository:**

'git clone https://github.com/yourusername/BioMedicaLibrary.git
cd BioMedicaLibrary'

**2.Compile the Java Files:**

'javac *.java'

**3.Run the Application:**

'java Main'

**4. Follow the On-Screen Prompts to register, log in, or manage articles. And Enjoy!**
