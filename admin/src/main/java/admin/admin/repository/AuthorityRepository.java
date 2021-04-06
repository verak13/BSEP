package admin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import admin.admin.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
