USE BioMedicaLibrary;

CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,  
    username NVARCHAR(50) NOT NULL UNIQUE, 
    password NVARCHAR(255) NOT NULL,  
    role NVARCHAR(10) CHECK (role IN ('Admin', 'Author', 'Learner')) NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);

INSERT INTO users (username, password, role) 
VALUES ('admin', 'hashed_admin_password', 'Admin');

CREATE TABLE articles (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(100) NOT NULL,
    abstract NVARCHAR(MAX),
    keywords NVARCHAR(255),
    published_date DATE,
    created_at DATETIME DEFAULT GETDATE()
);
