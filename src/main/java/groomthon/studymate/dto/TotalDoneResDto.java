package groomthon.studymate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TotalDoneResDto {
    private String teamName;

    List<UserResponseDto> users;

    List<DoneResDto> dones;
}
