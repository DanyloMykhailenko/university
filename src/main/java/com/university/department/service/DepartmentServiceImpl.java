package com.university.department.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentRequest;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.department.model.Department;
import com.university.department.repository.DepartmentRepository;
import com.university.lector.dto.LectorRequest;
import com.university.lector.dto.LectorResponse;
import com.university.lector.model.Lector;
import com.university.lector.repository.LectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentRepository.save(new Department(departmentRequest.name()));
        return new DepartmentResponse(department.getId(), department.getName());
    }

    @Override
    @Transactional
    public LectorResponse addLectorToDepartment(String departmentName, LectorRequest lectorRequest) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow();
        Lector lector = new Lector(lectorRequest.degree(), lectorRequest.salary(), lectorRequest.fullName());
        department.addLector(lectorRepository.save(lector), lectorRequest.isHead());
        return new LectorResponse(lector.getId(), lector.getFullName(), true);
    }

    @Override
    public HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(String departmentName) {
        return lectorRepository.getHeadOfDepartmentByDepartmentName(departmentName).orElseThrow();
    }

    @Override
    public List<DepartmentStatisticResponse> getDepartmentStatistics(String departmentName) {
        return lectorRepository.getDepartmentStatistics(departmentName);
    }

    @Override
    public AverageSalaryResponse getAverageSalary(String departmentName) {
        return lectorRepository.getAverageSalary(departmentName);
    }

    @Override
    public CountOfEmployeesResponse getCountOfEmployee(String departmentName) {
        return lectorRepository.getCountOfEmployee(departmentName);
    }

}
