package com.davidoladeji.flexisaf.model;

import com.davidoladeji.flexisaf.util.FullnameGenerated;
import com.davidoladeji.flexisaf.util.MatricGenerated;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_sequence_name")
    private long Id;

    @GeneratorType(type = MatricGenerated.class, when = GenerationTime.INSERT)
    @Column(name = "matric_number")
    private String matricNumber;


    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "other_name")
    @JsonProperty("other_name")
    private String otherName;

    @Column(name = "email_adddress")
    @JsonProperty("email_adddress")
    private String emailAddress;

    @GeneratorType(type = FullnameGenerated.class, when = GenerationTime.ALWAYS)
    @Column(name = "full_name")
    @JsonProperty("full_name")
    private String fullName;


    @Column(name = "gender")
    @JsonProperty("gender")
    private String gender;

    @Column(name = "date_birth")
    @JsonProperty("date_birth")
    private Date dateOfBirth;

    @Column(name = "created_by")
    private String created_by;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonProperty("department_id")
    private Department department;


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Student student))
            return false;
        return Objects.equals(this.matricNumber, student.matricNumber) &&
                Objects.equals(this.firstName, student.firstName) &&
                Objects.equals(this.lastName, student.lastName) &&
                Objects.equals(this.gender, student.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.matricNumber, this.firstName, this.lastName, this.gender);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + this.matricNumber + ", firstname='" + this.firstName + '\'' + ", lastname='" + this.lastName + '\'' + '}';
    }



}