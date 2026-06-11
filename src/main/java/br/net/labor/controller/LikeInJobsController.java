package br.net.labor.controller;

import br.net.labor.config.JWTUserData;
import br.net.labor.model.dto.likeJobs.LikeInJobsResponseDTO;
import br.net.labor.model.jobs.JobVacancies;
import br.net.labor.service.CandidateInJobService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/likeInJobs")
public class LikeInJobsController {

    private final CandidateInJobService candidateInJobService;

    public LikeInJobsController(CandidateInJobService candidateInJobService) {
        this.candidateInJobService = candidateInJobService;
    }

    @PostMapping("/{id}")
    public LikeInJobsResponseDTO likeJobs(@AuthenticationPrincipal JWTUserData userData, @PathVariable UUID id){
        if(userData == null){
            throw new RuntimeException("Usuário não autenticado");
        }

        String emailFromLogged = userData.email();

        return candidateInJobService.likeJobs(emailFromLogged, id);
    }

}
