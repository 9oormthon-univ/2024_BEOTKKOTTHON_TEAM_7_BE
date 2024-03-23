package groomthon.studymate.repository;

import groomthon.studymate.entity.MentorImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorImageRepository extends JpaRepository<MentorImage, Long> {
}

