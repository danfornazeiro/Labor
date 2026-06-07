package br.net.labor.model.dto.jobs;

import br.net.labor.model.dto.typeUsers.company.CompanyResponseDTO;

import java.time.LocalDateTime;
import java.util.Date;

public record JobsVacanciesResponseDTO(String title,
                                       String ability,
                                       Double payValue,
                                       LocalDateTime initAndEndTime,
                                       Date dateJob,
                                       String description,
                                       CompanyResponseDTO companyName
) {
}
