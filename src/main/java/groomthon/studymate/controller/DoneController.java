package groomthon.studymate.controller;

import groomthon.studymate.dto.DoneRequestDto;
import groomthon.studymate.dto.DoneResDto;
import groomthon.studymate.dto.TotalDoneResDto;
import groomthon.studymate.service.DoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoneController {
    private final DoneService doneService;

//    @PostMapping(value = "/user/study/{study_id}/afterstudy/write",consumes = "multipart/form-data")
//    public String createDone(@PathVariable("study_id")Long study_id, @ModelAttribute DoneRequestDto doneRequestDto)throws IOException {
//        return doneService.createDone(study_id,doneRequestDto);
//    }

//    @PostMapping(value = "/user/study/{study_id}/afterstudy/write",consumes = "multipart/form-data")
//    public String createDone(Authentication authentication, @PathVariable("study_id")Long study_id,
//                             @RequestPart(value = "file",required = false) List<MultipartFile> multipartFiles,
//                             @RequestParam(value = "contents") String contents,@RequestParam(value = "week") int week)throws IOException {
//        return doneService.createDone(authentication, study_id,multipartFiles,contents,week);
//    }
    @PostMapping(value = "/user/study/{study_id}/afterstudy/write",consumes = "multipart/form-data")
    public String createDone( @PathVariable("study_id")Long study_id,
                             @RequestPart(value = "file",required = false) List<MultipartFile> multipartFiles,
                             @RequestParam(value = "contents") String contents,@RequestParam(value = "week") int week)throws IOException {
        return doneService.createDone( study_id,multipartFiles,contents,week);
    }


    @GetMapping("/study/{study_id}/afterstudy")
    public TotalDoneResDto findAllDone(@PathVariable("study_id")Long study_id){
        return doneService.findAllDone(study_id);
    }


}
