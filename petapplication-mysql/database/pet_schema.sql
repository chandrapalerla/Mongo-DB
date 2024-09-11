CREATE TABLE pet.Owners (
    owner_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(255),
    phone_number VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE pet.Pets (
    pet_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    species VARCHAR(50),
    breed VARCHAR(100),
    gender ENUM('Male', 'Female'),
    birthdate DATE,
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES pet.Owners(owner_id)
);

CREATE TABLE pet.Vets (
    vet_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    clinic_name VARCHAR(255),
    phone_number VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE pet.Visits (
    visit_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_date DATE,
    reason TEXT,
    notes TEXT,
    pet_id INT,
    vet_id INT,
    FOREIGN KEY (pet_id) REFERENCES pet.Pets(pet_id),
    FOREIGN KEY (vet_id) REFERENCES pet.Vets(vet_id)
);

CREATE TABLE pet.Medications (
    medication_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dosage VARCHAR(50),
    instructions TEXT
);

CREATE TABLE pet.Prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_id INT,
    medication_id INT,
    start_date DATE,
    end_date DATE,
    notes TEXT,
    FOREIGN KEY (visit_id) REFERENCES pet.Visits(visit_id),
    FOREIGN KEY (medication_id) REFERENCES pet.Medications(medication_id)
);
