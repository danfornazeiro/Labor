package br.net.labor.service;

import br.net.labor.model.jobs.JobVacancies;
import br.net.labor.model.typeUser.Candidate;
import br.net.labor.model.user.User;
import br.net.labor.repository.CandidateRepository;
import br.net.labor.repository.CompanyRepository;
import br.net.labor.repository.JobVacanciesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CompanyLikeCandidates {

    private final CandidateRepository candidateRepository;
    private final JobVacanciesRepository jobVacanciesRepository;
    private final CompanyRepository companyRepository;

    public CompanyLikeCandidates(CandidateRepository candidateRepository, JobVacanciesRepository jobVacanciesRepository, CompanyRepository companyRepository) {
        this.candidateRepository = candidateRepository;
        this.jobVacanciesRepository = jobVacanciesRepository;
        this.companyRepository = companyRepository;
    }

    public void likeCandidate(UUID idJob, UUID idCandidate) {
        JobVacancies job = jobVacanciesRepository.findById(idJob)
                .orElseThrow(() -> new RuntimeException("Job not found."));
        Optional<Candidate> candidate = candidateRepository.findById(idCandidate);

//        var  = job.getCandidates();
//        if(candidatesJob.isEmpty()){
//            throw new RuntimeException("This job have no candidates");
//        }




    }


}
