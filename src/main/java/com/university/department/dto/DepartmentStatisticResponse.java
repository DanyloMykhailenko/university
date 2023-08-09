package com.university.department.dto;

import java.util.Objects;

import com.university.lector.model.Degree;

public record DepartmentStatisticResponse(Degree degree, Long lectorsCount) {

    @Override
    public String toString() {
        return degree() + "s - " + lectorsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentStatisticResponse that = (DepartmentStatisticResponse) o;
        return degree == that.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree);
    }
}
