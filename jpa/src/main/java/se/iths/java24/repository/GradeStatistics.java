package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.Student;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import se.iths.java24.entity.Student;

import java.util.List;

public class GradeStatistics {


    public static void showGradeDistribution() {
        // Hämta alla studenter från databasen
        List<Student> students = JPAUtil.getEntityManager().createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();

        long igCount = students.stream().filter(student -> student.getGrade() == Grade.IG).count();
        long gCount = students.stream().filter(student -> student.getGrade() == Grade.G).count();
        long vgCount = students.stream().filter(student -> student.getGrade() == Grade.VG).count();
        long mvgCount = students.stream().filter(student -> student.getGrade() == Grade.MVG).count();

        System.out.println("Statistik över betyg:");
        System.out.println("IG : " + igCount);
        System.out.println("G : " + gCount);
        System.out.println("VG : " + vgCount);
        System.out.println("MVG : " + mvgCount);
    }

    public static void showStudentsWithGrade(Grade grade) {
        // Hämta alla studenter med det specifika betyget från databasen
        List<Student> students = JPAUtil.getEntityManager().createQuery("SELECT s FROM Student s WHERE s.grade = :grade", Student.class)
                .setParameter("grade", grade)
                .getResultList();

        long count = students.size(); // Räknar antalet studenter

        System.out.println("Elever med betyget " + grade + ":");
        students.forEach(student -> System.out.println(student.getForename()+" "+student.getSurname()+"Grade: "+student.getGrade()));

        System.out.println("Antal elever med betyget " + grade + ": " + count);
    }
}
