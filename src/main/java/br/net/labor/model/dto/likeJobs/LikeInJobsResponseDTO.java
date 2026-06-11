package br.net.labor.model.dto.likeJobs;

import br.net.labor.model.dto.jobs.JobsVacanciesResponseDTO;

public record LikeInJobsResponseDTO(
        String jobName,
        String candidateName
) {
}
