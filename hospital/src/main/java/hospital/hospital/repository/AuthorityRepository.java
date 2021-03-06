package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital.model.Authority;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
