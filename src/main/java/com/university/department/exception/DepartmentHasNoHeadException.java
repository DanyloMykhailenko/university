package com.university.department.exception;

public class DepartmentHasNoHeadException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Department %s has no head.";

    public DepartmentHasNoHeadException(String departmentName) {
        super(MESSAGE_TEMPLATE.formatted(departmentName));
    }

}
