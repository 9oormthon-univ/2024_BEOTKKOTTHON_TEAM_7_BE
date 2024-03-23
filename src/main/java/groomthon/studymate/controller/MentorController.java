package groomthon.studymate.controller;

import groomthon.studymate.dto.MentorResDto;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MentorController {
    private final UserRepository userRepository;
    @GetMapping("/mentor/list")
    public List<MentorResDto> findAllMentor(){
        List<User> foundUser = userRepository.findByRole_Mentor(Role.MENTOR);
        return foundUser.stream().map(user -> new MentorResDto(user.getPicture(),user.getName(),user.getSubject(),user.getScore())).collect(Collectors.toList());
    }

}
