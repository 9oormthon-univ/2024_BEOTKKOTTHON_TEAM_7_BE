package groomthon.studymate.repository;

import groomthon.studymate.entity.Study;
import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudyRepository extends JpaRepository<Study,Long> {

    @Query("select s from Study s where s.complete= :complete order by s.createdDate desc")
    Page<Study> findAllByComp(Pageable pageable, @Param("complete") boolean complete);


    //유저 이름을 주면 그 유저의 이메을을 스터디의 작성자이메일이
// select us from userstudy  us where
    @Query("select s from Study s left join User u on s.writer=u.email where u.name=:username or s.frequency in :freqs or s.subject in :subjects")
    List<Study> findByQuery(@Param("username")String username, @Param("freqs")List<Frequency> freq, @Param("subjects")List<Subject> subject);

    @Query("select s from Study s left join Heart h on s=h.study group by s.id order by count(h) desc limit 3")
    List<Study> findByHeartTop3();

    @Query("select s from Study s left join Comment  c on s=c.study group by s.id order by count(c) desc limit 3")
    List<Study> findByCommentTop3();



}
