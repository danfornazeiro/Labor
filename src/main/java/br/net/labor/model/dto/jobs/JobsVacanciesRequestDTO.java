package br.net.labor.model.dto.jobs;

import br.net.labor.model.typeUser.Company;

import java.time.LocalDateTime;
import java.util.Date;

public record JobsVacanciesRequestDTO(String title,
                                      String ability,
                                      Double payValue,
                                      LocalDateTime initAndEndTime,
                                      Date dateJob,
                                      String description
) {
}
