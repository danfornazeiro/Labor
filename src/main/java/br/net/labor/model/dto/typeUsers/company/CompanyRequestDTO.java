package br.net.labor.model.dto.typeUsers.company;

import br.net.labor.model.user.enums.RolesEnumType;

public record CompanyRequestDTO(String email,
                                String phoneNumber,
                                String password,

                                String addresses,
                                String emails,

                                String companyName,
                                String cnpj,
                                String description,
                                String industry,
                                String companyPhoto,
                                String status
) {
}
