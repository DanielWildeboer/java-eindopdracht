package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.repository.UserRepository;
import nl.stenden.eindopdracht.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthoritySet);
    }

}
