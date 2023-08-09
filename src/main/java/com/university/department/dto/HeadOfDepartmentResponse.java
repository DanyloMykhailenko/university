package com.university.department.dto;

public record HeadOfDepartmentResponse(String departmentName, Long lectorId, String lectorFullName) {

    @Override
    public String toString() {
        return "Head of %s department is %s".formatted(departmentName, lectorFullName);
    }

}
