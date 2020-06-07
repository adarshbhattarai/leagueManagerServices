package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.ERole;
import dantakuti.games.manager.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author adarshbhattarai on 2020-05-24
 * @project LeagueManager
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole name);
}
