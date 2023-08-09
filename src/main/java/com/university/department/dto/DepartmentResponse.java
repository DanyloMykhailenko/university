package com.university.department.dto;

public record DepartmentResponse(Long id, String name) {

    @Override
    public String toString() {
        return "Department %s was created with id %d".formatted(name, id);
    }

}
