package se.iths.java24;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.iths.java24.entity.*;
import se.iths.java24.repository.*;
//import se.iths.java24.repository.StudentService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        while (running) {
            System.out.println("Choose one of the tables:");
            System.out.println("1. Professor");
            System.out.println("2. Student");
            System.out.println("3. ClassYear");
            System.out.println("4. Program");
            System.out.println("5. Course");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> professorMenu(scanner);
                case 2 -> studentMenu(scanner);
                case 3 -> classYearMenu(scanner);
                case 4 -> programMenu(scanner);
                case 5 -> courseMenu(scanner);
                case 0 -> {
                    System.out.println("Exits the program");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }


    public static void studentMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("Student CRUD-meny:");
            System.out.println("1. Lägg till student");
            System.out.println("2. Visa alla studenter");
            System.out.println("3. Uppdatera student");
            System.out.println("4. Ta bort student");
            System.out.println("5. Tillbaka");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumera newline

            switch (choice) {
                case 1:
                    System.out.println("Ange förnamn: ");
                    String forename = scanner.nextLine();
                    System.out.println("Ange efternamn: ");
                    String surname = scanner.nextLine();
                    System.out.println("Ange ålder: ");
                    Integer age = scanner.nextInt();
                    System.out.println("Ange e-post: ");
                    String email = scanner.nextLine();

                    scanner.nextLine();

                    // Lista alla befintliga program (för att välja ett)
                    System.out.println("Tillgängliga ÅrsKurser:");
                    List<ClassYear> classYears = ClassYearService.getAllClassYears(); // En metod för att hämta alla program
                    for (ClassYear classYear : classYears) {
                        System.out.println(classYear.getId() + ": " + classYear.getClassProgramID().getProgramName());
                    }

                    // Låt användaren välja ett program
                    System.out.println("Ange ID för årskursen som eleven ska kopplas till:");
                    Long programId = scanner.nextLong();
                    scanner.nextLine(); // Konsumera ny rad

                    // Hämta programmet från databasen
                    ClassYear selectedClassYear = ClassYearService.getClassYearById(programId); // En metod för att hämta program baserat på ID
                    if (selectedClassYear == null) {
                        System.out.println("Ogiltigt program-ID. Försök igen.");
                    }


                        Student student = new Student(forename, surname, age, email, selectedClassYear);
                        StudentService.addStudent(student);

                        break;

                        case 2:
                            var students = StudentService.getAllStudents();

                            for (Student s : students) {
                                System.out.println("StudentId: " +s.getId());
                                System.out.println(s.getForename() + " " + s.getSurname());
                                System.out.println("Grade: " + s.getGrade());
                                System.out.println("Class: " + s.getStudentClassYear().getClassProgramID().getProgramName());
                                System.out.println("Årskurs: " + s.getStudentClassYear().getClassPeriod());
                                System.out.println("===");
                            }
                            break;


                        case 3:
                            System.out.println("Ange studentens ID att uppdatera:");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ange nytt betyg:");
                            String newGrade = scanner.nextLine();
                            StudentService.updateStudent(updateId, newGrade);
                            break;
                        case 4:
                            System.out.println("Ange studentens ID att ta bort:");
                            int deleteId = scanner.nextInt();
                            StudentService.deleteStudent(deleteId);
                            break;
                        case 5:
                            back = true;
                            break;
                        default:
                            System.out.println("Ogiltigt val.");
                    }
            }
        }

        public static void classYearMenu (Scanner scanner){
            boolean back = false;

            while (!back) {
                System.out.println("ClassYear CRUD-meny:");
                System.out.println("1. Lägg till årskurs");
                System.out.println("2. Visa alla årskurser");
                System.out.println("3. Uppdatera årskurs");
                System.out.println("4. Ta bort årskurs");
                System.out.println("5. Tillbaka");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumera newline

                switch (choice) {
                    case 1:
                        System.out.println("Ange årtal på årskurs:");
                        String classPeriod = scanner.nextLine();

                        // Lista alla befintliga program (för att välja ett)
                        System.out.println("Tillgängliga program:");
                        List<Program> programs = ProgramService.getAllPrograms(); // En metod för att hämta alla program
                        for (Program program : programs) {
                            System.out.println(program.getId() + ": " + program.getProgramName());
                        }

                        // Låt användaren välja ett program
                        System.out.println("Ange ID för programmet som årskursen ska kopplas till:");
                        Long programId = scanner.nextLong();
                        scanner.nextLine(); // Konsumera ny rad

                        // Hämta programmet från databasen
                        Program selectedProgram = ProgramService.getProgramById(programId); // En metod för att hämta program baserat på ID
                        if (selectedProgram == null) {
                            System.out.println("Ogiltigt program-ID. Försök igen.");
                            break;
                        }


                        ClassYear classYear = new ClassYear(classPeriod, selectedProgram);
                        ClassYearService.addClassYear(classYear);
                        break;

                    case 2:
                        ClassYearService.viewAllClassYears();
                        break;

                    case 3:
                        System.out.println("Ange ID på årskurs att uppdatera:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ange uppdaterat årtal:");
                        String newYearName = scanner.nextLine();
                        ClassYearService.updateClassYear(updateId, newYearName);
                        break;

                    case 4:
                        System.out.println("Ange ID på årskurs att ta bort:");
                        int deleteId = scanner.nextInt();
                        ClassYearService.deleteClassYear(deleteId);
                        break;

                    case 5:
                        back = true;
                        break;

                    default:
                        System.out.println("Ogiltigt val.");
                }
            }
        }

        public static void professorMenu (Scanner scanner){
            boolean back = false;

            while (!back) {
                System.out.println("Student CRUD-meny:");
                System.out.println("1. Lägg till professor");
                System.out.println("2. Visa alla professorer");
                System.out.println("3. Uppdatera professor");
                System.out.println("4. Ta bort professor");
                System.out.println("5. Tillbaka");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumera newline

                switch (choice) {
                    case 1:
                        //CREATE
                        System.out.println("Ange förnamn: ");
                        String forename = scanner.nextLine();
                        System.out.println("Ange efternamn: ");
                        String surname = scanner.nextLine();
                        System.out.println("Ange år av erfarenhet: ");
                        int yearsOfExpertise = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ange e-post: ");
                        String email = scanner.nextLine();
                        scanner.nextLine();


                        Professor professor = new Professor(forename, surname, yearsOfExpertise, email);
                        ProfessorService.addProffessor(professor);
                        break;

                    case 2:
                        //READ
                        var professors = ProfessorService.getAllProfessors();

                        for (Professor s : professors) {
                            System.out.println("Professor ID: " + s.getId());
                            System.out.println("Professor First name: " + s.getForename() + " Surname: " + s.getSurname());
                            System.out.println("Professor Email: " + s.getprofessorEmail());

                            if (s.getPrograms() != null && !s.getPrograms().isEmpty()) {
                                for (Program program : s.getPrograms()) {
                                    System.out.println("Professor Program: " + program.getProgramName());  // Hämta ProgramName från Program-objektet
                                }
                            } else {
                                System.out.println("No program assigned");
                            }


                            System.out.println("Professor Years of expertice " + s.getYearsOfExpertise());
                            System.out.println("===");
                        }
                        break;

                    case 3:
                        //UPDATE
                        System.out.println("Ange Professorns ID att uppdatera:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ange ny EpostAdress:");
                        String newEmail = scanner.nextLine();
                        ProfessorService.updateProfessor(updateId, newEmail);
                        break;
                    case 4:
                        //DELETE
                        System.out.println("Ange professor ID att ta bort:");
                        int deleteId = scanner.nextInt();
                        ProfessorService.deleteProfessor(deleteId);
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Ogiltigt val.");
                }
            }


        }

        public static void programMenu (Scanner scanner){
            boolean back = false;

            while (!back) {
                System.out.println("Program CRUD-meny:");
                System.out.println("1. Lägg till Program");
                System.out.println("2. Visa alla program");
                System.out.println("3. Uppdatera program");
                System.out.println("4. Ta bort program");
                System.out.println("5. Tillbaka");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumera newline

                switch (choice) {
                    case 1:
                        System.out.println("Ange namn på Program:");
                        String programName = scanner.nextLine();

                        System.out.println("Ange behörighet:");
                        String requirement = scanner.nextLine();

                        System.out.println("Ange max kapacitet:");
                        int maxCapacity = scanner.nextInt();
                        scanner.nextLine(); // Rensa scanner-bufferten

                        // Visa alla befintliga professorer
                        System.out.println("Befintliga professorer:");
                        List<Professor> professors = ProfessorService.getAllProfessors();
                        for (Professor professor : professors) {
                            System.out.println(professor.getProfessorID() + ": " + professor.getForename() + " " + professor.getSurname());
                        }

                        // Be användaren välja professor
                        System.out.println("Ange ID för professor som ska kopplas till programmet:");
                        int professorID = scanner.nextInt();
                        scanner.nextLine(); // Rensa scanner-bufferten

                        // Kontrollera att professor med valt ID finns
                        Professor chosenProfessor = professors.stream()
                                .filter(professor -> professor.getProfessorID() == professorID)
                                .findFirst()
                                .orElse(null);

                        if (chosenProfessor == null) {
                            System.out.println("Ogiltigt professor-ID. Programmet skapades inte.");
                            break;
                        }

                        // Skapa och spara programmet
                        Program program = new Program(programName, requirement, maxCapacity, chosenProfessor);
                        ProgramService.addProgram(program);
                        break;

                    case 2:
                        ProgramService.viewAllPrograms();
                        break;

                    case 3:
                        System.out.println("Ange ID på program att uppdatera:");
                        int programId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ange uppdaterat maxantal i programmet:");
                        int newMaxCapacity = Integer.parseInt(scanner.nextLine());
                        ProgramService.updateProgram(programId, newMaxCapacity);
                        break;

                    case 4:
                        System.out.println("Ange ID på program att ta bort:");
                        int deleteId = scanner.nextInt();
                        ProgramService.deleteProgram(deleteId);
                        break;

                    case 5:
                        back = true;
                        break;

                    default:
                        System.out.println("Ogiltigt val.");
                }
            }

        }

        public static void courseMenu (Scanner scanner){
            boolean back = false;

            while (!back) {
                System.out.println("Course CRUD-meny:");
                System.out.println("1. Lägg till kurs");
                System.out.println("2. Visa alla kurser");
                System.out.println("3. Uppdatera kurs");
                System.out.println("4. Ta bort kurs");
                System.out.println("5. Tillbaka");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumera newline

                switch (choice) {
                    case 1:
                        //CREATE
                        System.out.println("Ange namn på ny kurs:");
                        String courseName = scanner.nextLine();

                        // Lista alla befintliga program (för att välja ett)
                        System.out.println("Tillgängliga program:");
                        List<Program> programs = ProgramService.getAllPrograms(); // En metod för att hämta alla program
                        for (Program program : programs) {
                            System.out.println(program.getId() + ": " + program.getProgramName());
                        }

                        // Låt användaren välja ett program
                        System.out.println("Ange ID för programmet som kursen ska kopplas till:");
                        Long programId = scanner.nextLong();
                        scanner.nextLine(); // Konsumera ny rad

                        // Hämta programmet från databasen
                        Program selectedProgram = ProgramService.getProgramById(programId); // En metod för att hämta program baserat på ID
                        if (selectedProgram == null) {
                            System.out.println("Ogiltigt program-ID. Försök igen.");
                            break;
                        }

                        Course course = new Course(courseName);
                        course.setProgram(selectedProgram);

                        CourseService.addCourse(course);
                        break;

                    case 2:
                        //READ
                        CourseService.viewAllCourses();
                        break;

                    case 3:
                        //UPDATE
                        System.out.println("Ange ID på kursen som ska uppdateras:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ange uppdaterat kursnamn:");
                        String newCourseName = scanner.nextLine();
                        CourseService.updateCourse(updateId, newCourseName);
                        break;

                    case 4:
                        //DELETE
                        System.out.println("Ange ID på kurs att ta bort:");
                        int deleteId = scanner.nextInt();
                        CourseService.deleteCourse(deleteId);
                        break;

                    case 5:
                        back = true;
                        break;

                    default:
                        System.out.println("Ogiltigt val.");
                }
            }

        }


    }










//package se.iths.java24;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import se.iths.java24.entity.Country;
//
//import java.util.List;
//import java.util.Scanner;
//
//import static se.iths.java24.JPAUtil.getEntityManager;
//import static se.iths.java24.JPAUtil.inTransaction;
//
//public class Main {
//
//    public static void main(String[] args) {
//        EntityManager em = getEntityManager();
//        Cities cities = new Cities();
//        //Ask user for information
//        System.out.println("Add city to country");
//        cities.addCityToCountry("Kalmar", 60000, "Sweden");
//
////       System.out.print("Enter search term: ");
////       Scanner scanner = new Scanner(System.in);
////      String name = scanner.nextLine();
////
////       // Validate user input
////       if (name == null || name.isEmpty()) {
////           System.out.println("Invalid input.");
////           return;
////       }
////
//      //JPQL
//      //String queryStr = "SELECT c FROM Country c WHERE c.countryName =:name";
//      //TypedQuery<Country> query = em.createQuery(queryStr, Country.class);
//       //query.setParameter("name", name);
//       //List<Country> countries = query.getResultList();
//      //countries.forEach(System.out::println);
//
//        //Create new country
//        Country country = new Country();
//        country.setCountryName("Poland");
//        country.setCountryCode("pl");
//
//        var transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(country);
//        transaction.commit();
//        em.close();
//
//        JPAUtil.inTransaction(entityManager -> {
//            entityManager.persist(country);
//        });
//
//        //Create
//        try {
//            inTransaction(entityManager -> {
//                entityManager.persist(country);
//            });
//        } catch (Exception e) {
//
//        }
//
//        //Update
//        inTransaction(entityManager -> {
//            Country poland = entityManager.find(Country.class, "pl");
//            if (poland != null) {
//                poland.setCountryName("Poland (PL)");
//                poland.setCountryName("Test");
//            }
//        });
//
//        //Delete
//        inTransaction(entityManager -> {
//            Country poland = entityManager.find(Country.class, "pl");
//            if (poland != null)
//                entityManager.remove(poland);
//        });
//
//        inTransaction(entityManager -> {
//            var country1 = entityManager.find(Country.class, "se");
//            System.out.println(country1.getThreeLetterName());
//        });
//
//        //Use JOIN FETCH to prevent N + 1 problem
//        inTransaction(entityManager -> {
//            var c = entityManager.createQuery("SELECT c FROM Country c JOIN FETCH c.cities", Country.class)
//                    .getResultList();
//            c.forEach(System.out::println);
//        });
//
//        //Named entity graph to prevent N + 1 problem, defined in Entity class
//        inTransaction(entityManager -> {
//            var eg = entityManager.getEntityGraph("Country.cities");
//
//            var c = entityManager.createQuery("SELECT c FROM Country c", Country.class)
//                    .setHint("jakarta.persistence.fetchgraph", eg)
//                    .getResultList();
//            c.forEach(System.out::println);
//        });
//
//        //Create entity graph using code.
//        inTransaction(entityManager -> {
//            var eg = entityManager.createEntityGraph(Country.class);
//            eg.addAttributeNodes("cities");
//
//            var c = entityManager.createQuery("SELECT c FROM Country c", Country.class)
//                    .setHint("jakarta.persistence.fetchgraph", eg)
//                    .getResultList();
//            c.forEach(System.out::println);
//        });
//
//        //Only retrieve what we need
//        inTransaction(entityManager -> {
//            var c = entityManager.createQuery("SELECT c.countryName FROM Country c", String.class)
//                    .getResultList();
//            c.forEach(System.out::println);
//        });
//
//        //Convert selected Entity to dto object
//        inTransaction(entityManager -> {
//            var c = entityManager.createQuery("SELECT c FROM Country c", Country.class)
//                    .getResultList();
//            c.stream().map(country1 ->
//                            new CountryCodeAndName(country1.getCountryCode(),
//                                    country1.getCountryName()))
//                    .forEach(System.out::println);
//        });
//
//        //Select information into dto directly, Projection
//        inTransaction(entityManager -> {
//            var c = entityManager.createQuery("SELECT new se.iths.java24.CountryCodeAndName(c.countryCode, c.countryName)" +
//                                              " FROM Country c", CountryCodeAndName.class)
//                    .getResultList();
//            c.forEach(System.out::println);
//        });
//
//        //Native query, gives us access to full sql
////        inTransaction(entityManager -> {
////            var c = entityManager.createNativeQuery("delete from country where country_code='tt'")
////                    .executeUpdate();
////        });
//
//    }
//
//
//
//}
