package com.university.department.dto;

import com.university.lector.dto.LectorRequest;

public record AddLectorToDepartmentRequest(String departmentName, LectorRequest lector) {}
