package com.university.department.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentRequest;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.department.exception.DepartmentHasNoHeadException;
import com.university.department.exception.DepartmentNotFoundException;
import com.university.department.model.Department;
import com.university.department.repository.DepartmentRepository;
import com.university.lector.dto.LectorRequest;
import com.university.lector.dto.LectorResponse;
import com.university.lector.model.Degree;
import com.university.lector.model.Lector;
import com.university.lector.repository.LectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Set<DepartmentStatisticResponse> DEFAULT_STATISTICS = Set.of(
            new DepartmentStatisticResponse(Degree.ASSISTANT, 0L),
            new DepartmentStatisticResponse(Degree.ASSOCIATE_PROFESSOR, 0L),
            new DepartmentStatisticResponse(Degree.PROFESSOR, 0L)
    );

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
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
        Lector lector = new Lector(lectorRequest.degree(), lectorRequest.salary(), lectorRequest.fullName());
        department.addLector(lectorRepository.save(lector), lectorRequest.isHead());
        return new LectorResponse(lector.getId(), lector.getFullName(), true);
    }

    @Override
    public HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(String departmentName) {
        if (!departmentRepository.existsByName(departmentName)) {
            throw new DepartmentNotFoundException(departmentName);
        }
        return lectorRepository.getHeadOfDepartmentByDepartmentName(departmentName)
                .orElseThrow(() -> new DepartmentHasNoHeadException(departmentName));
    }

    @Override
    public Set<DepartmentStatisticResponse> getDepartmentStatistics(String departmentName) {
        if (!departmentRepository.existsByName(departmentName)) {
            throw new DepartmentNotFoundException(departmentName);
        }
        Set<DepartmentStatisticResponse> responseSet = new HashSet<>(lectorRepository.getDepartmentStatistics(departmentName)) {
            @Override
            public String toString() {
                return this.stream()
                        .map(DepartmentStatisticResponse::toString)
                        .collect(Collectors.joining("\n"));
            }
        };
        responseSet.addAll(DEFAULT_STATISTICS);
        return responseSet;
    }

    @Override
    public AverageSalaryResponse getAverageSalary(String departmentName) {
        if (!departmentRepository.existsByName(departmentName)) {
            throw new DepartmentNotFoundException(departmentName);
        }
        return lectorRepository.getAverageSalary(departmentName);
    }

    @Override
    public CountOfEmployeesResponse getCountOfEmployee(String departmentName) {
        if (!departmentRepository.existsByName(departmentName)) {
            throw new DepartmentNotFoundException(departmentName);
        }
        return lectorRepository.getCountOfEmployee(departmentName);
    }

}
