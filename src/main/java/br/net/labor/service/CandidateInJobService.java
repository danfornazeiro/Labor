package br.net.labor.service;

import br.net.labor.model.dto.likeJobs.LikeInJobsResponseDTO;
import br.net.labor.model.jobs.JobVacancies;
import br.net.labor.model.typeUser.Candidate;
import br.net.labor.repository.CandidateRepository;
import br.net.labor.repository.JobVacanciesRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateInJobService {

    private final JobVacanciesRepository jobVacanciesRepository;
    private final CandidateRepository candidateRepository;

    public CandidateInJobService(JobVacanciesRepository jobVacanciesRepository, CandidateRepository candidateRepository) {
        this.jobVacanciesRepository = jobVacanciesRepository;
        this.candidateRepository = candidateRepository;
    }

    public LikeInJobsResponseDTO likeJobs(String email, UUID id){
        Candidate candidate = candidateRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        JobVacancies jobVacancies = jobVacanciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        if(jobVacancies.getCandidates().contains(candidate)){
          jobVacancies.removeCandidate(candidate);
          jobVacanciesRepository.save(jobVacancies);
        }
        jobVacancies.addCandidate(candidate);
         jobVacanciesRepository.save(jobVacancies);
         return new LikeInJobsResponseDTO(
                 jobVacancies.getTitle(),
                 candidate.getUsername()
         );
    }

}
