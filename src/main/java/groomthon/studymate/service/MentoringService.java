package groomthon.studymate.service;

import groomthon.studymate.dto.MentoringFullInfo;
import groomthon.studymate.dto.MentoringResDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.*;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import groomthon.studymate.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MentoringService {
    private final UserRepository userRepository;
    private final MentoringRepository mentoringRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final MentoringImageService mentoringImageService;
    private final MentoringImageRepository mentoringImageRepository;

    public String createMentoring(Authentication authentication,List<MultipartFile> multipartFiles,int week, String teamName, String title, String contents, Subject subject, String type, Frequency frequency) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        User user= userRepository.findByEmail(userDto.getEmail()).orElse(null);

        Mentoring newMentoring = new Mentoring(week,teamName,title,contents,user,subject,frequency);
        Mentoring savedMentoring = mentoringRepository.save(newMentoring);

        for(MultipartFile multipartFile:multipartFiles){
            mentoringImageService.createImage(multipartFile,savedMentoring);
        }

        //연관관계 매핑
        if(type.equals("mentor")){//멘토 요청일 경우
            Mentor newMentor = new Mentor(user, savedMentoring);
            newMentor.setMentoring(savedMentoring);
            newMentor.setUser(user);
            mentorRepository.save(newMentor);
        }else if(type.equals("mentee")){//멘티 요청일 경우
            Mentee newMentee = new Mentee(user, savedMentoring);
            newMentee.setMentoring(savedMentoring);
            newMentee.setUser(user);
            menteeRepository.save(newMentee);
        }else{
            throw new IllegalArgumentException("멘토나 멘티만 가능합니다");
        }

        //나중에 멘토링 객체 생성되면 여기서 이미지 추가해주기

        return "저장 완료";

    }


    //멘토링 전체 조회
    public List<MentoringResDto> findAllMentoring() {
        List<Mentoring> foundMentoring = mentoringRepository.findAll();

        List<MentoringResDto> resultDtos=new ArrayList<>();
        for(Mentoring mentoring: foundMentoring){

            List<MentoringImage> foundMentoringImages = mentoringImageRepository.findByMentoring(mentoring);
            List<String> urls=foundMentoringImages.stream().map(fm->fm.getUrl()).collect(Collectors.toList());

            resultDtos.add(new MentoringResDto(mentoring.getWeek(),mentoring.getId(), mentoring.getTitle(),mentoring.getContents(),mentoring.getFrequency(),mentoring.getSubject(),urls));
        }
        return resultDtos;
    }

    //멘토링 상세조회
    public MentoringFullInfo getFullMentoringInfo(Long mentoringId) {
        Mentoring foundMentoring = mentoringRepository.findById(mentoringId).orElse(null);
        User user=mentoringRepository.findById(mentoringId).orElse(null).getUser();

        List<MentoringImage> foundMentoringImages = mentoringImageRepository.findByMentoring(foundMentoring);
        List<String> urls=foundMentoringImages.stream().map(fm->fm.getUrl()).collect(Collectors.toList());

        return new MentoringFullInfo(foundMentoring.getTitle(), foundMentoring.getContents(), urls);
    }
}
