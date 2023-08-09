package com.university.lector.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import com.university.department.model.Position;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lector")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lector {

    @Id
    @SequenceGenerator(name = "lector_sequence",
            sequenceName = "lector_sequence",
            initialValue = 1, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lector_sequence")
    private Long id;

    private String fullName;

    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Degree degree;

    private Double salary;

    @OneToMany(mappedBy = "lector",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Position> positions = new ArrayList<>();

    public Lector(Degree degree, Double salary, String fullName) {
        this.degree = degree;
        this.salary = salary;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Lector lector = (Lector) o;
        return getId() != null && Objects.equals(getId(), lector.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
