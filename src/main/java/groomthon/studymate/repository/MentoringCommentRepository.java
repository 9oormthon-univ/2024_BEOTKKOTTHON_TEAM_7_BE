package groomthon.studymate.repository;

import groomthon.studymate.entity.Mentoring;
import groomthon.studymate.entity.MentoringComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentoringCommentRepository extends JpaRepository<MentoringComment,Long> {
    List<MentoringComment> findAllByMentoring(Mentoring mentoring);
}
