package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.AuthToken;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    @Qualifier("authTokenRepository")
    @Autowired
    AuthTokenRepository authTokenRepository;

    @Override
    public void save(AuthToken authToken) {
        authTokenRepository.save(authToken);
    }

    @Override
    public Page<AuthToken> findTop10() {
        Pageable limit = new PageRequest(0,10);
        return authTokenRepository.findAll(limit);
    }

    @Override
    public AuthToken findByUserId(Long id){
        return authTokenRepository.findByUserId(id);
    }

    @Override
    public AuthToken findById(Long Id) {
        return authTokenRepository.getOne(Id);
    }

    @Override
    public void delete(Long Id) {
        authTokenRepository.delete(Id);
    }
}
