package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.AuthToken;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthTokenService {
    void save(AuthToken authToken);
    Page<AuthToken> findTop10();
    AuthToken findByUserId(Long userId);
    AuthToken findById(Long Id);
    void delete(Long Id);
}
