package groomthon.studymate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageResDto {

    private Long id;

    private String name;

    private String type;

    private byte[] imageData;
}
