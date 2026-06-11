package br.net.labor.model.dto.likeJobs;

import br.net.labor.model.dto.typeUsers.candidate.CandidateResponse;

public record LikeInJobsRequestDTO(
    CandidateResponse candidateResponse
) {
}
