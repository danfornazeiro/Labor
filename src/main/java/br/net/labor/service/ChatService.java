package br.net.labor.service;

import br.net.labor.repository.ApplicationRepository;
import br.net.labor.repository.CandidateRepository;
import br.net.labor.repository.JobVacanciesRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final JobVacanciesRepository jobVacanciesRepository;
    private final CandidateRepository candidateRepository;
    private final ApplicationRepository applicationRepository;

    public ChatService(JobVacanciesRepository jobVacanciesRepository, CandidateRepository candidateRepository, ApplicationRepository applicationRepository) {
        this.jobVacanciesRepository = jobVacanciesRepository;
        this.candidateRepository = candidateRepository;
        this.applicationRepository = applicationRepository;
    }

//    private void candidateIsSelected(UUID idCandidate){
//        boolean isSelected = applicationRepository.existsByCandidateIdAndStatus(idCandidate, ApplicationStatus.SELECTED);
//        if(isSelected){
//
//        }
//    }
}
