package com.university.department.exception;

public class DepartmentNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Department with name %s not found.";

    public DepartmentNotFoundException(String departmentName) {
        super(MESSAGE_TEMPLATE.formatted(departmentName));
    }

}
