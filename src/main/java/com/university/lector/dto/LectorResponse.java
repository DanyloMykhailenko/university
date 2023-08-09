package com.university.lector.dto;

public record LectorResponse(Long id, String fullName, Boolean justCreated) {

    @Override
    public String toString() {
        return justCreated ? "Lector %s was added with id %d".formatted(fullName, id) : fullName;
    }

}
