package br.net.labor.controller;

import br.net.labor.config.JWTUserData;
import br.net.labor.model.dto.rate.RateRequestDTO;
import br.net.labor.model.dto.rate.RatingResponseDTO;
import br.net.labor.service.RatingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    public final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{id}")
    public RatingResponseDTO ratingCompany(@AuthenticationPrincipal JWTUserData userData, @PathVariable UUID id, @RequestBody RateRequestDTO rateRequestDTO){
        if(userData == null){
            throw new RuntimeException("Usuário não autenticado");
        }

        String emailFromLogged = userData.email();
        return ratingService.ratingCompany(rateRequestDTO, emailFromLogged, id);
    }

    @GetMapping
    public List<RatingResponseDTO> getAll(){
        return ratingService.getAll();
    }
}
