package com.university.department.service;

import java.util.List;

import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentRequest;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.lector.dto.LectorRequest;
import com.university.lector.dto.LectorResponse;

public interface DepartmentService {

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);

    LectorResponse addLectorToDepartment(String departmentName, LectorRequest readValue);

    HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(String departmentName);

    List<DepartmentStatisticResponse> getDepartmentStatistics(String departmentName);

    AverageSalaryResponse getAverageSalary(String departmentName);

    CountOfEmployeesResponse getCountOfEmployee(String departmentName);

}
