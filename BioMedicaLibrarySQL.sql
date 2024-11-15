USE BioMedicaLibrary; 

-- Create Users Table
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(50) CHECK (Role IN ('LECTURER', 'LEARNER'))
);

-- Create Authors Table
CREATE TABLE Authors (
    AuthorID INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL
);

-- Create Materials Table
CREATE TABLE Materials (
    MaterialID INT PRIMARY KEY IDENTITY(1,1),
    Title VARCHAR(255) NOT NULL,
    Content TEXT NOT NULL,
    UploadDate DATETIME NOT NULL,
    UserID INT,
    AuthorID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID)
);
