package nl.stenden.eindopdracht.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.stenden.eindopdracht.model.AuthToken;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.AuthTokenService;
import nl.stenden.eindopdracht.service.TokenService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtTokenFactory {

    @Autowired
    AuthTokenService authTokenService;

    public String createAccessJwtToken(User user) {
        if (user.getEmail() == null)
            throw new IllegalArgumentException("Cannot create JWT Token without username");



        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(60).toDate())
                .signWith(SignatureAlgorithm.HS512, user.getEmail().getBytes())
                .compact();

        AuthToken authToken = new AuthToken(token, user);
        authTokenService.save(authToken);
        return token;
    }
}