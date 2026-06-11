package br.net.labor.model.dto.jobs;

import br.net.labor.model.typeUser.Candidate;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record JobsVacanciesResponseDTO(
                                       UUID id,
                                       String title,
                                       String ability,
                                       Double payValue,
                                       LocalTime initTime,
                                       LocalTime endTime,
                                       Date dateJob,
                                       String description,
                                       String companyName,
                                       List<Candidate> candidate
) {
}
