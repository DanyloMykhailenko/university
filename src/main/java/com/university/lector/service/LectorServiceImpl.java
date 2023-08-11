package com.university.lector.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.university.lector.dto.LectorResponse;
import com.university.lector.exception.NoMatchingLectorsNamesException;
import com.university.lector.model.Lector;
import com.university.lector.repository.LectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;

    @Override
    public Set<LectorResponse> globalSearchByName(String template) {
        Set<Lector> lectors = lectorRepository.findByFullNameIgnoringCaseContaining(template);
        if (lectors.isEmpty()) {
            throw new NoMatchingLectorsNamesException(template);
        }
        return lectors.stream()
                .map(lector -> new LectorResponse(lector.getId(), lector.getFullName(), false))
                .collect(Collectors.toSet());
    }

}
