package nl.stenden.eindopdracht;

import nl.stenden.eindopdracht.filter.CorsFilterRequest;
import nl.stenden.eindopdracht.filter.JsonAuthenticationFilter;
import nl.stenden.eindopdracht.service.UserDetailsServiceImpl;
import nl.stenden.eindopdracht.utility.RequestAwareAuthenticationFailureHandler;
import nl.stenden.eindopdracht.utility.RequestAwareAuthenticationSuccesHandler;
import nl.stenden.eindopdracht.utility.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    RequestAwareAuthenticationSuccesHandler succesHandler;

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
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login", "/api/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CorsFilterRequest(), ChannelProcessingFilter.class)
                .formLogin()
                .loginPage("/api/login")
                .successHandler(succesHandler)
                .failureHandler(new RequestAwareAuthenticationFailureHandler())
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/api/logout");
    }

    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception{
        JsonAuthenticationFilter authFilter = new JsonAuthenticationFilter();
        authFilter.setAuthenticationManager(this.authenticationManager());
        authFilter.setUsernameParameter("email");
        authFilter.setPasswordParameter("password");
        authFilter.setAuthenticationFailureHandler(new RequestAwareAuthenticationFailureHandler());
        authFilter.setAuthenticationSuccessHandler(succesHandler);
        return authFilter;

    }

    @Bean
    public RequestAwareAuthenticationSuccesHandler mySuccessHandler(){
        return new RequestAwareAuthenticationSuccesHandler();
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean RestAuthenticationEntryPoint restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }


}
