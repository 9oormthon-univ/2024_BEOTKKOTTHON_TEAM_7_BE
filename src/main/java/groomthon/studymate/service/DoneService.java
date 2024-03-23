package groomthon.studymate.service;

import groomthon.studymate.dto.*;
import groomthon.studymate.entity.*;
import groomthon.studymate.repository.DoneRepository;
import groomthon.studymate.repository.ImageRepository;
import groomthon.studymate.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DoneService {
    private final StudyRepository studyRepository;
    private final ImageService imageService;
    private final DoneRepository doneRepository;
    private final UserStudyService userStudyService;
    private final ImageRepository imageRepository;


    public String createDone( Long studyId, List<MultipartFile> multipartFiles, String contents,int week) throws IOException {
//        Authentication authentication,

        Study foundStudy = studyRepository.findById(studyId).orElse(null);
//        UserDto userDto=(UserDto)authentication.getPrincipal();
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96",Role.MENTEE);


        Done resultDone= doneRepository.save(new Done(contents, foundStudy, userDto.getEmail(),week));
        log.info("multipartFiles.size():"+ multipartFiles.size());


        for(int i=0;i< multipartFiles.size();i++){
            imageService.createImage( multipartFiles.get(i),resultDone);
        }

        return "저장 완료";

    }

    public TotalDoneResDto findAllDone(Long studyId) {
        Study foundStudy = studyRepository.findById(studyId).orElse(null);
        List<Done> foundDones = doneRepository.findByStudy(foundStudy);

        //이 스터디에 참가하는 유저들
        List<User> users = userStudyService.getUserListByStudyId(foundStudy.getId());
        List<UserResponseDto> userResponseDtos= new ArrayList<>();
        for(User user:users){
            userResponseDtos.add(new UserResponseDto(user.getId(),user.getName(),user.getEmail(), user.getPicture()));
        }

        List<DoneResDto> dtos=new ArrayList<>();
        for(Done done:foundDones){
            List<ImageResDto> imageResDtos=new ArrayList<>();
            List<Image> imageList = done.getImageList();
            List<Image> images = imageRepository.findByDone(done);
            List<String> urls= imageRepository.findByDone(done).stream().map(image -> image.getUrl()).collect(Collectors.toList());

            dtos.add(new DoneResDto(done.getId(),done.getContents(),done.getWriterEmail(),done.getCreatedDate(),done.getUpdatedDate(),done.getWeek(),urls));
        }


        return new TotalDoneResDto(foundStudy.getTitle(),userResponseDtos,dtos);
    }
}
