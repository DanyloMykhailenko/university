package com.university.lector.service;

import java.util.Set;

import com.university.lector.dto.GlobalSearchRequest;
import com.university.lector.dto.LectorResponse;

public interface LectorService {

    Set<LectorResponse> globalSearchByName(GlobalSearchRequest globalSearchRequest);

}
