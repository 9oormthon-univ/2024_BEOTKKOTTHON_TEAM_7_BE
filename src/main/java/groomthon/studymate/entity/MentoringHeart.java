package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MentoringHeart {
    @Id
    @GeneratedValue
    @Column(name = "mentoringHeart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public MentoringHeart(Mentoring mentoring, User user) {
        this.mentoring=mentoring;
        this.user = user;
    }
}
