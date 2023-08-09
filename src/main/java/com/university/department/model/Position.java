package com.university.department.model;

import java.util.Objects;

import com.university.lector.model.Lector;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "position")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Position {

    @EmbeddedId
    private PositionId id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @MapsId("departmentId")
    private Department department;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lector_id")
    @MapsId("lectorId")
    private Lector lector;

    @Setter
    private boolean isHead;

    public Position(Department department, Lector lector, boolean isHead) {
        this.department = department;
        this.lector = lector;
        this.id = new PositionId();
        this.isHead = isHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(department, position.department)
                && Objects.equals(lector, position.lector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, lector);
    }

}
