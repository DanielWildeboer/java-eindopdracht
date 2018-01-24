package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    AuthToken findByUserId(Long userId);

    AuthToken findByToken(String authToken);
}
