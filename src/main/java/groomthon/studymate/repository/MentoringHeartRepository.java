package groomthon.studymate.repository;

import groomthon.studymate.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentoringHeartRepository extends JpaRepository<MentoringHeart,Long> {
    Optional<MentoringHeart> findByMentoringAndUser(Mentoring mentoring, User user);
    Integer countByMentoring(Mentoring mentoring);
}
