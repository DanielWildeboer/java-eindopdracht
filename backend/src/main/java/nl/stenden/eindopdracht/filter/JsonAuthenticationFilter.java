package nl.stenden.eindopdracht.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.stenden.eindopdracht.utility.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@Component
@Order(4)
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String jsonEmail;
    private String jsonPassword;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
}
