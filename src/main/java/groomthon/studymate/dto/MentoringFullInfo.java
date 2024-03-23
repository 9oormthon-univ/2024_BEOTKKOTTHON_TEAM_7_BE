package groomthon.studymate.dto;

import lombok.Data;

import java.util.List;

@Data//제목, 내용, 첨부파일
public class MentoringFullInfo {
    private String title;
    private String contents;
    private List<String> imageUrls;

    public MentoringFullInfo(String title, String contents, List<String> imageUrls) {
        this.title = title;
        this.contents = contents;
        this.imageUrls = imageUrls;
    }
}
