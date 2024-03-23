package groomthon.studymate.service;

import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.Heart;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.Study;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.HeartRepository;
import groomthon.studymate.repository.StudyRepository;
import groomthon.studymate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {
    private final HeartRepository heartRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public String pushHeart( Long studyId) {//나중에 하트 카운트 만들어서 증감 해줘야함.
//        Authentication authentication,
//        UserDto userDto=(UserDto) authentication.getPrincipal();
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96", Role.MENTEE);

        Study foundStudy= studyRepository.findById(studyId).orElse(null);
        User foundUser= userRepository.findByEmail(userDto.getEmail()).orElse(null);
        Heart foundHeart = heartRepository.findByStudyAndUser(foundStudy,foundUser).orElse(null);
        if(foundHeart!=null){
            heartRepository.delete(foundHeart);
            return "하트 지움";
        }else{
            Heart heart= new Heart(foundStudy,foundUser);
            heartRepository.save(heart);
            return "하트 누름";
        }



    }
}
