package nl.stenden.eindopdracht.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.utility.JwtTokenFactory;
import nl.stenden.eindopdracht.utility.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Component
@Order(4)
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String jsonEmail;
    private String jsonPassword;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AUTH_HEADER_NAME = "AUTH-TOKEN";

    @Autowired
    private UserService userService;

    public JsonAuthenticationFilter(){
        super();
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
        logger.info("Init Custom Auth Filter");
    }

    /**
     * override the default method to check if the json/application header is present
     * @param request
     * @return password
     */
    @Override
    protected String obtainPassword(HttpServletRequest request){
        String password = null;
        if("application/json".equals(request.getHeader("Content-Type"))){
            password = this.jsonPassword;
        } else {
            password = super.obtainPassword(request);
        }
        return password;
    }

    /**
     * override the default method to check if the json/application header is present
     * @param request
     * @return email
     */
    @Override
    protected String obtainUsername(HttpServletRequest request){
        String email = null;
        if("application/json".equals(request.getHeader("Content-Type"))){
            email = this.jsonEmail;
        } else {
            email = super.obtainUsername(request);
        }
        return email;
    }

    /**
     * This method reads the send JSON and converts it into readable strings for the normal Spring-security to use for authentication
     * @param request the received request
     * @param response the response we are gonna send
     * @return
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        logger.info("Attempting authentication");
        if("application/json".equals(request.getHeader("Content-Type"))){
            try{
                BufferedReader br = new BufferedReader(request.getReader());
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                //read the values written by the bufferedReader into a loginRequest object so we can call them later.
                ObjectMapper mapper = new ObjectMapper();
                LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

                //fetch the data from the loginRequest through it's getters
                this.jsonEmail = loginRequest.getEmail();
                this.jsonPassword = loginRequest.getPassword();

            } catch(Exception e) {
                logger.info(e.toString());
            }
        }
        //if the content type isn't json then continue like normal
        return super.attemptAuthentication(request, response);
    }

    /**
     * This method finds the user associated with the email and creates a token based on that user, it then places the
     * token into the database and returns it in the AUTH-TOKEN header for later use.
     * @param request the received request
     * @param response the response we are gonna send
     * @param filterChain the chain of filters
     * @param authentication authentication object that we use to get the user name and stuff
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication)
            throws IOException, ServletException {

        logger.info("Creating accestoken");

        //find the user with the request
        User user = userService.findByEmail(authentication.getName());

        //Creat the jwt token and save it for the user\
        JwtTokenFactory factory = new JwtTokenFactory();
        user.setAuthToken(factory.createAccessJwtToken(user));
        userService.updateUser(user.getId(), user);

        //send token in the response header as "auth-token"
        response.setHeader(AUTH_HEADER_NAME, user.getAuthToken().getToken());
    }
}
