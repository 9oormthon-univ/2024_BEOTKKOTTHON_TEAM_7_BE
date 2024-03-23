package groomthon.studymate.dto;

import groomthon.studymate.entity.Image;
import groomthon.studymate.entity.Study;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class DoneResDto {
    private Long id;

    private String contents;

    private String writerEmail;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private int week;

    private List<String> imageUrls;
}
