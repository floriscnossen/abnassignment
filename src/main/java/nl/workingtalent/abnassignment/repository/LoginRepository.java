package nl.workingtalent.abnassignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.workingtalent.abnassignment.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Optional<Login> findByToken(String token);
}
