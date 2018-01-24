package nl.stenden.eindopdracht;

import nl.stenden.eindopdracht.filter.CorsFilterRequest;
import nl.stenden.eindopdracht.filter.JsonAuthenticationFilter;
import nl.stenden.eindopdracht.filter.TokenAuthenticationFilter;
import nl.stenden.eindopdracht.service.UserDetailsServiceImpl;
import nl.stenden.eindopdracht.service.UserService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    RequestAwareAuthenticationSuccesHandler succesHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /** This method sets the authentication provider and the userDetailsService on the authenticationManager
     *
     * @param auth AuthenticationManagerBuilder
     */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.userDetailsService(userDetailsService());
    }

    /**Initialize the authentication provider and set the userDetailsService that it should use and the passwordEncoder
     *
     * @return returns the DaoProvider used in the authenticationManagerBuilder
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /** This method enable's certain actions from the outisde and add's the filters required for the login,
     *  cors and json data posting.
     *
     * @param http the http parameter that we can configure
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Disable csrf for now
                .csrf().disable()
                .addFilterAfter(tokenAuthenticationFilter(), JsonAuthenticationFilter.class)
                .addFilterBefore(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CorsFilterRequest(), ChannelProcessingFilter.class)

                //Set the server to not create sessions
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

                //Stackoverflow told me to do this
                .and()
                .httpBasic()

                //Introduce a exception handler for better debugging
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint())

                //logout
                .and()
                .logout()
                .logoutUrl("/api/logout");
    }

    /** Initializes the JsonAuthenticationFilter with the authenticationManager, which parameters the username and password the login will take and tells
     * the filter which succes and failure handles to use.
     *
     * @return JsonAuthenticationFilter that enables json data to be posted to the api/login
     * @throws Exception
     */
    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception{
        JsonAuthenticationFilter authFilter = new JsonAuthenticationFilter();
        authFilter.setAuthenticationManager(authenticationManager());

        authFilter.setPasswordParameter("password");
        authFilter.setUsernameParameter("email");
        authFilter.setAuthenticationSuccessHandler(mySuccessHandler());
        authFilter.setAuthenticationFailureHandler(myFailureHandler());
        return authFilter;
    }

    /*
-------------------------------------SIMPLE BEANS------------------------------------------
     */

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

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }


}
