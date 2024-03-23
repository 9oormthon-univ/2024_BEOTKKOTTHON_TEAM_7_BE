package groomthon.studymate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Done extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "done_id")
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @Column
    private String writerEmail;

    @Column
    private int week;

    @Column
    @OneToMany(mappedBy = "done")
    private List<Image> imageList;


    public Done(String contents, Study study,String writerEmail,int week) {
        this.contents = contents;
        this.study = study;
        this.writerEmail=writerEmail;
        this.week = week;
    }
}
