package br.net.labor.repository;

import br.net.labor.model.candidateApplication.CandidateApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<CandidateApplication, UUID> {
}
