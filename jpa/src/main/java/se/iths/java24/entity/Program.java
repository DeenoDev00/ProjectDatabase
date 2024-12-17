    package se.iths.java24.entity;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "Program", schema = "demo")
    public class Program {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ProgramID", nullable = false)
        private Integer id;

        @jakarta.validation.constraints.Size(max = 50)
        @Column(name = "ProgramName", length = 50)
        private String programName;

        @jakarta.validation.constraints.Size(max = 50)
        @Column(name = "Requirement", length = 50)
        private String requirement;

        @jakarta.validation.constraints.NotNull
        @Column(name = "MaxCapacity", nullable = false)
        private Integer maxCapacity;

        @jakarta.validation.constraints.NotNull
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "ProgramProfessor", nullable = false)
        private Professor programProfessor;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getRequirement() {
            return requirement;
        }

        public void setRequirement(String requirement) {
            this.requirement = requirement;
        }

        public Integer getMaxCapacity() {
            return maxCapacity;
        }

        public void setMaxCapacity(Integer maxCapacity) {
            this.maxCapacity = maxCapacity;
        }

        public Professor getProgramProfessor() {
            return programProfessor;
        }

        public void setProgramProfessor(Professor programProfessor) {
            this.programProfessor = programProfessor;
        }

    }