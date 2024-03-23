package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TeamRate extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "rate_id")
    private Long id;

    @Column
    private String contents;

    @Column
    private double rate;

    @Column
    private int prefer;

    @Column
    private Long study_id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writeUser;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private User objectuser;

    public TeamRate(String contents, double rate, int prefer, Long study_id,User writeUser, User objectuser) {
        this.contents = contents;
        this.rate = rate;
        this.prefer=prefer;
        this.study_id=study_id;
        this.writeUser = writeUser;
        this.objectuser = objectuser;
    }
}
