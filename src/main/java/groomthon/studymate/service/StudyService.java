package groomthon.studymate.service;

import groomthon.studymate.dto.StudyQueryDto;
import groomthon.studymate.dto.StudyRequestDto;
import groomthon.studymate.dto.StudyResponseDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.Study;
import groomthon.studymate.entity.User;
import groomthon.studymate.entity.UserStudy;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import groomthon.studymate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final UserStudyRepository userStudyRepository;
    private final UserStudyService userStudyService;
    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;
    public StudyResponseDto createStudy(StudyRequestDto studyRequestDto) {
//        Authentication authentication,
//        UserDto userDto = (UserDto) authentication.getPrincipal();
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96", Role.MENTEE);


        //스터디 생성
        Study newStudy= new Study(studyRequestDto.getTitle(),studyRequestDto.getContents(),
                studyRequestDto.getSubject(),studyRequestDto.getRecruitNum(),false,studyRequestDto.getFrequency(), userDto.getEmail());
        newStudy.setNowNum(1);
        Study savedStudy = studyRepository.save(newStudy);
        //로그인한 정보로 현재 유저 불러오기
        User foundUser= userRepository.findByEmail(userDto.getEmail()).get();

        //중간 테이블 생성, 저장
        UserStudy userStudy= new UserStudy(newStudy,foundUser);
        UserStudy savedUserStudy = userStudyRepository.save(userStudy);

        //서로 연관관계 매핑
        userStudy.setStudy(newStudy);
        userStudy.setUser(foundUser);


        List<User> users = userStudyService.getUserListByStudyId(savedStudy.getId());

        return changer(savedStudy,users);

    }

    public List<StudyResponseDto> findAllStudy(Pageable pageable, boolean cond) {//0은 모집 덜된거 1은 다된거임
        Page<Study> studies = studyRepository.findAllByComp(pageable, cond);
        List<StudyResponseDto> temp = new ArrayList<>();
        for(Study tempStudy:studies){
            List<User> users = userStudyService.getUserListByStudyId(tempStudy.getId());
            temp.add(changer(tempStudy,users));
        }
        return temp;
    }

    public StudyResponseDto findOneStudy(Long studyId) {
        Study foundStudy = studyRepository.findById(studyId).orElse(null);
        List<User> users = userStudyService.getUserListByStudyId(foundStudy.getId());

        return changer(foundStudy,users);

    }

    public StudyResponseDto changer(Study study,List<User> users){
        int heartNum= heartRepository.countByStudy(study);
        int commentNum = commentRepository.countByStudy(study);


        return new StudyResponseDto(study.getId(), study.getTitle(), study.getContents(),
                study.getSubject(),study.getRecruitNum(), study.isComplete(), study.getFrequency(), study.getWriter(), study.getNowNum(),heartNum,commentNum,
                study.getCreatedDate(),study.getUpdatedDate(),
                users.stream().map(user -> User.toUserResDto(user)).collect(Collectors.toList()));
    }


    public List<StudyResponseDto> findByQuery(String name, List<Frequency> freqs, List<Subject> subjects) {
        List<Study> foundStudies = studyRepository.findByQuery(name, freqs, subjects);
        List<StudyResponseDto> studyResponseDtos=new ArrayList<>();
        for(Study study:foundStudies){
            List<User> users = userStudyService.getUserListByStudyId(study.getId());
            studyResponseDtos.add(changer(study,users));
        }
        return studyResponseDtos;
    }

    public List<StudyResponseDto> findByHeartTop3() {
        List<Study> foundStudies = studyRepository.findByHeartTop3();
        List<StudyResponseDto> studyResponseDtos=new ArrayList<>();
        for(Study study:foundStudies){
            List<User> users = userStudyService.getUserListByStudyId(study.getId());
            studyResponseDtos.add(changer(study,users));
        }
        return studyResponseDtos;
    }

    public List<StudyResponseDto> findByCommentTop3() {
        List<Study> foundStudies = studyRepository.findByCommentTop3();
        List<StudyResponseDto> studyResponseDtos=new ArrayList<>();
        for(Study study:foundStudies){
            List<User> users = userStudyService.getUserListByStudyId(study.getId());
            studyResponseDtos.add(changer(study,users));
        }
        return studyResponseDtos;

    }
}
