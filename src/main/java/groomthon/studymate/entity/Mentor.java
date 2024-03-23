package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mentor {
    @Id
    @GeneratedValue
    @Column(name = "mentor_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;

    public Mentor(User user, Mentoring mentoring) {
        this.user = user;
        this.mentoring = mentoring;
    }

    public void setUser(User user){
        this.user=user;
        user.getMentors().add(this);
    }
    public void setMentoring(Mentoring mentoring){
        this.mentoring=mentoring;
        mentoring.getMentors().add(this);
    }
}
