package nl.stenden.eindopdracht.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.stenden.eindopdracht.model.AuthToken;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.AuthTokenService;
import nl.stenden.eindopdracht.service.TokenService;
import nl.stenden.eindopdracht.service.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtTokenFactory {

    @Autowired
    AuthTokenService authTokenService;

    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *  This method creates a jwt acces token which is used to validate requests on resources
     * @param user used to generate a token based on the user email
     * @return
     * @throws NullPointerException
     */
    public AuthToken createAccessJwtToken(User user) throws NullPointerException {
        if (user.getEmail() == null)
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        //get the current time in a datetime object
        DateTime currentTime = new DateTime();

        //Token creation, set the token expiration to 60 minutes from the currentTime
        String token = Jwts.builder()
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(60).toDate())
                .signWith(SignatureAlgorithm.HS512, user.getEmail().getBytes())
                .compact();

        return new AuthToken(token, currentTime.plusMinutes(60).toDate());


    }
}