package nl.workingtalent.abnassignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.workingtalent.abnassignment.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
	
}
