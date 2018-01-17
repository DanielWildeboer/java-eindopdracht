package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("tokenRepository")
public interface TokenRepository extends CrudRepository<Token, Integer> {
}
