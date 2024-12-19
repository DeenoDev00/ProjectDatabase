package se.iths.java24.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Professor", schema = "demo")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfessorID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "Forename", length = 50)
    private String forename;

    @Size(max = 50)
    @Column(name = "Surname", length = 50)
    private String surname;

    @Size(max = 50)
    @Column(name = "ProfessorEmail", length = 50)
    private String professorEmail;

    @Column(name = "YearsOfExpertise")
    private Integer yearsOfExpertise;

    @OneToMany(mappedBy = "professor")
    private Set<Program> programs = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public Professor(String forename, String surname, int yearsOfExpertise, String email){
        this.forename = forename;
        this.surname = surname;
        this.yearsOfExpertise = yearsOfExpertise;
        this.professorEmail = email;
    }

    public int getProfessorID(){
        return this.id;
    }

    public void setProfessorID(int id){
        this.id = id;
    }

    public String getprofessorEmail() {
        return professorEmail;
    }


    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public Integer getYearsOfExpertise() {
        return yearsOfExpertise;
    }

    public void setYearsOfExpertise(Integer yearsOfExpertise) {
        this.yearsOfExpertise = yearsOfExpertise;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    // Default-konstruktor krävs av JPA
    public Professor() {}


}