package br.net.labor.service;

import br.net.labor.model.dto.jobs.JobsVacanciesRequestDTO;
import br.net.labor.model.dto.jobs.JobsVacanciesResponseDTO;
import br.net.labor.model.jobs.JobVacancies;
import br.net.labor.model.typeUser.Candidate;
import br.net.labor.model.typeUser.Company;
import br.net.labor.repository.CompanyRepository;
import br.net.labor.repository.JobVacanciesRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobVacanciesService {

    private final JobVacanciesRepository jobVacanciesRepository;
    private final CompanyRepository companyRepository;

    public JobVacanciesService(JobVacanciesRepository jobVacanciesRepository, CompanyRepository companyRepository) {
        this.jobVacanciesRepository = jobVacanciesRepository;
        this.companyRepository = companyRepository;
    }

    public JobsVacanciesResponseDTO createJobsVacancies(JobsVacanciesRequestDTO jobsVacanciesRequestDTO, String email){
       Company company = companyRepository.findByUserEmail(email)
               .orElseThrow(() -> new RuntimeException("Empresa não encontrada para este usuário logado."));
        JobVacancies jobVacancies = getJobVacancies(jobsVacanciesRequestDTO, company);
        var savedJob = jobVacanciesRepository.save(jobVacancies);

        return new JobsVacanciesResponseDTO(
                savedJob.getId(),
                savedJob.getTitle(),
                savedJob.getAbility(),
                savedJob.getPayValue(),
                savedJob.getInitTime(),
                savedJob.getEndTime(),
                savedJob.getDateJob(),
                savedJob.getDescription(),
                savedJob.getCompany().getCompanyName(),
                savedJob.getCandidates().stream()
                        .map(Candidate::getUsername).toList()
        );
    }

    private static @NonNull JobVacancies getJobVacancies(JobsVacanciesRequestDTO jobsVacanciesRequestDTO, Company company) {
        JobVacancies jobVacancies = new JobVacancies();

        jobVacancies.setTitle(jobsVacanciesRequestDTO.title());
        jobVacancies.setAbility(jobsVacanciesRequestDTO.ability());
        jobVacancies.setPayValue(jobsVacanciesRequestDTO.payValue());
        jobVacancies.setInitTime(jobsVacanciesRequestDTO.initTime());
        jobVacancies.setEndTime(jobsVacanciesRequestDTO.endTime());
        jobVacancies.setDateJob(jobsVacanciesRequestDTO.dateJob());
        jobVacancies.setDescription(jobsVacanciesRequestDTO.description());
        jobVacancies.setCompany(company);
        jobVacancies.setCandidates(null);
        return jobVacancies;
    }

    public List<JobsVacanciesResponseDTO> getAll(){
        return jobVacanciesRepository.findAll()
                .stream()
                .map(job -> new JobsVacanciesResponseDTO(
                        job.getId(),
                        job.getTitle(),
                        job.getAbility(),
                        job.getPayValue(),
                        job.getInitTime(),
                        job.getEndTime(),
                        job.getDateJob(),
                        job.getDescription(),
                        job.getCompany().getCompanyName(),
                        job.getCandidates().stream()
                                .map(Candidate::getUsername).toList()
                )).toList();
    }

    public void deleteById(UUID id){
        jobVacanciesRepository.deleteById(id);
    }

}
