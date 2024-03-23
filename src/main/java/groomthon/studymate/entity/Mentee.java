package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mentee {
    @Id
    @GeneratedValue
    @Column(name = "mentee_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;

    public void setUser(User user){
        this.user=user;
        user.getMentees().add(this);
    }
    public void setMentoring(Mentoring mentoring){
        this.mentoring=mentoring;
        mentoring.getMentees().add(this);
    }

    public Mentee(User user, Mentoring mentoring) {
        this.user = user;
        this.mentoring = mentoring;
    }
}
