package groomthon.studymate.dto;

import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import lombok.Data;

import java.util.List;

@Data
public class MentoringResDto {
    private int week;
    private Long mentoringId;
    private String title;
    private String contents;

    private Frequency frequency;

    private Subject subject;
    private List<String> imageUrls;

    public MentoringResDto(int week,Long mentoringId,String title, String contents, Frequency frequency, Subject subject,List<String> imageUrls) {
        this.week=week;
        this.mentoringId=mentoringId;
        this.title = title;
        this.contents = contents;
        this.frequency = frequency;
        this.subject = subject;
        this.imageUrls=imageUrls;
    }






    //좋아요 수
    // 댓글 수


}
