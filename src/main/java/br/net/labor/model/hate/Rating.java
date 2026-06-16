package br.net.labor.model.hate;

import br.net.labor.model.typeUser.Candidate;
import br.net.labor.model.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer rating = 0;
    private String ratingDescription;
    @ManyToOne
    @JoinColumn(name = "send_by_id")
    private Candidate sentBy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Candidate sentBy() {
        return sentBy;
    }

    public void sentBy(Candidate sentBy) {
        this.sentBy = sentBy;
    }

    public User getUser() {
        return user;
    }

    public Candidate getSentBy() {
        return sentBy;
    }

    public void setSentBy(Candidate sentBy) {
        this.sentBy = sentBy;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }
}
