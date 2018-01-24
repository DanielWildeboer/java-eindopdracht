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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.Date;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenAuthenticationFilter extends OncePerRequestFilter
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AUTH_HEADER_NAME = "AUTH-TOKEN";

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(){
        logger.info("Init TokenAuthenticationFilter");
    }

    /**
     * TODO write comment
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

        //extract token from header
        final String accessToken = httpRequest.getHeader(AUTH_HEADER_NAME);
        if (accessToken != null) {
            Date currentTime = new Date();
            logger.info(accessToken);
            User user = userService.findByAuthToken(accessToken);
            if(user == null){
                logger.info("could not find an user with the token");
                response.sendError(response.SC_UNAUTHORIZED, "could not find an user with the token");
                return;
            }

            try {

                if (currentTime.before(user.getAuthToken().getDate())) {

                    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
                    final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());

                    authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (NullPointerException e){
                response.sendError(response.SC_UNAUTHORIZED, "Not authorized");

            }
        }
        chain.doFilter(request, response);
    }

}