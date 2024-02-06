package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * Represents a DTO for creating Museum entity.
 */
public record MuseumCreationDto(
        String name,
        String description,
        String address,
        String collectionType,
        String subject,
        String url,
        Coordinate coordinate
) {

}