package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Token;

public interface TokenService
{
    Token findTokenByIds(String groupId, String studentId);
    void addToken(Token token);
}
