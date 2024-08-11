drop database todoappdb;
create database todoappdb;
use todoappdb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    created_date Date,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status char(15) DEFAULT 'PENDING',
    created_date Date,
    updated_date Date,
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);