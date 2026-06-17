package br.net.labor.model.dto.jobs;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record JobsVacanciesResponseWithOutCandidatesDTO(
                                       UUID id,
                                       String title,
                                       String ability,
                                       Double payValue,
                                       LocalTime initTime,
                                       LocalTime endTime,
                                       Date dateJob,
                                       String description,
                                       String companyName
) {
}
