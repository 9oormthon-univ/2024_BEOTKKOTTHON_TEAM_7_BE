package groomthon.studymate.repository;

import groomthon.studymate.entity.Done;
import groomthon.studymate.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByDone(Done done);
}