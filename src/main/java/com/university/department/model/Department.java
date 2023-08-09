package com.university.department.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import com.university.lector.model.Lector;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "department")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    @SequenceGenerator(name = "department_sequence",
            sequenceName = "department_sequence",
            initialValue = 1, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Position> positions = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addLector(Lector lector, boolean isHead) {
        Position position = new Position(this, lector, isHead);
        positions.add(position);
        lector.getPositions().add(position);
    }

    public void removeLector(Lector lector) {
        for (Iterator<Position> iterator = positions.iterator(); iterator.hasNext();) {
            Position position = iterator.next();
            if (position.getDepartment().equals(this)
                    && position.getLector().equals(lector)) {
                iterator.remove();
                position.getLector().getPositions().remove(position);
                position.setDepartment(null);
                position.setLector(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
