package se.iths.java24.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Professor", schema = "demo")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfessorID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "Forename", length = 50)
    private String forename;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "Surname", length = 50)
    private String surname;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "ProfessorEmail", length = 50)
    private String professorEmail;

    @Column(name = "YearsOfExpertise")
    private Integer yearsOfExpertise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}