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

    @Autowired
    private UserService userService;

    public JsonAuthenticationFilter(){
        super();
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
        logger.info("Init Custom Auth Filter");
    }

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

                ObjectMapper mapper = new ObjectMapper();
                LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

                this.jsonEmail = loginRequest.getEmail();
                this.jsonPassword = loginRequest.getPassword();
                logger.info("email: " + jsonEmail + " || password: " + jsonPassword );


            } catch(Exception e) {
                logger.info(e.toString());
            }
        }

        return super.attemptAuthentication(request, response);
    }

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
        response.setHeader("AUTH-TOKEN" , user.getAuthToken().getToken());
    }
}
