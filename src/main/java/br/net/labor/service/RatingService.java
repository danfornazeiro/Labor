package br.net.labor.service;

import br.net.labor.model.dto.rate.RateRequestDTO;
import br.net.labor.model.dto.rate.RatingResponseDTO;
import br.net.labor.model.hate.Rating;

import br.net.labor.model.typeUser.Candidate;

import br.net.labor.model.user.User;
import br.net.labor.repository.CandidateRepository;
import br.net.labor.repository.CompanyRepository;
import br.net.labor.repository.RatingRepository;
import br.net.labor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService {

    public final RatingRepository ratingRepository;
    public final CompanyRepository companyRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, CompanyRepository companyRepository, CandidateRepository candidateRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.companyRepository = companyRepository;
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    public RatingResponseDTO ratingCompany(RateRequestDTO rateRequestDTO, String email, UUID idUser){
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Candidate candidate = candidateRepository.findByUserEmail(email)
                        .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Rating rating = new Rating();
        rating.setRating(rateRequestDTO.rating());
        rating.setRatingDescription(rateRequestDTO.ratingDescription());

        rating.setUser(user);
        rating.setSentBy(candidate);

        userRepository.save(user);
        var savedRate = ratingRepository.save(rating);

        return new RatingResponseDTO(
                savedRate.getId(),
                savedRate.getRating(),
                savedRate.getRatingDescription(),
                candidate.getUsername(),
                user.getUsername()
        );
    }

    public List<RatingResponseDTO> getAll(){
        return ratingRepository.findAll().stream()
                .map(rating -> new RatingResponseDTO(
                        rating.getId(),
                        rating.getRating(),
                        rating.getRatingDescription(),
                        rating.getSentBy().getUsername(),
                        rating.getUser().getUsername()
                )).toList();
    }

}
