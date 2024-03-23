package groomthon.studymate.controller;

import groomthon.studymate.dto.StudyQueryDto;
import groomthon.studymate.dto.StudyRequestDto;
import groomthon.studymate.dto.StudyResponseDto;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import groomthon.studymate.service.StudyService;
import groomthon.studymate.service.UserStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;
    private final UserStudyService userStudyService;

    //스터디 작성
//    @PostMapping("/user/study/write")
//    public StudyResponseDto createStudy(Authentication authentication, @RequestBody StudyRequestDto studyRequestDto){
//        return studyService.createStudy(authentication,studyRequestDto);
//    }
    @PostMapping("/user/study/write")
    public StudyResponseDto createStudy( @RequestBody StudyRequestDto studyRequestDto){
        return studyService.createStudy(studyRequestDto);
    }







    //스터디 마감 안된것 전체 조회(0이면 모집 안끝난것 1이면 끝난것)
    @GetMapping("/study")
    public List<StudyResponseDto> findAllStudy(Pageable pageable){
        return studyService.findAllStudy(pageable,false);
    }

    //스터디 마감된것 전체조회
    @GetMapping("/study/complete")
    public List<StudyResponseDto> findAllCompleteStudy(Pageable pageable){
        return studyService.findAllStudy(pageable,true);
    }

    //스터디 하나 조회
    @GetMapping("/study/{study_id}")
    public StudyResponseDto findOneStudy(@PathVariable("study_id") Long study_id){
        return studyService.findOneStudy(study_id);
    }

    //스터디 참가
//    @GetMapping("/user/study/{study_id}/write")
//    public String joinStudy(Authentication authentication, @PathVariable("study_id") Long study_id){
//        return userStudyService.joinStudy(authentication,study_id);
//
//    }

    @GetMapping("/user/study/{study_id}/write")
    public String joinStudy( @PathVariable("study_id") Long study_id){
        return userStudyService.joinStudy(study_id);
    }

    //조건 조회
    @GetMapping("/study/query")
    public List<StudyResponseDto> findByUserName(@RequestParam(name = "name")String name, @RequestParam(name = "freq")List<Frequency> freqs, @RequestParam(name = "subject")List<Subject> subjects){
        return studyService.findByQuery(name,freqs,subjects);

    }
    //하트 top3
    @GetMapping("/study/heart3")
    public List<StudyResponseDto> bestHeart3(){
        return studyService.findByHeartTop3();
    }

    //댓글 top3
    @GetMapping("/study/comment3")
    public List<StudyResponseDto> bestComment3(){
        return studyService.findByCommentTop3();
    }

}
