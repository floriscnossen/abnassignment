package nl.workingtalent.abnassignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.workingtalent.abnassignment.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
