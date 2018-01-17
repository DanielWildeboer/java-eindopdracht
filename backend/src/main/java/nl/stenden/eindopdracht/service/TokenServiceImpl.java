package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Token;
import nl.stenden.eindopdracht.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token findTokenByIds(int id) {
        return tokenRepository.findOne(id);
    }

    @Override
    public void addToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public void deleteToken(int id) {
        tokenRepository.delete(id);
    }
}
