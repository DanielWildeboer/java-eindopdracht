package nl.stenden.eindopdracht.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenAuthenticationFilter extends GenericFilterBean
{


    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

        //extract token from header
        final String accessToken = httpRequest.getHeader("auth-token");
        if (null != accessToken) {

            //get and check whether token is valid ( from DB or file wherever you are storing the token)
            //TODO Populate SecurityContextHolder by fetching relevant information using token

            //TODO FIND THE USER ASSOCIATED WITH THE accesToken variable



            //TODO Uncomment when you find the right user
//            final UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        chain.doFilter(request, response);
    }

}