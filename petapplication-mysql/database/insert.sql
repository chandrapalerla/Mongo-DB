-- Insert data into Owners table
INSERT INTO pet.Owners (first_name, last_name, address, phone_number, email)
VALUES
('John', 'Doe', '123 Maple St', '555-1234', 'johndoe@example.com'),
('Jane', 'Smith', '456 Oak St', '555-5678', 'janesmith@example.com'),
('Alice', 'Johnson', '789 Pine St', '555-8765', 'alicej@example.com'),
('Bob', 'Brown', '101 Elm St', '555-3456', 'bobbrown@example.com');

-- Insert data into Pets table
INSERT INTO pet.Pets (name, species, breed, gender, birthdate, owner_id)
VALUES
('Max', 'Dog', 'Golden Retriever', 'Male', '2020-01-15', 1),
('Bella', 'Cat', 'Siamese', 'Female', '2019-03-22', 2),
('Charlie', 'Dog', 'Labrador', 'Male', '2021-05-30', 3),
('Lucy', 'Cat', 'Maine Coon', 'Female', '2018-07-12', 4),
('Rocky', 'Dog', 'Bulldog', 'Male', '2017-11-02', 1);

-- Insert data into Vets table
INSERT INTO pet.Vets (first_name, last_name, clinic_name, phone_number, email)
VALUES
('Dr. Emma', 'White', 'Happy Paws Clinic', '555-1111', 'emmawhite@happypaws.com'),
('Dr. James', 'Green', 'Paws & Claws', '555-2222', 'jamesgreen@pawsclaws.com'),
('Dr. Olivia', 'Black', 'VetCare', '555-3333', 'oliviablack@vetcare.com'),
('Dr. Liam', 'Gray', 'Pet Health Center', '555-4444', 'liamgray@pethealth.com');

-- Insert data into Visits table
INSERT INTO pet.Visits (visit_date, reason, notes, pet_id, vet_id)
VALUES
('2023-06-01', 'Annual Checkup', 'Healthy and active', 1, 1),
('2023-07-15', 'Vaccination', 'Received rabies vaccine', 2, 2),
('2023-08-20', 'Skin allergy', 'Prescribed allergy meds', 3, 3),
('2023-09-05', 'Dental cleaning', 'Teeth cleaned', 4, 4),
('2023-06-25', 'Ear infection', 'Administered ear drops', 5, 1),
('2023-08-10', 'Weight check', 'Suggested diet change', 1, 2),
('2023-09-12', 'Limping', 'X-ray shows no issues', 3, 4),
('2023-07-22', 'Vaccination', 'Updated vaccines', 4, 3),
('2023-09-01', 'Routine checkup', 'Overall good health', 2, 1),
('2023-10-10', 'Eye infection', 'Prescribed eye drops', 5, 2);

-- Insert data into Medications table
INSERT INTO pet.Medications (name, dosage, instructions)
VALUES
('Allergy Relief', '10mg', 'Take once daily'),
('Rabies Vaccine', '2ml', 'Administered by vet'),
('Ear Drops', '5ml', 'Apply twice daily'),
('Weight Control', 'N/A', 'Adjust feeding schedule'),
('Eye Drops', '5ml', 'Apply three times daily');

-- Insert data into Prescriptions table
INSERT INTO pet.Prescriptions (visit_id, medication_id, start_date, end_date, notes)
VALUES
(3, 1, '2023-08-20', '2023-09-20', 'Continue for one month'),
(2, 2, '2023-07-15', '2023-07-15', 'One-time administration'),
(5, 3, '2023-06-25', '2023-07-05', 'Apply for 10 days'),
(6, 4, '2023-08-10', '2023-09-10', 'Adjust diet and monitor weight'),
(10, 5, '2023-10-10', '2023-10-20', 'Apply as directed');
