package com.university.lector.controller;

import java.util.Set;

import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.context.InteractionMode;
import org.springframework.stereotype.Component;

import com.university.lector.dto.GlobalSearchRequest;
import com.university.lector.dto.LectorResponse;
import com.university.lector.exception.NoMatchingLectorsNamesException;
import com.university.lector.service.LectorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Command(interactionMode = InteractionMode.INTERACTIVE)
@Component
@RequiredArgsConstructor
@Slf4j
public class ShellLectorController implements LectorController {

    public static final String LECTORS_MANIPULATIONS_GROUP = "Lectors manipulations";

    private final LectorService lectorService;

    @Command(command = "global search by",
            group = LECTORS_MANIPULATIONS_GROUP,
            description = """
                    Returns the list of lectors full names, that contain provided template.
                    Example: global search by van
                    """)
    public Set<LectorResponse> globalSearch(String template) {
        return globalSearch(new GlobalSearchRequest(template));
    }

    @Override
    public Set<LectorResponse> globalSearch(GlobalSearchRequest globalSearchRequest) {
        return lectorService.globalSearchByName(globalSearchRequest);
    }

    @ExceptionResolver(NoMatchingLectorsNamesException.class)
    public CommandHandlingResult handleNoMatchingLectorsNamesException(NoMatchingLectorsNamesException exception) {
        CommandHandlingResult result = CommandHandlingResult.of("Error: " + exception.getMessage());
        log.error(result.message());
        return result;
    }

}
