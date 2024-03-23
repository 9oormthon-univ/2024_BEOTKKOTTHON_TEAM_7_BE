package groomthon.studymate.repository;

import groomthon.studymate.entity.Mentoring;
import groomthon.studymate.entity.MentoringImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoringImageRepository extends JpaRepository<MentoringImage,Long> {
    List<MentoringImage> findByMentoring(Mentoring mentoring);
}
