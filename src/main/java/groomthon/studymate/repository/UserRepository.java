package groomthon.studymate.repository;

import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    @Query("select u from User u where u.role=:role")
    List<User> findByRole_Mentor(Role role);
}
