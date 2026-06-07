package br.net.labor.service;

import br.net.labor.model.dto.jobs.JobsVacanciesRequestDTO;
import br.net.labor.model.dto.jobs.JobsVacanciesResponseDTO;
import br.net.labor.model.dto.typeUsers.company.CompanyResponseDTO;
import br.net.labor.model.jobs.JobVacancies;
import br.net.labor.model.typeUser.Company;
import br.net.labor.repository.CompanyRepository;
import br.net.labor.repository.JobVacanciesRepository;
import org.springframework.stereotype.Service;

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
        JobVacancies jobVacancies = new JobVacancies();

        jobVacancies.setTitle(jobsVacanciesRequestDTO.title());
        jobVacancies.setAbility(jobsVacanciesRequestDTO.ability());
        jobVacancies.setPayValue(jobsVacanciesRequestDTO.payValue());
        jobVacancies.setInitAndEndTime(jobsVacanciesRequestDTO.initAndEndTime());
        jobVacancies.setDateJob(jobsVacanciesRequestDTO.dateJob());
        jobVacancies.setDescription(jobsVacanciesRequestDTO.description());
        jobVacancies.setCompany(company);
        var savedJob = jobVacanciesRepository.save(jobVacancies);

        CompanyResponseDTO companyResponse = new CompanyResponseDTO(
                company.getCompanyName(),
                company.getUser().getEmail()
        );

        return new JobsVacanciesResponseDTO(
                savedJob.getTitle(),
                savedJob.getAbility(),
                savedJob.getPayValue(),
                savedJob.getInitAndEndTime(),
                savedJob.getDateJob(),
                savedJob.getDescription(),
                companyResponse
        );

    }

}
