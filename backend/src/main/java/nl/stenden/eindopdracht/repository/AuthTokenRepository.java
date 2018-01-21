package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    @Query("select a from AuthToken a where a.userId = :userId")
    AuthToken findByUserId(@Param("userId") Long userId);

    @Query("select a from AuthToken a where a.token = :authToken")
    AuthToken findByToken(@Param("authToken")String authToken);
}