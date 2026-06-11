package br.net.labor.controller;

import br.net.labor.model.dto.typeUsers.LoginRequest;
import br.net.labor.model.dto.typeUsers.LoginResponse;
import br.net.labor.model.dto.typeUsers.candidate.CandidateRequest;
import br.net.labor.model.dto.typeUsers.candidate.CandidateResponse;
import br.net.labor.model.dto.typeUsers.company.CompanyRequestDTO;
import br.net.labor.model.dto.typeUsers.company.CompanyResponseDTO;
import br.net.labor.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/enterprise/register")
    public CompanyResponseDTO register(@RequestBody CompanyRequestDTO companyRequestDTO){
        return authService.registerCompany(companyRequestDTO);
    }
    @PostMapping("/candidate/register")
    public CandidateResponse register(@RequestBody CandidateRequest candidateRequest){
        return authService.registerCandidate(candidateRequest);
    }
    @PostMapping("/admin/register")
    public void registerAdm(){
      authService.cadAdm();
    }
}
