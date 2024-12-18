package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.ClassYear;
import se.iths.java24.entity.Student;

import java.util.List;

public class ClassYearService {

    //CREATE
    public static void addClassYear(ClassYear classYear) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(classYear);
                transaction.commit();
                System.out.println("Class year tillagd!");
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    //READ
    public static void viewAllClassYears() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            var query = em.createQuery("SELECT cy FROM ClassYear cy", ClassYear.class);
            var classYears = query.getResultList();

            if (classYears.isEmpty()) {
                System.out.println("Inga ClassYears hittades.");
            } else {
                for (ClassYear cy : classYears) {
                    System.out.println("ClassYearID: " + cy.getId());
                    System.out.println("ClassPeriod: " + cy.getClassPeriod());
                    System.out.println("Program: " + cy.getClassProgramID().getProgramName());
                    System.out.println("====");
                }
            }
        } finally {
            em.close();
        }
    }


    // UPDATE
    public static void updateClassYear(int classYearId, String newPeriod) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                ClassYear classYear = entityManager.find(ClassYear.class, classYearId);
                if (classYear != null) {
                    classYear.setClassPeriod(newPeriod);
                    entityManager.merge(classYear);
                    System.out.println("Klassår uppdaterat!");
                } else {
                    System.out.println("Klassår hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // Delete
    public static void deleteClassYear(int classYearId) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                ClassYear classYear = entityManager.find(ClassYear.class, classYearId);
                if (classYear != null) {
                    entityManager.remove(classYear);
                    System.out.println("Klassår borttaget!");
                } else {
                    System.out.println("Klassår hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }


}
