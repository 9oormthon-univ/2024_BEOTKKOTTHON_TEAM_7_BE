package groomthon.studymate.controller;

import groomthon.studymate.entity.MentoringHeart;
import groomthon.studymate.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;

//    @GetMapping("user/study/{study_id}/heart")
//    public String pushHeart(Authentication authentication, @PathVariable(name = "study_id")Long study_id){
//        return heartService.pushHeart(authentication,study_id);
//    }
    @GetMapping("user/study/{study_id}/heart")
    public String pushHeart( @PathVariable(name = "study_id")Long study_id){
        return heartService.pushHeart(study_id);
    }


    @GetMapping("user/mentoring/{mentoring_id}/heart")
    public String pushMentoringHeart( @PathVariable(name = "mentoring_id")Long mentoring_id){
        return heartService.pushMentoringHeart(mentoring_id);
    }




}
