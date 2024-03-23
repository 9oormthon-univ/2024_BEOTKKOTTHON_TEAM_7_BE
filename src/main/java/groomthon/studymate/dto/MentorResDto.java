package groomthon.studymate.dto;

import groomthon.studymate.entity.tag.Subject;
import lombok.Data;

@Data
public class MentorResDto {
    //사진, 이름 ,분야, 점수
    private String picture;
    private String name;
    private Subject subject;
    private double score;

    public MentorResDto(String picture, String name, Subject subject, double score) {
        this.picture = picture;
        this.name = name;
        this.subject = subject;
        this.score = score;
    }
}
