CREATE TABLE Professor
(
    ProfessorID      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Forename         VARCHAR(50),
    Surname          VARCHAR(50),
    ProfessorEmail   VARCHAR(50),
    YearsOfExpertise INT
);

CREATE TABLE Program (
                         ProgramID        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         ProgramName      VARCHAR(50),
                         Requirement      VARCHAR(50),
                         MaxCapacity      INT             NOT NULL,
                         ProgramProfessor INT             NOT NULL,
                         FOREIGN KEY (ProgramProfessor) REFERENCES Professor (ProfessorID)
);

CREATE TABLE ClassYear
(
    ClassYearID    INT PRIMARY KEY AUTO_INCREMENT,
    ClassPeriod    VARCHAR(50),
    ClassProgramID INT NOT NULL,
    FOREIGN KEY (ClassProgramID) REFERENCES Program (ProgramID)
);

CREATE TABLE Course (
                        CourseID        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        CourseName      VARCHAR(50),
                        CourseProgramID INT             NOT NULL,
                        FOREIGN KEY (CourseProgramID) REFERENCES Program (ProgramID)
);

CREATE TABLE Student
(
    StudentID        INT PRIMARY KEY AUTO_INCREMENT,
    Forename         VARCHAR(50),
    Surname          VARCHAR(50),
    Grade            VARCHAR(50),
    StudentAge       INT NOT NULL,
    StudentMail      VARCHAR(50),
    StudentClassYear INT NOT NULL,
    FOREIGN KEY (StudentClassYear) REFERENCES ClassYear (ClassYearID)
);

ALTER TABLE Student MODIFY COLUMN Grade ENUM('MVG', 'VG', 'G', 'IG');

-- Lägg in professorer
INSERT INTO Professor (Forename, Surname, ProfessorEmail, YearsOfExpertise)
VALUES ('Anna', 'Lindberg', 'anna.lindberg@university.com', 10),
       ('Erik', 'Svensson', 'erik.svensson@university.com', 15);

-- Lägg in program
INSERT INTO Program (ProgramName, Requirement, MaxCapacity, ProgramProfessor)
VALUES ('UX Designers', 'Design Portfolio', 30, 1),
       ('JavaUtvecklare', 'Basic Programming', 30, 2);

-- Lägg in klassår
INSERT INTO ClassYear (ClassPeriod, ClassProgramID)
VALUES ('2023', 1),
       ('2024', 1),
       ('2023', 2),
       ('2024', 2);

-- Lägg in studenter för UX Designers
-- UX 2023
INSERT INTO Student (Forename, Surname, Grade, StudentAge, StudentMail, StudentClassYear)
VALUES
    ('Lisa', 'Andersson', 'MVG', 20, 'lisa.andersson@school.com', 1),
    ('Oskar', 'Nilsson', 'VG', 21, 'oskar.nilsson@school.com', 1),
    ('Karin', 'Johansson', 'G', 19, 'karin.johansson@school.com', 1),
    ('Eva', 'Karlsson', 'IG', 22, 'eva.karlsson@school.com', 1),
    ('Adam', 'Larsson', 'MVG', 23, 'adam.larsson@school.com', 1),

    -- UX 2024
    ('Emma', 'Berg', 'G', 20, 'emma.berg@school.com', 2),
    ('Lars', 'Holm', 'VG', 21, 'lars.holm@school.com', 2),
    ('Sara', 'Ek', 'MVG', 19, 'sara.ek@school.com', 2),
    ('Jonas', 'Persson', 'G', 22, 'jonas.persson@school.com', 2),
    ('Sophia', 'Eriksson', 'VG', 23, 'sophia.eriksson@school.com', 2);

-- Lägg in studenter för JavaUtvecklare
INSERT INTO Student (Forename, Surname, Grade, StudentAge, StudentMail, StudentClassYear)
VALUES
    -- Java 2023
    ('Daniel', 'Bengtsson', 'MVG', 20, 'daniel.bengtsson@school.com', 3),
    ('Maria', 'Lind', 'G', 21, 'maria.lind@school.com', 3),
    ('Patrik', 'Sandberg', 'VG', 19, 'patrik.sandberg@school.com', 3),
    ('Helena', 'Åkesson', 'IG', 22, 'helena.akesson@school.com', 3),
    ('Viktor', 'Sjögren', 'MVG', 23, 'viktor.sjogren@school.com', 3),

    -- Java 2024
    ('Johan', 'Hedlund', 'G', 20, 'johan.hedlund@school.com', 4),
    ('Matilda', 'Wahlström', 'VG', 21, 'matilda.wahlstrom@school.com', 4),
    ('Fredrik', 'Nyström', 'MVG', 19, 'fredrik.nystrom@school.com', 4),
    ('Camilla', 'Lundberg', 'G', 22, 'camilla.lundberg@school.com', 4),
    ('Oscar', 'Hansson', 'IG', 23, 'oscar.hansson@school.com', 4);

-- Lägg in kurser för UX Designers
INSERT INTO Course (CourseName, CourseProgramID)
VALUES ('Figma', 1),
       ('Photoshop', 1),
       ('Illustrator', 1);

-- Lägg in kurser för JavaUtvecklare
INSERT INTO Course (CourseName, CourseProgramID)
VALUES ('JavaBasics', 2),
       ('Databaser', 2),
       ('Javascript', 2);

-- Kontrollera data

SELECT *
FROM Professor;
SELECT *
FROM Program;
SELECT *
FROM ClassYear;
SELECT *
FROM Student;
SELECT *
FROM Course;