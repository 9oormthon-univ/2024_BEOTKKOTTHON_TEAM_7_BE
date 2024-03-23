package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value=EnumType.STRING)
    private Type type;

    public Comment(String contents, Type type,Study study, User user) {
        this.contents = contents;
        this.study = study;
        this.user = user;
        this.type = type;
    }
}