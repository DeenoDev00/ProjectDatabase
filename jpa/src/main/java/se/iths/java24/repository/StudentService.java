package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import se.iths.java24.JPAUtil;
import se.iths.java24.Main;
import se.iths.java24.entity.Student;

import java.util.List;

public class StudentService {

    // Create
    public static void addStudent(Student student) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(student);
                transaction.commit();
                System.out.println("Student tillagd!");
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // Read
        public static List<Student> getAllStudents() {
            try(EntityManager entityManager = JPAUtil.getEntityManager()) {
                return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            }
        }

    // Update
    public static void updateStudent(int studentId, String newGrade) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Student student = entityManager.find(Student.class, studentId);
                if (student != null) {
                    student.setGrade(Grade.valueOf(newGrade));
                    entityManager.merge(student);
                    System.out.println("Student uppdaterad!");
                } else {
                    System.out.println("Student hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

        // Delete
        public static void deleteStudent(int studentId){
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    Student student = entityManager.find(Student.class, studentId);
                    if (student != null) {
                        entityManager.remove(student);
                        System.out.println("Student borttagen!");
                    } else {
                        System.out.println("Student hittades inte.");
                    }
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }
    }
