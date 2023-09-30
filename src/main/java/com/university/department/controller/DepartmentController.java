package com.university.department.controller;

import java.util.Set;

import com.university.department.dto.AddLectorToDepartmentRequest;
import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentRequest;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.lector.dto.LectorResponse;

public interface DepartmentController {

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);

    LectorResponse addLectorToDepartment(AddLectorToDepartmentRequest addLectorToDepartmentRequest);

    HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(DepartmentRequest departmentRequest);

    Set<DepartmentStatisticResponse> getDepartmentStatistics(DepartmentRequest departmentRequest);

    AverageSalaryResponse getAverageSalary(DepartmentRequest departmentRequest);

    CountOfEmployeesResponse getCountOfEmployees(DepartmentRequest departmentRequest);
}
