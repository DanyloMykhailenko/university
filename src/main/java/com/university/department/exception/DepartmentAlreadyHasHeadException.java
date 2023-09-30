package com.university.department.exception;

public class DepartmentAlreadyHasHeadException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Department %s already has head.";

    public DepartmentAlreadyHasHeadException(String departmentName) {
        super(MESSAGE_TEMPLATE.formatted(departmentName));
    }

}
