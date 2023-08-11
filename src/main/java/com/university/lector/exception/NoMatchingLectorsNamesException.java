package com.university.lector.exception;

public class NoMatchingLectorsNamesException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "No lectors with name matching template '%s' was found.";

    public NoMatchingLectorsNamesException(String template) {
        super(MESSAGE_TEMPLATE.formatted(template));
    }

}
