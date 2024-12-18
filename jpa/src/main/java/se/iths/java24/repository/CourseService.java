package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.Course;

public class CourseService {

    //CREATE
    public static void addCourse(Course course) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(course);
                transaction.commit();
                System.out.println("Kurs tillagd!");
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    //READ
    public static void viewAllCourses() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            var query = em.createQuery("SELECT c FROM Course c", Course.class);
            var courses = query.getResultList();

            if (courses.isEmpty()) {
                System.out.println("Inga Kurser hittades.");
            } else {
                for (Course c : courses) {
                    System.out.println("ClassYearID: " + c.getId());
                    System.out.println("ClassPeriod: " + c.getCourseName());
                    System.out.println("Program: " + c.getProgram().getProgramName());
                    System.out.println("====");
                }
            }
        } finally {
            em.close();
        }
    }

    //UPDATE
    public static void updateCourse(int courseID, String newName) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Course course1 = entityManager.find(Course.class, courseID);
                if (course1 != null) {
                    course1.setCourseName(newName);
                    entityManager.merge(course1);
                    System.out.println("Kurs uppdaterat!");
                } else {
                    System.out.println("Kurs hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    //DELETE
    public static void deleteCourse(int courseID) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Course course = entityManager.find(Course.class, courseID);
                if (course != null) {
                    entityManager.remove(course);
                    System.out.println("Kurs borttaget!");
                } else {
                    System.out.println("Kurs hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }


    }


}




