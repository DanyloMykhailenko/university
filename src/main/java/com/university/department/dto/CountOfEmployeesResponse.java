package com.university.department.dto;

public record CountOfEmployeesResponse(String departmentName, Long countOfEmployees) {

    @Override
    public String toString() {
        return "The count of employees of department %s is %d".formatted(departmentName, countOfEmployees);
    }
}
