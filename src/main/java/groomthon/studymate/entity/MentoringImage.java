package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentoringImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoringimage_id")
    private Long id;

    @Column
    private String url;


    @ManyToOne
    @JoinColumn(name = "mentoring_id")
    private Mentoring mentoring;


    @Builder
    public MentoringImage(String url, Mentoring mentoring) {
        this.url=url;
        this.mentoring=mentoring;
    }
}