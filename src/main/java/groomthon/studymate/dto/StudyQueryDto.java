package groomthon.studymate.dto;

import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import lombok.Data;

import java.util.List;

@Data
public class StudyQueryDto {
    private String name;

    private List<Frequency> frequencies;

    private List<Subject> subjects;
}
