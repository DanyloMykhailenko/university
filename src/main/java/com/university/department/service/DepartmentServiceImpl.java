package com.university.department.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.department.dto.AddLectorToDepartmentRequest;
import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentRequest;
import com.university.department.dto.DepartmentResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.department.exception.DepartmentAlreadyHasHeadException;
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

    @Transactional
    @Override
    public LectorResponse addLectorToDepartment(AddLectorToDepartmentRequest addLectorToDepartmentRequest) {
        Department department = departmentRepository.findByName(addLectorToDepartmentRequest.departmentName())
                .orElseThrow(() -> new DepartmentNotFoundException(addLectorToDepartmentRequest.departmentName()));
        if (addLectorToDepartmentRequest.lector().isHead()
                && lectorRepository.getHeadOfDepartmentByDepartmentName(addLectorToDepartmentRequest.departmentName()).isPresent()) {
            throw new DepartmentAlreadyHasHeadException(addLectorToDepartmentRequest.departmentName());
        }
        LectorRequest lectorRequest = addLectorToDepartmentRequest.lector();
        Lector lector = new Lector(lectorRequest.degree(), lectorRequest.salary(), lectorRequest.fullName());
        department.addLector(lectorRepository.save(lector), lectorRequest.isHead());
        return new LectorResponse(lector.getId(), lector.getFullName(), true);
    }

    @Transactional(readOnly = true)
    @Override
    public HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsByName(departmentRequest.name())) {
            throw new DepartmentNotFoundException(departmentRequest.name());
        }
        return lectorRepository.getHeadOfDepartmentByDepartmentName(departmentRequest.name())
                .orElseThrow(() -> new DepartmentHasNoHeadException(departmentRequest.name()));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<DepartmentStatisticResponse> getDepartmentStatistics(DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsByName(departmentRequest.name())) {
            throw new DepartmentNotFoundException(departmentRequest.name());
        }
        Set<DepartmentStatisticResponse> responseSet = new HashSet<>(lectorRepository.getDepartmentStatistics(departmentRequest.name())) {
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

    @Transactional(readOnly = true)
    @Override
    public AverageSalaryResponse getAverageSalary(DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsByName(departmentRequest.name())) {
            throw new DepartmentNotFoundException(departmentRequest.name());
        }
        return lectorRepository.getAverageSalary(departmentRequest.name());
    }

    @Transactional(readOnly = true)
    @Override
    public CountOfEmployeesResponse getCountOfEmployee(DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsByName(departmentRequest.name())) {
            throw new DepartmentNotFoundException(departmentRequest.name());
        }
        return lectorRepository.getCountOfEmployee(departmentRequest.name());
    }

}
