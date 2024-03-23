package groomthon.studymate.service;

import groomthon.studymate.dto.CommentDto;
import groomthon.studymate.dto.CommentRequestDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.*;
import groomthon.studymate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final MentoringRepository mentoringRepository;
    private final MentoringCommentRepository mentoringCommentRepository;
    public String addComment( Long studyId, CommentRequestDto dto) {
        //Authenticaton 필요
//        UserDto userDto = (UserDto) authentication.getPrincipal();
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96",Role.MENTEE);
        Study foundStudy= studyRepository.findById(studyId).orElse(null);
        User foundUser= userRepository.findByEmail(userDto.getEmail()).orElse(null);

        Comment comment = new Comment(dto.getContents(), Type.STUDY,foundStudy,foundUser);
        commentRepository.save(comment);
        return "댓글 작성 완료";
    }

    public List<CommentDto> getCommentsByStudy(Long studyId) {
        Study foundStudy= studyRepository.findById(studyId).orElse(null);
        List<Comment> foundComments = commentRepository.findAllByStudy(foundStudy);
        return foundComments.stream().map(comment -> new CommentDto(comment.getId(), comment.getContents(),comment.getCreatedDate(),comment.getUpdatedDate())).collect(Collectors.toList());
    }

    public String addMentoringComment(Long mentoringId, CommentRequestDto dto) {
        UserDto userDto = new UserDto("superuser@gmail.com","슈퍼유저","https://lh3.googleusercontent.com/a/ACg8ocKzuCF06tNfWxK2VEOLD3gxJTlwAk24lb4gmqx5xH29=s96",Role.MENTEE);
        Mentoring foundMentoring= mentoringRepository.findById(mentoringId).orElse(null);
        User foundUser= userRepository.findByEmail(userDto.getEmail()).orElse(null);

        MentoringComment mentoringComment = new MentoringComment(dto.getContents(), Type.MENTORING,foundMentoring,foundUser);
        mentoringCommentRepository.save(mentoringComment);
        return "댓글 작성 완료";
    }

    public List<CommentDto> getCommentsByMentoring(Long mentoringId) {
        Mentoring foundMentoring= mentoringRepository.findById(mentoringId).orElse(null);
        List<MentoringComment> foundMentoringComments = mentoringCommentRepository.findAllByMentoring(foundMentoring);
        return foundMentoringComments.stream().map(comment -> new CommentDto(comment.getId(), comment.getContents(),comment.getCreatedDate(),comment.getUpdatedDate())).collect(Collectors.toList());
    }
}
