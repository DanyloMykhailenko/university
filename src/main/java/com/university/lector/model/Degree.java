package com.university.lector.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Degree {

    ASSISTANT("assistant"),
    ASSOCIATE_PROFESSOR("associate professor"),
    PROFESSOR("professor");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
