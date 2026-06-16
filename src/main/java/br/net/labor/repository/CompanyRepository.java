package br.net.labor.repository;

import br.net.labor.model.typeUser.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByUserEmail(String email);

    UUID id(UUID id);
}
