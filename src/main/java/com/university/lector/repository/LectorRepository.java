package com.university.lector.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.department.dto.AverageSalaryResponse;
import com.university.department.dto.CountOfEmployeesResponse;
import com.university.department.dto.DepartmentStatisticResponse;
import com.university.department.dto.HeadOfDepartmentResponse;
import com.university.lector.model.Lector;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    //language=sql
    @Query("""
            SELECT new HeadOfDepartmentResponse(
            :departmentName AS departmentName,
            lector.id AS lectorId,
            lector.fullName AS lectorFullName)
            FROM Lector lector
            JOIN lector.positions positions
            JOIN positions.department department
            WHERE department.name = :departmentName
            AND positions.isHead = TRUE""")
    Optional<HeadOfDepartmentResponse> getHeadOfDepartmentByDepartmentName(@Param("departmentName") String departmentName);

    //language=sql
    @Query("""
            SELECT new DepartmentStatisticResponse(
            lector.degree AS degree,
            count(lector) AS lectorsCount)
            FROM Lector lector
            JOIN lector.positions positions
            JOIN positions.department department
            WHERE department.name = :departmentName
            GROUP BY lector.degree""")
    Set<DepartmentStatisticResponse> getDepartmentStatistics(@Param("departmentName") String departmentName);

    //language=sql
    @Query("""
            SELECT new AverageSalaryResponse(
            :departmentName AS departmentName,
            avg(lector.salary) AS averageSalary)
            FROM Lector lector
            JOIN lector.positions positions
            JOIN positions.department department
            WHERE department.name = :departmentName""")
    AverageSalaryResponse getAverageSalary(@Param("departmentName") String departmentName);

    //language=sql
    @Query("""
            SELECT new CountOfEmployeesResponse(
            :departmentName AS departmentName,
            count(lector) AS countOfEmployees)
            FROM Lector lector
            JOIN lector.positions positions
            JOIN positions.department department
            WHERE department.name = :departmentName""")
    CountOfEmployeesResponse getCountOfEmployee(@Param("departmentName") String departmentName);

    Set<Lector> findByFullNameIgnoringCaseContaining(String template);

}
