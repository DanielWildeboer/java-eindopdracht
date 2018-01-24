package nl.stenden.eindopdracht.filter;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.utility.JwtTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
        logger.info("Init CustomBasicAuthenticationFilter");
    }

    /**
     * Method to create a token and save it for the user on succesful login
     */
    @Override
    protected void onSuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final Authentication authResult) {
        logger.info("Creating accestoken");

        //find the user with the request
        User user = userService.findByEmail(request.getUserPrincipal().getName());

        //Creat the jwt token and save it for the user\
        JwtTokenFactory factory = new JwtTokenFactory();
        user.setAuthToken(factory.createAccessJwtToken(user));
        userService.updateUser(user.getId(), user);

        //send token in the response header as "auth-token"
        response.setHeader("auth-token" , "token");
    }

}