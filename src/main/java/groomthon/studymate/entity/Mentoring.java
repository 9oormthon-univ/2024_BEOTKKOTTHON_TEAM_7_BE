package groomthon.studymate.entity;

import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Mentoring extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "mentoring_id")
    private Long id;

    @Column
    private String teamName;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private int week;

    @OneToMany(mappedBy = "mentoring")
    List<Mentor> mentors= new ArrayList<>();

    @OneToMany(mappedBy = "mentoring")
    List<Mentee> mentees= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User user;

    public Mentoring(int week,String teamName, String title, String contents, User user,Subject subject,Frequency frequency) {
        this.week=week;
        this.teamName=teamName;
        this.title = title;
        this.contents = contents;
        this.subject=subject;
        this.frequency=frequency;
        this.user = user;
    }

    @Column
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
}
