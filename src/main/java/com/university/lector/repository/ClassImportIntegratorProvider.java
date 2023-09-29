package com.university.lector.repository;

import java.util.List;

import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;

import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;

import io.hypersistence.utils.hibernate.type.util.ClassImportIntegrator;

public class ClassImportIntegratorProvider implements IntegratorProvider {

    @Override
    public List<Integrator> getIntegrators() {
        return List.of(
                new ClassImportIntegrator(
                        List.of(
                                HeadOfDepartmentResponse.class,
                                DepartmentStatisticResponse.class,
                                AverageSalaryResponse.class,
                                CountOfEmployeesResponse.class
                        )
                )
        );
    }
}
