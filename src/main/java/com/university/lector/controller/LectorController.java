package com.university.lector.controller;

import java.util.Set;

import com.university.lector.dto.LectorResponse;

public interface LectorController {

    Set<LectorResponse> globalSearch(String template);

}
