package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public Token findTokenByIds(String groupId, String studentId) {
        return null; //return Token
    }

    @Override
    public void addToken(Token token) {

    }
}
