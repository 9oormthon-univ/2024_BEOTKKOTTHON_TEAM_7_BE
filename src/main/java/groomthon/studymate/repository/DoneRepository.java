package groomthon.studymate.repository;

import groomthon.studymate.entity.Done;
import groomthon.studymate.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoneRepository extends JpaRepository<Done,Long> {
    List<Done> findByStudy(Study study);
}
