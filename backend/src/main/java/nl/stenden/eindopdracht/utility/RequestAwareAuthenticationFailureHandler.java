package nl.stenden.eindopdracht.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String errorMessage;
        if(e.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            errorMessage = "Username not found";
        } else if (e.getClass().isAssignableFrom(LockedException.class)) {
            errorMessage = "Too many attempts";
        } else if(e.getClass().isAssignableFrom(BadCredentialsException.class)) {
            errorMessage = "Bad credentials";
        } else {
            errorMessage = "you done goofed: " + e.toString();
        }
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), errorMessage);
    }
}
