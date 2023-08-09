package com.university.lector.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.context.InteractionMode;
import org.springframework.stereotype.Component;

import com.university.lector.dto.LectorResponse;
import com.university.lector.service.LectorService;

import lombok.RequiredArgsConstructor;

@Command(interactionMode = InteractionMode.INTERACTIVE)
@Component
@RequiredArgsConstructor
public class ShellLectorController implements LectorController {

    public static final String LECTORS_MANIPULATIONS_GROUP = "Lectors manipulations";

    private final LectorService lectorService;

    @Override
    @Command(command = "global search by",
            group = LECTORS_MANIPULATIONS_GROUP,
            description = """
                    Returns the list of lectors full names, that contain provided template.
                    Example: global search by van""")
    public Set<LectorResponse> globalSearch(String template) {
        return new HashSet<>(lectorService.globalSearchByName(template)) {
            @Override
            public String toString() {
                return this.stream()
                        .map(LectorResponse::toString)
                        .collect(Collectors.joining(", "));
            }
        };
    }

}
