package groomthon.studymate.repository;

import groomthon.studymate.entity.Heart;
import groomthon.studymate.entity.Study;
import groomthon.studymate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart,Long> {
//    @Query("select case when count(h) >0 then true else false end from Heart h where h.study= :study and h.user = :user")
//    Optional<Heart> findByStudyIdAndUserId(@Param("study") Study study, @Param("user") User user );

    Optional<Heart> findByStudyAndUser(Study study,User user);
    Integer countByStudy(Study study);

}
