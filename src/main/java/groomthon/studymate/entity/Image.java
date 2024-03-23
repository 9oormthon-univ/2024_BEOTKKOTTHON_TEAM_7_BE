package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column
    private String url;


    @ManyToOne
    @JoinColumn(name = "done_id")
    private Done done;


    @Builder
    public Image(String url, Done done) {
        this.url=url;
        this.done = done;
    }
}