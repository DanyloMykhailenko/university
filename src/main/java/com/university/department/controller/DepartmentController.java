package com.university.department.controller;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.lector.dto.LectorResponse;

public interface DepartmentController {

    DepartmentResponse addDepartment(String departmentRequestJson) throws JsonProcessingException;

    LectorResponse addLectorToDepartment(String departmentName, String lectorRequestJson) throws JsonProcessingException;

    HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(String departmentName);

    Set<DepartmentStatisticResponse> getDepartmentStatistics(String departmentName);

    AverageSalaryResponse getAverageSalary(String departmentName);

    CountOfEmployeesResponse getCountOfEmployees(String departmentName);
}
