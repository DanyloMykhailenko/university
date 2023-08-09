package com.university.lector.dto;

import com.university.lector.model.Degree;

public record LectorRequest(String fullName, Degree degree, Double salary, boolean isHead) {}
