package nl.stenden.eindopdracht;

import nl.stenden.eindopdracht.filter.CorsFilterRequest;
import nl.stenden.eindopdracht.filter.CustomBasicAuthenticationFilter;
import nl.stenden.eindopdracht.filter.JsonAuthenticationFilter;
import nl.stenden.eindopdracht.filter.TokenAuthenticationFilter;
import nl.stenden.eindopdracht.service.UserDetailsServiceImpl;
import nl.stenden.eindopdracht.utility.RequestAwareAuthenticationFailureHandler;
import nl.stenden.eindopdracht.utility.RequestAwareAuthenticationSuccesHandler;
import nl.stenden.eindopdracht.utility.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    RequestAwareAuthenticationSuccesHandler succesHandler;

    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Disable csrf for now
                .csrf().disable()
                .addFilterBefore(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CorsFilterRequest(), ChannelProcessingFilter.class)
                .addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(customBasicAuthenticationFilter(), jsonAuthenticationFilter().getClass())

                //Set the server to not use sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //Allow users to register and login
                .authorizeRequests()
                .antMatchers("/api/login", "/api/register")
                .permitAll()
                .and()

                //Force others url's to require authentication
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                //Introduce a exception handler for better debugging
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint())

                //logout
                .and()
                .logout()
                .logoutUrl("/api/logout");
    }

    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception{
        JsonAuthenticationFilter authFilter = new JsonAuthenticationFilter();
        authFilter.setAuthenticationManager(this.authenticationManager());
        authFilter.setPasswordParameter("password");
        authFilter.setUsernameParameter("email");
        authFilter.setAuthenticationSuccessHandler(mySuccessHandler());
        authFilter.setAuthenticationFailureHandler(myFailureHandler());
        return authFilter;
    }

    @Bean
    public CustomBasicAuthenticationFilter customBasicAuthenticationFilter(){
        return new CustomBasicAuthenticationFilter(this.authenticationManager);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter();
    }

    @Bean
    public RequestAwareAuthenticationSuccesHandler mySuccessHandler(){
        return new RequestAwareAuthenticationSuccesHandler();
    }
    @Bean
    public RequestAwareAuthenticationFailureHandler myFailureHandler(){
        return new RequestAwareAuthenticationFailureHandler();
    }

    @Bean RestAuthenticationEntryPoint restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }


}
