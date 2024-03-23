package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentorimage_id")
    private Long id;

    @Column
    private String url;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public MentorImage(String url, User user) {
        this.url=url;
        this.user=user;
    }
}
