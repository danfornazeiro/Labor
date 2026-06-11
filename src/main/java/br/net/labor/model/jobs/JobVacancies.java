package br.net.labor.model.jobs;

import br.net.labor.model.typeUser.Candidate;
import br.net.labor.model.typeUser.Company;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_vacancies")
public class JobVacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String ability;
    private Double payValue;
    private LocalTime initTime;
    private LocalTime endTime;
    private Date dateJob;
    private String description;
    @OneToMany(mappedBy = "jobVacancies")
    @JsonIgnoreProperties("jobVacancies")
    private List<Candidate> candidates;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties("jobVacancies")
    private Company company;

    public void addCandidate(Candidate candidate) {
        if (this.candidates == null) {
            this.candidates = new ArrayList<>();
        }
        this.candidates.add(candidate);
        candidate.setJobVacancies(this);
    }

    public void removeCandidate(Candidate candidate){
        if(this.candidates != null){
            this.candidates.remove(candidate);
            candidate.setJobVacancies(null);
        }

    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Double getPayValue() {
        return payValue;
    }

    public void setPayValue(Double payValue) {
        this.payValue = payValue;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Date getDateJob() {
        return dateJob;
    }

    public void setDateJob(Date dateJob) {
        this.dateJob = dateJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
