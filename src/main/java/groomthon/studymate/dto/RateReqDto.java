package groomthon.studymate.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

@Data
public class RateReqDto {

    Long studyId;
    Long memberId;

    int prefer;// 별로에요:1 좋아요:2 최고에요:3

    double rate;
    String message;
}
