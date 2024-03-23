package groomthon.studymate.dto;

import groomthon.studymate.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


}
