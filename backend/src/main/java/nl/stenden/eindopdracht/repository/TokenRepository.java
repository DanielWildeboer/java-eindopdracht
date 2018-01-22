package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("tokenRepository")
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByGroupIdAndStudentId(String groupId, String studentId);
}

