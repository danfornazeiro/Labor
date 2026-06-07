package br.net.labor.model.dto.typeUsers.candidate;

import br.net.labor.model.user.enums.RolesEnumType;

import java.time.LocalDate;

public record CandidateRequest(
         String email,
         String phoneNumber,
         String password,
         String address,
         String username,
         String cpf,
         LocalDate birthDate,
         String userPhoto,
         String status,
         String realName
) {
}
