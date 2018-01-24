package nl.stenden.eindopdracht.filter;

import nl.stenden.eindopdracht.model.Token;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.repository.UserRepository;
import nl.stenden.eindopdracht.service.AuthTokenService;
import nl.stenden.eindopdracht.service.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(){
        logger.info("Init TokenAuthenticationFilter");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

        //extract token from header
        final String accessToken = httpRequest.getHeader("auth-token");
        if (accessToken != null) {
            Date currentTime = new Date();

            User user = userService.findByAuthToken(accessToken);
            if(user == null){
                logger.info("could not find an user with the token");
            }

            if(currentTime.before(user.getAuthToken().getDate())){
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());

                authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                if(usernamePasswordAuthenticationToken.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        chain.doFilter(request, response);
    }

}