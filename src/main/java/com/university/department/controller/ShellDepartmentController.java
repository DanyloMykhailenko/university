package com.university.department.controller;

import static com.university.lector.controller.ShellLectorController.LECTORS_MANIPULATIONS_GROUP;

import java.util.Set;

import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.context.InteractionMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.university.department.service.DepartmentService;
import com.university.lector.dto.LectorRequest;
import com.university.lector.dto.LectorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Command(interactionMode = InteractionMode.INTERACTIVE)
@Component
@RequiredArgsConstructor
@Slf4j
public class ShellDepartmentController implements DepartmentController {

    private static final String DEPARTMENTS_MANIPULATIONS_GROUP = "Departments manipulations";

    private final DepartmentService departmentService;
    private final ObjectMapper mapper;

    @Command(command = "add department",
            group = DEPARTMENTS_MANIPULATIONS_GROUP,
            description = """
                   Add new department.
                   Example: add department '{"name":"Biology"}'
                   """)
    public DepartmentResponse addDepartment(String departmentRequestJson) throws JsonProcessingException {
        DepartmentRequest departmentRequest = mapper.readValue(departmentRequestJson, DepartmentRequest.class);
        return addDepartment(departmentRequest);
    }

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        return departmentService.addDepartment(departmentRequest);
    }

    @Command(command = "add lector to",
            group = LECTORS_MANIPULATIONS_GROUP,
            description = """
                   Add new lector to specified department.
                   Example: add lector to Biology '{"fullName":"James Garrison","degree":"ASSISTANT","salary":12.2,"isHead": false}'
                   """)
    public LectorResponse addLectorToDepartment(String departmentName, String lectorRequestJson) throws JsonProcessingException {
        LectorRequest lectorRequest = mapper.readValue(lectorRequestJson, LectorRequest.class);
        return addLectorToDepartment(new AddLectorToDepartmentRequest(departmentName, lectorRequest));
    }

    @Override
    public LectorResponse addLectorToDepartment(AddLectorToDepartmentRequest addLectorToDepartmentRequest) {
        return departmentService.addLectorToDepartment(addLectorToDepartmentRequest);
    }

    @Command(command = "show head of",
            group = DEPARTMENTS_MANIPULATIONS_GROUP,
            description = """
                   Show full name of head of specified department.
                   Example: show head of Biology
                   """)
    public HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(String departmentName) {
        return getHeadOfDepartmentByDepartmentName(new DepartmentRequest(departmentName));
    }

    @Override
    public HeadOfDepartmentResponse getHeadOfDepartmentByDepartmentName(DepartmentRequest departmentRequest) {
        return departmentService.getHeadOfDepartmentByDepartmentName(departmentRequest);
    }

    @Command(command = "show statistics of",
            group = DEPARTMENTS_MANIPULATIONS_GROUP,
            description = """
                   Returns count of lectors of each degree in the specified department.
                   Example: show statistics of Biology
                   """)
    public Set<DepartmentStatisticResponse> getDepartmentStatistics(String departmentName) {
        return getDepartmentStatistics(new DepartmentRequest(departmentName));
    }

    @Override
    public Set<DepartmentStatisticResponse> getDepartmentStatistics(DepartmentRequest departmentRequest) {
        return departmentService.getDepartmentStatistics(departmentRequest);
    }

    @Command(command = "show the average salary for",
            group = DEPARTMENTS_MANIPULATIONS_GROUP,
            description = """
                   Show the average salary for the specified department.
                   Example: show the average salary for Biology
                   """)
    public AverageSalaryResponse getAverageSalary(String departmentName) {
        return getAverageSalary(new DepartmentRequest(departmentName));
    }

    @Override
    public AverageSalaryResponse getAverageSalary(DepartmentRequest departmentRequest) {
        return departmentService.getAverageSalary(departmentRequest);
    }

    @Command(command = "show count of employees for",
            group = DEPARTMENTS_MANIPULATIONS_GROUP,
            description = """
                   Show the count of employees for the specified department.
                   Example: show count of employees for Biology
                   """)
    public CountOfEmployeesResponse getCountOfEmployees(String departmentName) {
        return getCountOfEmployees(new DepartmentRequest(departmentName));
    }

    @Override
    public CountOfEmployeesResponse getCountOfEmployees(DepartmentRequest departmentRequest) {
        return departmentService.getCountOfEmployee(departmentRequest);
    }

    @ExceptionResolver({DepartmentNotFoundException.class, DepartmentHasNoHeadException.class, DepartmentAlreadyHasHeadException.class})
    public CommandHandlingResult handleDepartmentException(Exception exception) {
        CommandHandlingResult result = CommandHandlingResult.of("Error: " + exception.getMessage());
        log.error(result.message());
        return result;
    }

    @ExceptionResolver(JsonProcessingException.class)
    public CommandHandlingResult handleJsonProcessingException(JsonProcessingException exception) {
        CommandHandlingResult result = CommandHandlingResult.of("Error occurred during JSON processing: " + exception.getMessage());
        log.error(result.message());
        return result;
    }

}
