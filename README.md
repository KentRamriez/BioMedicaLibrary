# BioMedicaLibrary

## Overview

**BioMedicaLibrary** is a Java-based application designed to facilitate user registration, login, and article management for a biomedical library. The application supports two types of users: **Authors** and **Learners**, each with specific functionalities. The system is built using Object-Oriented Programming (OOP) principles to ensure modularity, reusability, and maintainability.

## Features

- **User Registration**: Allows new users to register as Authors or Learners.
- **User Login**: Enables registered users to log in to their accounts.
- **Admin Login**: Provides administrative access for managing users and articles.
- **Article Management**: Authors can add and manage articles, while Learners can browse and filter articles.

## Technologies Used

- **Java SE**: The programming language used for development.
- **JDBC**: For database connectivity.
- **SQL Server**: The database used to store user and article information.

## Sustainable Development Goals (SDGs) Implemented

- **SDG 3: Good Health and Well-Being**: Aligns with promoting access to biomedical research and health information.
- **SDG 4: Quality Education**: Facilitates access to educational resources and training in the biomedical field.
- **SDG 9: Industry, Innovation, and Infrastructure**: Supports research and innovation in health technologies.
- **SDG 17: Partnerships for the Goals**: Encourages collaboration among stakeholders in the health sector.

## Object-Oriented Programming (OOP) Principles

### 1. Encapsulation
Encapsulation involves bundling data (attributes) and methods (functions) into a single unit or class, restricting direct access to object components to prevent accidental data modification.

**Example:**
```java
public class UserManagement {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

### 2. Abstraction
Abstraction hides complex implementation details and exposes only the essential features, reducing programming complexity and increasing efficiency.

**Example:**
```java
public interface UserManagementInterface {
    void register(Scanner scanner) throws SQLException;
    boolean loginAsAuthor(String username, String password);
    boolean loginAsLearner(String username, String password);
}
```

### 3. Inheritance
Inheritance allows a new class to inherit properties and methods from an existing class, promoting code reusability and relationships between classes.

**Example:**
```java
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
```

### 4. Polymorphism
Polymorphism allows methods to perform differently based on the object they act upon. This is achieved through method overriding and overloading.

**Example:**
```java
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
```

## Usage

### 1. Clone the Repository:
```bash
git clone https://github.com/yourusername/BioMedicaLibrary.git
cd BioMedicaLibrary
```

### 2. Compile the Java Files:
```bash
javac *.java
```

### 3. Run the Application:
```bash
java Main
```

### 4. Follow the On-Screen Prompts:
- Register, log in, or manage articles.
- Enjoy using **BioMedicaLibrary**!

