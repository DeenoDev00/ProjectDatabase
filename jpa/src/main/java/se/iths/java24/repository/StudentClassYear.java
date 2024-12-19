package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.ClassYear;
import se.iths.java24.entity.Program;
import se.iths.java24.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentClassYear {

    public static void printClassYearDetails(int classYearId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Hämta den aktuella ClassYear-entiteten baserat på classYearId
            ClassYear classYear = em.find(ClassYear.class, classYearId);

            if (classYear == null) {
                System.out.println("ClassYear not found with id: " + classYearId);
                return;
            }

            // Skapa en query som hämtar ClassYear, Program och relaterade studenter
            String queryStr = """
            SELECT cy, p, s
            FROM ClassYear cy
            JOIN cy.classProgramID p
            JOIN cy.students s
            WHERE cy = :classYear
        """;

            // Skapa queryn och sätt parameter
            TypedQuery<Object[]> query = em.createQuery(queryStr, Object[].class);
            query.setParameter("classYear", classYear);  // Sätt parametern som en ClassYear-objekt

            // Hämta resultatet och skriv ut
            List<Object[]> result = query.getResultList();
            for (Object[] row : result) {
                ClassYear cy = (ClassYear) row[0];
                Program program = (Program) row[1];
                Student student = (Student) row[2];

                // Skriv ut information om ClassYear, Program och Student
                System.out.println("ClassYear: " + cy.getClassPeriod());
                System.out.println("Program: " + program.getProgramName());
                System.out.println("Student: " + student.getForename() + " " + student.getSurname());
                System.out.println("Betyg: " + student.getGrade());
                System.out.println("==============================");
            }
        } finally {
            em.close();
        }
    }



    public static Map<String, List<Student>> getAllStudentsGroupedByYearAndProgram() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String queryStr = """
            SELECT s FROM Student s
        """;
            TypedQuery<Student> query = em.createQuery(queryStr, Student.class);

            List<Student> students = query.getResultList();

            // Gruppera elever per årskull och program
            return students.stream()
                    .collect(Collectors.groupingBy(student ->
                            "================================  "+student.getStudentClassYear().getClassPeriod() + " - " +
                                    student.getStudentClassYear().getClassProgramID().getProgramName()+
                                    "  ================================"
                    ));
        } finally {
            em.close();
        }
    }
}


