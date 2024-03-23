package groomthon.studymate.repository;

import groomthon.studymate.entity.Comment;
import groomthon.studymate.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Integer countByStudy(Study study);
    List<Comment> findAllByStudy(Study study);
}
