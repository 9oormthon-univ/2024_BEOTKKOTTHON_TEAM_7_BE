package groomthon.studymate.service;

import groomthon.studymate.dto.RateReqDto;
import groomthon.studymate.dto.RateResDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.TeamRate;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.TeamRateRepository;
import groomthon.studymate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamRateService {
    private final TeamRateRepository teamRateRepository;
    private final UserRepository userRepository;

    public String rateUser( RateReqDto rateReqDto) {
//        Authentication authentication,
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96", Role.MENTEE);
//        UserDto userDto= (UserDto) authentication.getPrincipal();

        User foundUser = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        User objectUser = userRepository.findById(rateReqDto.getMemberId()).orElse(null);


        teamRateRepository.save(new TeamRate(rateReqDto.getMessage(), rateReqDto.getRate(), rateReqDto.getPrefer(),rateReqDto.getStudyId() ,foundUser,objectUser));

        return "팀원 평가 완료";
    }

//    public List<RateResDto> rateAboutMe(Authentication authentication) {
//        UserDto userDto= (UserDto) authentication.getPrincipal();
//
//    }
}
