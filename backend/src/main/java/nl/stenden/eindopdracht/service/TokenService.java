package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Token;

public interface TokenService
{
    Token findTokenByIds(String groupId, String studentId);
    Token findTokenById(int id);
    void addToken(Token token);
    void deleteToken(int id);
}
