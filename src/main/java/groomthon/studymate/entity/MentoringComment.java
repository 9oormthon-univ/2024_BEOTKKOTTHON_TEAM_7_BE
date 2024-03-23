package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MentoringComment extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "mentoringComment_id")
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value=EnumType.STRING)
    private Type type;

    public MentoringComment(String contents, Type type,Mentoring mentoring, User user) {
        this.contents = contents;
        this.mentoring=mentoring;
        this.user = user;
        this.type = type;
    }
}
