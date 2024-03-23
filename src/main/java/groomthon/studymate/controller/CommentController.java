package groomthon.studymate.controller;

import groomthon.studymate.dto.CommentDto;
import groomthon.studymate.dto.CommentRequestDto;
import groomthon.studymate.entity.Comment;
import groomthon.studymate.service.CommentService;
import groomthon.studymate.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

//    @PostMapping("user/study/{study_id}/talk/write")
//    public String addComment(Authentication authentication, @PathVariable(name = "study_id")Long study_id, @RequestBody CommentRequestDto dto){
//        return commentService.addComment(authentication,study_id, dto);
//    }

    @PostMapping("user/study/{study_id}/talk/write")
    public String addComment( @PathVariable(name = "study_id")Long study_id, @RequestBody CommentRequestDto dto){
        return commentService.addComment(study_id, dto);
    }

    @GetMapping("study/{study_id}/talk")
    public List<CommentDto> getCommentsByStudy(@PathVariable(name = "study_id")Long study_id){
        return commentService.getCommentsByStudy(study_id);
    }

    @PostMapping("user/mentoring/{mentoring_id}/talk/write")
    public String addMentoringComment( @PathVariable(name = "mentoring_id")Long mentoring_id, @RequestBody CommentRequestDto dto){
        return commentService.addMentoringComment(mentoring_id, dto);
    }

    @GetMapping("mentoring/{mentoring_id}/talk")
    public List<CommentDto> getCommentsByMentoring(@PathVariable(name = "mentoring_id")Long mentoring_id){
        return commentService.getCommentsByMentoring(mentoring_id);
    }


}