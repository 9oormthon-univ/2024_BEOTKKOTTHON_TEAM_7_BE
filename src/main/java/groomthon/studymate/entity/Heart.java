package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Heart {
    @Id
    @GeneratedValue
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Heart(Study study, User user) {
        this.study = study;
        this.user = user;
    }
}
