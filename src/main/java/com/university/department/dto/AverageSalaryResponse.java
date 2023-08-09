package com.university.department.dto;

public record AverageSalaryResponse(String departmentName, Double averageSalary) {

    @Override
    public String toString() {
        return "The average salary of department %s is %.03f"
                .formatted(departmentName, averageSalary != null ? averageSalary : 0);
    }

}
