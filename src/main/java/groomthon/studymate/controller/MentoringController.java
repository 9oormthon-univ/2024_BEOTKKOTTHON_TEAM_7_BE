package groomthon.studymate.controller;

import groomthon.studymate.dto.MentoringFullInfo;
import groomthon.studymate.dto.MentoringResDto;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import groomthon.studymate.service.MentoringService;
import groomthon.studymate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MentoringController {
    private final MentoringService mentoringService;
    private final UserService userService;



    // 멘토링 등록
    @PostMapping(value = "/user/mentoring/register",consumes = "multipart/form-data")
    public String createMentoring(Authentication authentication, @RequestPart(value = "file",required = false) List<MultipartFile> multipartFiles,
                                  @RequestParam(value = "week") int week,
                                  @RequestParam(value = "teamname") String teamName, @RequestParam(value = "title") String title,
                                  @RequestParam(value = "contents") String contents, @RequestParam(value = "subject")Subject subject,
                                  @RequestParam(value = "type")String type, @RequestParam(value = "freq")Frequency frequency)throws IOException {
        return mentoringService.createMentoring(authentication,multipartFiles,week,teamName,title,contents,subject,type,frequency);
    }

    //멘토 등록
    @PostMapping(value = "/user/tomentor",consumes = "multipart/form-data")
    public String changeToMentor(Authentication authentication,@RequestPart(value = "file",required = false) List<MultipartFile> multipartFiles,
                                 @RequestParam(name = "subject")Subject subject, @RequestParam(name = "score")double score){
        return userService.changeToMentor(authentication,multipartFiles,subject,score);
    }

    //멘토링 전체, 멘토 전체, 특정멘토의 상세
    //멘토링 전체
    @GetMapping("/mentoring")
    public List<MentoringResDto> findAllMentoring(){
        return mentoringService.findAllMentoring();
    }

    //멘토링 상세정보  //제목, 내용, 첨부파일
    @GetMapping("/mentoring/{mentoring_id}")
    public MentoringFullInfo getFullMentoringInfo(@PathVariable(name = "mentoring_id")Long mentoringId){
        return mentoringService.getFullMentoringInfo(mentoringId);
    }


}
