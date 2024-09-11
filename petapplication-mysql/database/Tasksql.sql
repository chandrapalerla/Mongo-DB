CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    creation_date DATE NOT NULL,
    deadline DATE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE subtasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    task_id INT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (task_id) REFERENCES tasks(id)
);