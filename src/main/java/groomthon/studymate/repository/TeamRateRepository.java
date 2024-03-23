package groomthon.studymate.repository;

import groomthon.studymate.entity.TeamRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRateRepository extends JpaRepository<TeamRate,Long> {
}
