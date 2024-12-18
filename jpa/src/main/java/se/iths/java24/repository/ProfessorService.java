package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.Professor;

import java.util.List;

public class ProfessorService {

        // Create
        public static void addProffessor(Professor professor) {
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    entityManager.persist(professor);
                    transaction.commit();
                    System.out.println("Professor tillagd!");
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }

        // Read
        public static List<Professor> getAllProfessors() {
            try(EntityManager entityManager = JPAUtil.getEntityManager()) {
                return entityManager.createQuery("SELECT s FROM Professor s", Professor.class).getResultList();
            }
        }

        // Update
        public static void updateProfessor(int professorId, String professorEmail) {
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    Professor professor = entityManager.find(Professor.class, professorId);
                    if (professor != null) {
                        professor.setProfessorEmail(professorEmail);
                        entityManager.merge(professor);
                        System.out.println("Professor uppdaterad!");
                    } else {
                        System.out.println("Professor hittades inte.");
                    }
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }

        // Delete
        public static void deleteProfessor(int professorId){
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    Professor professor = entityManager.find(Professor.class, professorId);
                    if (professor != null) {
                        entityManager.remove(professor);
                        System.out.println("Professor borttagen!");
                    } else {
                        System.out.println("Professor hittades inte.");
                    }
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }
    }


