package br.net.labor.controller;

import br.net.labor.model.dto.jobs.JobsVacanciesRequestDTO;
import br.net.labor.model.dto.jobs.JobsVacanciesResponseDTO;
import br.net.labor.service.JobVacanciesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import br.net.labor.config.JWTUserData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobsVacancies")
public class JobsVacanciesController {

    private final JobVacanciesService jobVacanciesService;

    public JobsVacanciesController(JobVacanciesService jobVacanciesService) {
        this.jobVacanciesService = jobVacanciesService;
    }

    @PostMapping
    public ResponseEntity<JobsVacanciesResponseDTO> createJobs(@AuthenticationPrincipal JWTUserData userData, @RequestBody JobsVacanciesRequestDTO jobsVacanciesRequestDTO){
        if (userData == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        String emailFromLoggeduser = userData.email();

        JobsVacanciesResponseDTO response = jobVacanciesService.createJobsVacancies(jobsVacanciesRequestDTO, emailFromLoggeduser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
