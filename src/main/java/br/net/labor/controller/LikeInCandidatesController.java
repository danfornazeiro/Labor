package br.net.labor.controller;

import br.net.labor.model.user.User;
import br.net.labor.service.CompanyLikeCandidates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/likeInCandidates")
public class LikeInCandidatesController {

    private final CompanyLikeCandidates companyLikeCandidates;

    public LikeInCandidatesController(CompanyLikeCandidates companyLikeCandidates) {
        this.companyLikeCandidates = companyLikeCandidates;
    }

    //@GetMapping("/{id}")
    //public Stream<User> like(@PathVariable UUID id){
     //   return companyLikeCandidates.likeCandidate(id);
    //}


}