package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Token;

public interface TokenService
{
    Token findTokenByIds(int id);
    void addToken(Token token);
    void deleteToken(int id);
}
