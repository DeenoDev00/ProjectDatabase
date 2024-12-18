package se.iths.java24.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import se.iths.java24.JPAUtil;
import se.iths.java24.entity.ClassYear;
import se.iths.java24.entity.Professor;
import se.iths.java24.entity.Program;

import java.util.List;

public class ProgramService {

        //CREATE
        public static void addProgram(Program program) {
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    entityManager.persist(program);
                    transaction.commit();
                    System.out.println("Program tillagd!");
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }

        //READ
        public static void viewAllPrograms() {
            EntityManager em = JPAUtil.getEntityManager();

            try {
                var query = em.createQuery("SELECT p FROM Program p", Program.class);
                var programs = query.getResultList();

                if (programs.isEmpty()) {
                    System.out.println("Inga Program hittades.");
                } else {
                    for (Program p : programs) {
                        System.out.println("ProgramID: " + p.getId());
                        System.out.println("Program name: " + p.getProgramName());
                        System.out.println("Behörigheter: " + p.getRequirement());
                        System.out.println("Max Capacity: " + p.getMaxCapacity());
                        System.out.println("Program Professor: " + p.getProgramProfessor().getForename()+ "  " + p.getProgramProfessor().getSurname());
                        //Fixa i persostance.xml
                        System.out.println("====");
                    }
                }
            } finally {
                em.close();
            }
        }


    // UPDATE
    public static void updateProgram(int programId, int newMaxCapacity) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Program program = entityManager.find(Program.class, programId);
                if (program != null) {
                    program.setMaxCapacity(newMaxCapacity); // Korrigerat: Anropar metoden på objektet
                    entityManager.merge(program);
                    System.out.println("Program uppdaterat!");
                } else {
                    System.out.println("Program hittades inte.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // Delete
        public static void deleteProgram(int programId) {
            try (EntityManager entityManager = JPAUtil.getEntityManager()) {
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    Program program = entityManager.find(Program.class, programId);
                    if (program != null) {
                        entityManager.remove(program);
                        System.out.println("Program borttaget!");
                    } else {
                        System.out.println("Program hittades inte.");
                    }
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction.isActive()) transaction.rollback();
                    e.printStackTrace();
                }
            }
        }

    public static List<Program> getAllPrograms() {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            return entityManager.createQuery("SELECT p FROM Program p", Program.class).getResultList();
        }
    }


    public static Program getProgramById(Long id) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            return entityManager.find(Program.class, id);
        }
    }

}

