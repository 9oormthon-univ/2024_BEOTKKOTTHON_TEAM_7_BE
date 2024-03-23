package groomthon.studymate.service;

import groomthon.studymate.config.auth.jwt.PrincipalDetails;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.User;
import groomthon.studymate.entity.tag.Subject;
import groomthon.studymate.repository.MentorImageRepository;
import groomthon.studymate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final MentorImageService mentorImageService;



    @Transactional
    public User createUser(UserDto dto){
        return userRepository.save(User.builder().email(dto.getEmail()).name(dto.getName()).picture(dto.getPicture()).role(Role.MENTEE).build());
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public String changeToMentor(Authentication authentication, List<MultipartFile> multipartFiles, Subject subject, double score) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        User foundUser = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(foundUser.getRole()==Role.MENTOR){
            return "이미 멘토입니다.";
        }else{
            foundUser.setRole(Role.MENTOR);
            foundUser.setScore(score);
            foundUser.setSubject(subject);

            for(int i=0;i< multipartFiles.size();i++){
                mentorImageService.createImage( multipartFiles.get(i),foundUser);
            }
            return "저장 완료";
        }
    }
}
