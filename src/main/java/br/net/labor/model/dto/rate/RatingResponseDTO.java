package br.net.labor.model.dto.rate;

import java.util.UUID;

public record RatingResponseDTO(
        UUID id,
        Integer rating,
        String ratingDescription,
        String candidateName,
        String companyName
) {}