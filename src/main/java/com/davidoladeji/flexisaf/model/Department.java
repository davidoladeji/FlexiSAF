package com.davidoladeji.flexisaf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "departments")
public class Department {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Student> students;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Department student))
            return false;
        return Objects.equals(this.id, student.id) &&
                Objects.equals(this.name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}