package lt.mackelo.backend.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT u FROM Person u WHERE u.email = ?1")
    Optional<Person> findPersonByEmail(String email);
}
