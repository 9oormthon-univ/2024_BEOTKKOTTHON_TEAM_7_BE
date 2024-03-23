package groomthon.studymate.controller;

import groomthon.studymate.dto.RateReqDto;
import groomthon.studymate.dto.RateResDto;
import groomthon.studymate.service.TeamRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamRateController {
    private final TeamRateService teamRateService;

//    @PostMapping("/user/study/rate")
//    public String rate(Authentication authentication, @RequestBody RateReqDto rateReqDto){
//        return teamRateService.rateUser(authentication,rateReqDto);
//    }

    @PostMapping("/user/study/rate")
    public String rate( @RequestBody RateReqDto rateReqDto){
        return teamRateService.rateUser(rateReqDto);
    }

//    @GetMapping("/user/my/rate")
//    public List<RateResDto> rateAboutMe(Authentication authentication){
//        return teamRateService.rateAboutMe(authentication);
//    }


}
